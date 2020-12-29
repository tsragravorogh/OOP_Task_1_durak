package com.tsragravorogh.Utils;

public class CyclicLinkedList<Player> {

    public class ListItem {
        private Player value;
        private transient ListItem next;

        public ListItem(Player value, ListItem next) {
            this.value = value;
            this.next = next;
        }
    }

    protected ListItem head = null;
    protected ListItem tail = null;

    protected int size = 0;

    public void addFirst(Player value) {
        head = new ListItem(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public boolean isExist(Player player) {
        ListItem item = head;
        for (int i = 0; i < size(); i++) {
            if (item.value == player) return true;
            item = item.next;
        }
        return false;
    }

    public void addLast(Player value) {
        ListItem temp = new ListItem(value, null);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
            temp.next = head;
        }
        size++;
    }

    public Player getNext(Player player) {
        ListItem curr = head;
        while (true) {
            if (curr.value.equals(player)) {
                return curr.next.value;
            } else {
                curr = curr.next;
            }
        }
    }
    public void removePlayer(Player player) {
        ListItem curr = head;
        while (curr.next != null) {
            if (curr.next.value.equals(player)) {
                if (curr == tail) {
                    head = head.next;
                    tail.next = head;
                    size--;
                    return;
                }
                if (curr.next == tail) {
                    curr.next = head;
                    tail = curr;
                    size--;
                    return;
                }
                curr.next = curr.next.next;
                size--;
                return;
            }
            curr = curr.next;
        }
    }



//    public void removePlayerc(Player player) {
//        int count = 0;
//        ListItem curr = head;
//        while (!curr.value.equals(player)) {
//            count++;
//            curr = curr.next;
//        }
//        remove(count);
//    }

//    public boolean remove(int index) {
//        if (index < 0 || index > size - 1) {
//            throw new IllegalArgumentException();
//        }
//        if (index == 0) {
//            head = head.next;
//            tail.next = head;
//        }
//        if (index == size - 1) {
//            ListItem node = findNodeBeforeByIndex(index);
//            node.next = head;
//        } else {
//            ListItem curr = findNodeBeforeByIndex(index);
//            ListItem tmp = findByIndex(index);
//            curr.next = tmp.next;
//        }
//        size--;
//        return false;
//    }

    public int indexByPlayer(Player player) {
        ListItem curr = head;
        int count = 0;
        while (curr.value != player) {
            curr = curr.next;
            count++;
        }
        return count;
    }


    public Player playerBeforePlayer(Player player) {
        ListItem curr = head;
        while (curr.next != null) {
            if(curr.next.value == player) {
                return curr.value;
            }
            curr = curr.next;
        }
        return curr.value;
    }

    public Player findPlayerBeforeByIndex(int index) {
        if (index == 0) {
            int count = size() - 1;
            int indexA = 0;
            ListItem curr = head;
            while (curr.next != null) {
                if (count == indexA) {
                    return curr.value;
                }
                indexA += 1;
                curr = curr.next;
            }
        }


        int count = 0;
        ListItem curr = head;
        while (curr.next != null) {
            if (count == index - 1) {
                return curr.value;
            }

            count++;
            curr = curr.next;
        }
        return null;
    }

//    public ListItem findNodeBeforeByIndex(int index) {
//
//        if (index == 0) {
//            return tail;
//        }
//
//        int count = 0;
//        ListItem curr = head;
//        while (curr.next != null) {
//            if (count == index - 1) {
//                return curr;
//            }
//            count++;
//            curr = curr.next;
//        }
//        return null;
//    }

    private ListItem findByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        int tmpIndex = 0;
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return head;
        }

        ListItem node = head;
        while (node.next != null) {
            node = node.next;
            tmpIndex++;
            if (tmpIndex == index) {
                return node;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public Player get(int index) {
        ListItem curr = head;
        while (index != 0) {
            index--;
            curr = curr.next;
        }
        return curr.value;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(head.value).append(" ");
        ListItem item = head;
        for (int i = 1; i < size(); i++) {
            item = item.next;
            sb.append(item.value);
        }
        return sb.toString();
    }
}
