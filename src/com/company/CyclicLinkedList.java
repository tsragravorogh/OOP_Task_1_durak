package com.company;

public class CyclicLinkedList<T> {

    private class ListItem {
        public T value;
        public ListItem next;

        public ListItem(T value, ListItem next) {
            this.value = value;
            this.next = next;
        }
    }

    protected ListItem head = null;
    protected ListItem tail = null;

    protected int size = 0;

    public void addFirst(T value) {
        head = new ListItem(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
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

    public T get(int index) throws Exception {
        if (index < 0 || index > size - 1) {
            throw new Exception("Incorrect index");
        }
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
