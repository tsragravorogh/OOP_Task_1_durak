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
}
