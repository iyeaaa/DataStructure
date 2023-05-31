import java.util.NoSuchElementException;

public class MyArrayQueue<T> {
    int MAX = 1000;
    int front;
    int rear;
    int size;
    T[] q;

    public MyArrayQueue() {
        front = rear = size = 0;
        q = (T[]) new Object[MAX];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == MAX - 1;
    }

    public int size() {
        return front - rear;
    }

    public void add(T value) {
        if ((rear + 1) % q.length == front)
            resize(2 * q.length);
        rear = (rear + 1) % q.length;
        q[rear] = value;
        size++;
    }

    private void resize(int newSize) {
        T[] t = (T[]) new Object[newSize];
        for (int i = 1, j = front + 1; i < size + 1; i++, j++)
            t[i] = q[j % q.length];
        front = 0;
        rear = size;
        q = (T[]) t;
    }

    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        front = (front + 1) % q.length;
        T item = q[front];
        q[front] = null;
        size--;
        if (size > 0 && size == q.length / 4)
            resize(q.length / 2);
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return q[(front+1) % q.length];
    }

    public void print() {
        for (int i=front+1; i<front+1+size; i++)
            System.out.print(q[i % q.length] + "\t");
        System.out.println();
    }
}