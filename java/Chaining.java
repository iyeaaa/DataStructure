public class Chaining<K, V> {
    public static class Node {
        private Object key;
        private Object data;
        public Node next;

        public Node(Object newKey, Object newData, Node ref) {
            key = newKey;
            data = newData;
            next = ref;
        }

        public Object getKey() {
            return key;
        }

        public Object getData() {
            return data;
        }
    }

    public int M = 13;
    public Node [] a = new Node[M];

    int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int hashed = hash(key);

        for (Node x = a[hashed]; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.data = value;
                return;
            }

        // 같은 key 없는경우 맨 앞에 insert
        a[hashed] = new Node(key, value, a[hashed]);
    }

    public V get(K key) {
        int hashed = hash(key);

        for (Node x = a[hashed]; x != null; x = x.next)
            if (key.equals(x.key)) {
                return (V) x.data;
            }

        return null;
    }
}

