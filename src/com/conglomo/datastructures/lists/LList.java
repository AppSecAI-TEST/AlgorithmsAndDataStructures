package com.conglomo.datastructures.lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List backed by a linked list
 */
public class LList implements List {
    private Node head;
    private Node tail;
    private Node headerNode;

    private class Node {
        Object cargo;
        Node next;

        public Node(Object cargo) {
            this.cargo = cargo;
        }
    }

    public LList() {
        headerNode = head = tail = new Node(null);
    }

    public LList(Object cargo) {
        this();
        if (cargo != null) {
            append(new Node(cargo));
            head = tail;
        }
    }

    @Override
    public boolean contains(Object cargo) {
        Node temp = headerNode;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.cargo.equals(cargo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node curr = head;

            @Override
            public boolean hasNext() {
                return curr.next != null;
            }

            @Override
            public Object next() {
                Node next = curr.next;
                curr = next;
                return next.cargo;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean addAll(Collection objects) {
        return objects.stream()
                .map(this::add)
                .anyMatch(Boolean.TRUE::equals);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        head = tail = headerNode;
    }

    private Node getNode(int index) {
        Node curr = headerNode;
        int idx = -1;
        while (curr.next != null) {
            curr = curr.next;
            idx++;
            if (idx == index) {
                return curr;
            }
        }
        return null;
    }

    @Override
    public Object get(int index) {
        Node node = getNode(index);
        return node == null ? null : node.cargo;
    }

    @Override
    public Object set(int index, Object element) {
        Node node = getNode(index);
        if (node != null) {
            Object retVal = node.cargo;
            node.cargo = element;
            return retVal;
        }
        return null;
    }

    @Override
    public void add(int index, Object element) {
        Node node = getNode(index);
    }

    @Override
    public Object remove(int index) {
        Object o = get(index);
        remove(o);
        return o;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean add(Object cargo) {
        Node newNode = new Node(cargo);
        boolean empty = isEmpty();
        append(newNode);
        if (empty) {
            head = tail;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return headerNode == head && head == tail;
    }

    private void append(Node newNode) {
        tail.next = newNode;
        tail = tail.next;
    }

    @Override
    public int size() {
        Node curr = headerNode;
        int size = 0;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }
        return size;
    }

    @Override
    public boolean remove(Object cargo) {
        Node curr = headerNode;
        while (curr.next != null) {
            Node prev = curr;
            curr = curr.next;
            if (curr.cargo.equals(cargo)) {
                if (headerNode.next == curr) {   // deleting head
                    if (head == tail) {   // only element
                        clear();
                    } else {   // multiple elements
                        head = head.next;
                    }
                } else if (curr.next == null) {   // deleting tail
                    if (head == tail) {   // only element
                        clear();
                        headerNode.next = null;
                    } else {   // multiple elements
                        tail = prev;
                        prev.next = null;
                    }
                } else {// multiple items
                    prev.next = curr.next;
                    curr.next = null;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public String toString() {
        Node temp = headerNode;
        StringBuilder builder = new StringBuilder("headNode");
        while (temp.next != null) {
            temp = temp.next;
            builder.append(" -> ");
            builder.append(temp.cargo);
        }
        String list = builder.toString();
        return "{ List: " + list + ",\n head: " + head.cargo + ", \n tail: " + tail.cargo + '}';
    }
}
