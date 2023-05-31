import java.util.EmptyStackException;

public class MyArrayStack<T> {
    private T[] cur;
    public int top;

    private boolean isFull() {
        return top == cur.length;
    }

    public MyArrayStack() {
        cur = (T[]) new Object[1];
        top = -1;
    }

    public int size() {
        return top+1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return cur[top];
    }

    void resize(int size) {
        Object[] nxt = new Object[size];
        for (int i = 0; i <= top; i++)
            nxt[i] = cur[i];
        cur = (T[]) nxt;
    }

    public void push(T value) {
        if (size() == cur.length)
            resize(size()*2);
        cur[++top] = value;
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        T item = cur[top];
        cur[top--] = null;
        if (size() > 0 && size() == cur.length / 4)
            resize(cur.length / 2);
        return item;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }
        for (int i = 0; i<= top; i++) {
            System.out.print(cur[i] + "\t");
        }
        System.out.println();
    }
}
