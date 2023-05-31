import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    public Node<Key, Value> root;
    public Node<Key, Value> getRoot() { return root; }
    public BST(Key newId, Value newName) {
        root = new Node<>(newId, newName);
    }

    public Value get(Key k) { return get(root, k); }
    public Value get(Node<Key, Value> node, Key k) {
        if (node == null) return null;
        int t = node.getKey().compareTo(k);
        if (t < 0) get(node.getRight(), k);
        else if (t > 0) get(node.getLeft(), k);
        return node.getValue();
    }

    public void put(Key k, Value v) { root = put(root, k, v); }
    public Node<Key, Value> put(Node<Key, Value> n, Key k, Value v) {
        if (n == null) return new Node<>(k, v);
        int t = k.compareTo(n.getKey());
        if (t < 0) n.setLeft(put(n.getLeft(), k, v));
        else if (t > 0) n.setRight(put(n.getRight(), k, v));
        else n.setValue(v);
        return n;
    }

    public void deleteMin() {
        if (root == null) System.out.println("empty 트리");
        root = deleteMin(root);
    }
    public Node<Key, Value> deleteMin(Node<Key, Value> n) {
        if (n.getLeft() == null) return n.getRight();
        n.setLeft(deleteMin(n.getLeft()));
        return n;
    }

    public Node<Key, Value> min(Node<Key, Value> n) {
        if (n.getLeft() == null) return n;
        return min(n.getLeft());
    }

    public void delete(Key k) { root = delete(root, k); }
    public Node<Key, Value> delete(Node<Key, Value> n, Key k) {
        if (n == null) return null;
        int t = n.getKey().compareTo(k);
        if (t > 0) n.setLeft(delete(n.getLeft(), k));
        else if (t < 0) n.setRight(delete(n.getRight(), k));
        else {
            if (n.getLeft() == null) return n.getRight();
            if (n.getRight() == null) return n.getLeft();
            Node<Key, Value> target = n;
            n = min(target.getRight());
            n.setRight(deleteMin(target.getRight()));
            n.setLeft(target.getLeft());
        }
        return n;
    }

    public void print(Node<Key, Value> root) {
        Queue<Node<Key, Value>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<Key, Value> cur = q.remove();
            if (cur.getLeft() != null)
                q.add(cur.getLeft());
            if (cur.getRight() != null)
                q.add(cur.getRight());
            System.out.print(cur.getValue() + "\t");
        }
    }
}
