public class AVL<Key extends Comparable<Key>, Value> {
    Node root;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int tallerHeight(int h1, int h2) {
        return h1 > h2 ? h1 : h2;
    }

    private int bf(Node n) {
        return height(n.left) - height(n.right);
    }

    private Node rotateLeft(Node n) {
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        x.height = tallerHeight(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotateRight(Node n) {
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        x.height = tallerHeight(height(x.left), height(x.right)) + 1;
        return x;
    }

    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    private Node put(Node n, Key k, Value v) {
        if (n == null) return new Node(1, k, v); // base case

        int t = n.key.compareTo(k);
        if (t > 0) n.left = put(n.left, k, v);
        else if (t < 0) n.right = put(n.right, k, v);
        else { n.value = v; return n; }

        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        return balance(n);
    }

    private Node balance(Node n) {
        if (bf(n) > 1) {
            if (bf(n.left) < 0) n.left = rotateLeft(n.left);
            n = rotateRight(n);
        } else if (bf(n) < -1) {
            if (bf(n.right) > 0) n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }

    private void preorder(Node n) {
        if (n == null) return;
        System.out.print(n.key + " ");
        preorder(n.left);
        preorder(n.right);
    }

    public void print() {
        preorder(root);
    }
}
