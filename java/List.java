import java.util.NoSuchElementException;

public class List<T> {
    private T[] cur;
    public int size;

    private boolean isFull() {
        return size == cur.length;
    }
    public List() {
        cur = (T[]) new Object[10];
        size = 0;
    }

    public T getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return cur[0];
    }

    public void resize(int size) {
        Object[] nxt = new Object[size * 2];
        for (int i = 0; i < size; i++)
            nxt[i] = cur[i];
        cur = (T[]) nxt;
    }

    public void append(T value) {
        if (isFull()) resize(cur.length);
        cur[size++] = value;
    }

    public void insert(T value, int idx) {
        if (size <= idx) throw new IndexOutOfBoundsException();
        if (size == cur.length) resize(cur.length);
        for (int i = size - 1; i >= idx; i--) {
            cur[i+1] = cur[i];
        }
        cur[idx] = value;
        size++;
    }

    public T delete(int idx) {
        if (isFull()) throw new NoSuchElementException();
        T item = cur[idx];
        for (int i = idx; i < size-1; i++)
            cur[i] = cur[i + 1];
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        for (int i=0; i<size; i++) {
            System.out.print(cur[i] + "\t");
        }
        System.out.println();
    }
}
