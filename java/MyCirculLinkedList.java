import java.util.NoSuchElementException;

public class MyCirculLinkedList<T extends Comparable<T>> {
    Node<T> last = null;
    int size = 0;

    boolean isEmpty() {
        return size == 0;
    }

    void insert(T newItem) {
        Node<T> newNode = new Node<>(null, newItem);
        if (last == null) {
            newNode.nxt = newNode;
            last = newNode;
        } else {
            newNode.nxt = last.nxt;
            last.nxt = newNode;
        }
        size++;
    }

    void delete() {
        if (isEmpty()) throw new NoSuchElementException();
        Node x = last.nxt;
        if (x == last) {
            last = null;
        } else {
            last.nxt = x.nxt;
            x.nxt = null;
        }
        size--;
    }

    void print() {
        Node x = last;
        for (int i=0; i<size; i++) {
            System.out.print(x.item + " ");
            x = x.nxt;
        }
        System.out.println();
    }
}
