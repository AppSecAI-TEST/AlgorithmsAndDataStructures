package com.conglomo.datastructures.lists;

/**
 * List backed by a linked list
 */
public class LList {
    private Node curr;
    private Node head;
    private Node tail;
    private Node headNode = new Node(null, null);

    private class Node {
        Object cargo;
        Node next;

        private Node(Object cargo, Node next) {
            this.cargo = cargo;
            this.next = next;
        }

        public Node(Object cargo) {
            this.cargo = cargo;
        }
    }


    public LList(Object cargo) {
        curr = head = tail = headNode;
        if (cargo != null) {
            headNode.next = new Node(cargo);
            head = tail = headNode.next;
        }

    }

    public boolean contains(Object cargo) {
        Node temp = headNode;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.cargo.equals(cargo)) {
                return true;
            }
        }
        return false;
    }

    public void add(Object cargo) {
        tail.next = new Node(cargo);
        tail = tail.next;
        if (head == headNode) {
            head = tail;
        }
    }

    public boolean remove(Object cargo) {
        Node temp = headNode;
        Node oneBefore = headNode;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.cargo.equals(cargo)) {
                oneBefore.next = temp.next;
                if (headNode.next != null) {
                    head = headNode.next;
                    if (temp == tail) {
                        tail = oneBefore;
                    }
                } else {
                    head = tail = headNode;
                }
                temp.next = null;
                return true;
            } else {
                oneBefore = oneBefore.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Node temp = headNode;
        StringBuilder builder = new StringBuilder("headNode");
        while (temp.next != null) {
            temp = temp.next;
            builder.append(" -> ");
            builder.append(temp.cargo);
        }
        String list = builder.toString();
        return "{ List: " + list + ",\n head: " + (head == headNode ? " headNode " : head.cargo) + ", \n tail: " + (tail == headNode ? " headNode " : tail.cargo) + '}';
    }
}
