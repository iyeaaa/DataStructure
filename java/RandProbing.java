import java.util.Random;

public class RandProbing<K, V> {
    int M = 13;
    K[] keyList = (K[]) new Object[M];
    V[] valList = (V[]) new Object[M];

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int init = hash(key);
        int idx = init;
        Random rand = new Random(10);
        for (int cnt=0; cnt<20; cnt++) {
            if (keyList[idx] == null) {
                keyList[idx] = key;
                valList[idx] = value;
                return;
            }
            if (keyList[idx].equals(key)) {
                valList[idx] = value;
                return;
            }
            idx = (init + rand.nextInt(1000)) % M;
        }
    }

    public V get(K key) {
        int init = hash(key);
        int idx = init;
        Random rand = new Random(10);
        for (int cnt=0; cnt<20 && keyList[idx] != null; cnt++) {
            if (keyList[idx].equals(key)) return valList[idx];
            idx = (init + rand.nextInt(1000)) % M;
        }
        return null;
    }
}
