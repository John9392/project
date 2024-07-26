public class DoublyLinkedList {
    private class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node newNode = new Node(element);

        if (index == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node toRemove;
        if (index == 0) {
            toRemove = head;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        } else if (index == size - 1) {
            toRemove = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            toRemove = head;
            for (int i = 0; i < index; i++) {
                toRemove = toRemove.next;
            }
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
        return toRemove.data;
    }

    public int find(int element) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == element) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void reverse() {
        Node current = head;
        Node temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            head = temp.prev;
        }
    }

    public void merge(DoublyLinkedList otherList) {
        if (otherList.size == 0) {
            return;
        }
        if (this.size == 0) {
            this.head = otherList.head;
            this.tail = otherList.tail;
        } else {
            this.tail.next = otherList.head;
            otherList.head.prev = this.tail;
            this.tail = otherList.tail;
        }
        this.size += otherList.size;
    }

    public void removeDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current.next;
            while (runner != null) {
                if (runner.data == current.data) {
                    Node toRemove = runner;
                    if (toRemove.next != null) {
                        toRemove.next.prev = toRemove.prev;
                    }
                    if (toRemove.prev != null) {
                        toRemove.prev.next = toRemove.next;
                    }
                    if (toRemove == tail) {
                        tail = toRemove.prev;
                    }
                    size--;
                }
                runner = runner.next;
            }
            current = current.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.add(0, 1);
        dll.add(1, 2);
        dll.add(2, 2);
        dll.add(3, 3);
        dll.add(4, 4);

        System.out.println("After adding elements: " + dll);

        dll.remove(2);
        System.out.println("After removing element at index 2: " + dll);

        int index = dll.find(3);
        System.out.println("Index of element 3: " + index);

        dll.reverse();
        System.out.println("After reversing: " + dll);

        DoublyLinkedList dll2 = new DoublyLinkedList();
        dll2.add(0, 5);
        dll2.add(1, 6);

        dll.merge(dll2);
        System.out.println("After merging: " + dll);

        dll.removeDuplicates();
        System.out.println("After removing duplicates: " + dll);
    }
}