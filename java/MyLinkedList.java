
class MyLinkedList<T extends Comparable<T>> {
    Node<T> head = null;
    int size = 0;

    int getSize() { return size; }
    Node<T> getHead() { return head; }
    boolean isEmpty() { return size == 0; }

    void insertFront(T item) {
        head = new Node<>(head, item);
        size++;
    }

    void insert(Node<T> p, T item) {
        p.nxt = new Node<>(p.nxt, item);
        size++;
    }

    void deleteFront() {
        head = head.nxt;
        size--;
    }

    void delete(Node<T> p) {
        p.nxt = p.nxt.nxt;
        size--;
    }

    void deleteAll(T item) {
        while (head != null && head.item == item)
            head = head.nxt;
        Node p = head;
        while (p.nxt != null) {
            if (p.nxt.item == item) {
                Node<T> newNext = p.nxt.nxt;
                delete(p);
                p.nxt = newNext;
                size--;
            }
            p = p.nxt;
        }
    }

    int search(T value) {
        Node p = head;
        for (int i=0; p != null; i++) {
            if (p.item == value)
                return i;
            p = p.nxt;
        }
        return -1;
    }

    void print() {
        Node p = head;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.nxt;
        }
        System.out.println();
    }
}
