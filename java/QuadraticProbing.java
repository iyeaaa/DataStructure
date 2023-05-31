public class QuadraticProbing<K, V> {
    int M = 13;
    K[] keyList = (K[]) new Object[M];
    V[] valList = (V[]) new Object[M];

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int init = hash(key);
        int idx = init;
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
            idx = (init + cnt*cnt) % M;
        }
    }

    public V get(K key) {
        int init = hash(key);
        int idx = init;
        for (int cnt = 0; cnt < 20; cnt++) {
            if (keyList[idx] == null) return null;
            if (keyList[idx].equals(key))
                return valList[idx];
            idx = (init + cnt*cnt) % M;
        }
        return null;
    }
}
