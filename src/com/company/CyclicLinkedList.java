package com.company;

public class CyclicLinkedList<Player>{

    public class ListItem {
        private Player value;
        private ListItem next;

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
        boolean s = true;
        ListItem curr = head;
        while (true) {
            if(player.equals(head.value)) {
                return head.next.value;
            }else {
                if(curr.value.equals(player)) {
                    return curr.next.value;
                }else{
                    curr = curr.next;
                }
            }
        }
    }

    public void removePlayer(Player player) {
        int count = 0;
        ListItem curr = head;
        while (!curr.value.equals(player)) {
            count++;
            curr = curr.next;
        }
        remove(count);
    }

    public boolean remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            head = head.next;
        }
        if(index == size -1) {
            ListItem node = findNodeBeforeByIndex(index);
            node.next = head;
        } else {
            ListItem curr = findNodeBeforeByIndex(index);
            ListItem tmp = findByIndex(index);
            curr.next = tmp.next;
        }
        size--;
        return false;
    }

    private ListItem findNodeBeforeByIndex(int index) {
        if (index <= 0 || index > size - 1) {
            return null;
        }

        int count = 0;
        ListItem curr = head;
        while (curr.next != null) {
            if (count == index - 1) {
                return curr;
            }
            count++;
            curr = curr.next;
        }
        return null;
    }

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
        for(int i = 1; i < size(); i++){
            item = item.next;
            sb.append(item.value);
        }
        return sb.toString();
    }
}
