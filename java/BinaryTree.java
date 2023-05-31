
public class BinaryTree<T extends Comparable<T>> {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node<T> rt) {
        root = rt;
    }

    public int size(Node<T> x) {
        return x == null ? 0 : 1 + size(x.getLeft()) + size(x.getRight());
    }

    public int height(Node<T> x) {
        return x == null ? 0 : Math.max(height(x.getLeft()) + 1, height(x.getRight()) + 1);
    }

    public void preorder(Node<T> x) {
        if (x == null) return;
        System.out.print(x.getKey() + " ");
        preorder(x.getLeft());
        preorder(x.getRight());
    }

    public void inorder(Node<T> x) {
        if (x == null) return;
        inorder(x.getLeft());
        System.out.print(x.getKey() + " ");
        inorder(x.getRight());
    }

    public void postorder(Node<T> x) {
        if (x == null) return;
        postorder(x.getLeft());
        postorder(x.getRight());
        System.out.print(x.getKey() + " ");
    }

    public void levelorder(Node<T> x) {
        ArrayQueue<Node<T>> q = new ArrayQueue<>();
        q.add(x);
        while (!q.isEmpty()) {
            Node<T> cur = q.remove();
            System.out.print(cur.getKey() + " ");
            if (cur.getLeft() != null)
                q.add(cur.getLeft());
            if (cur.getRight() != null)
                q.add(cur.getRight());
        }
    }

    public static boolean isEqual(Node x, Node y) {
        if (x == null || y == null)
            return x == y;

        if (x.getKey().compareTo(y.getKey()) != 0)
            return false;

        return isEqual(x.getLeft(), y.getLeft()) && isEqual(x.getRight(), y.getRight());
    }
}
