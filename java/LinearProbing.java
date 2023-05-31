public class LinearProbing<K, V> {
    public int M = 13;
    public K[] keyList = (K[]) new Object[M];
    public V[] valList = (V[]) new Object[M];

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int initIdx = hash(key);
        int idx = initIdx;

        do {
            if (keyList[idx] == null) {
                keyList[idx] = key;
                valList[idx] = value;
                return;
            }
            if (keyList[idx].equals(key)) {
                valList[idx] = value;
                return;
            }
            idx = (idx + 1) % M;
        } while (idx != initIdx);
    }

    public V get(K key) {
        int idx = hash(key);
        while (keyList[idx] != null) {
            if (keyList[idx].equals(key)) {
                return valList[idx];
            }
            idx = (idx + 1) % M;
        }
        return null;
    }
}
