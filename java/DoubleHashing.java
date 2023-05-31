public class DoubleHashing<K, V> {
    int M = 13;
    K[] keyList = (K[]) new Object[M];
    V[] valList = (V[]) new Object[M];

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int init = hash(key);
        int idx = init;
        int d = (7 - (int)key % 7);

        for (int cnt=1; cnt<=20; cnt++) {
            if (keyList[idx] == null) {
                keyList[idx] = key;
                valList[idx] = value;
                return;
            }
            if (keyList[idx].equals(key)) {
                valList[idx] = value;
                return;
            }
            idx = (init + cnt * d) % M;
        }
    }

    public V get(K key) {
        int init = hash(key);
        int idx = init;
        int d = (7 - (int)key % 7);

        for (int cnt=1; cnt<=20 && keyList[idx] != null; cnt++) {
            if (keyList[idx].equals(key))
                return valList[idx];
            idx = (init + cnt * d) % M;
        }

        return null;
    }
}
