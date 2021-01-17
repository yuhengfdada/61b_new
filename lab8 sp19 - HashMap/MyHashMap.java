import java.util.*;


public class MyHashMap<K, V> implements Map61B<K, V> {
    private static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    Set<K> keys = new HashSet<>();
    int size = 16; // number of buckets, not keys (a bad name, I admit)
    double loadFactor = 0.75;
    /* Important: An array of LinkedLists */
    ArrayList<LinkedList<Pair<K, V>>> buckets;

    private void initBuckets() {
        buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    public MyHashMap() {
        initBuckets();
    }
    public MyHashMap(int initialSize) {
        size = initialSize;
        initBuckets();
    }
    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        this.loadFactor = loadFactor;
        initBuckets();
    }


    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        keys.clear();
        buckets.clear();
    }
    @Override
    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keys.contains(key);
    }
    @Override
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (containsKey(key)) {
            int bucketNum = (key.hashCode() & 0x7FFFFFFF) % size;
            for (Pair<K, V> i : buckets.get(bucketNum)) {
                if (i.key.equals(key)) return i.value;
            }
        }
        return null;
    }
    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return keys.size();
    }

    /* Creates a new set of buckets and re-hash every K-V pair. */
    private void resize() {
        ArrayList<LinkedList<Pair<K, V>>> newBuckets = new ArrayList<>(size * 2);
        for (int i = 0; i < size * 2; i++) {
            newBuckets.add(new LinkedList<>());
        }
        /* Approach 1: get(key) and put(key). Not efficient in get()
        since it computes hashcode and goes through each LL over and over again.

        for (K key : keys) {
            V value = get(key);
            computeBucketNumber;
            put(pair);
        }
         */
        /* Approach 2: just go over the original hash table. */
        for (LinkedList<Pair<K, V>> ll : buckets) {
            for (Pair<K, V> p : ll) {
                K key = p.key;
                int newBucketNum = (key.hashCode() & 0x7FFFFFFF) % (size * 2);
                newBuckets.get(newBucketNum).add(p);
            }
        }
        /* Update metadata. */
        size *= 2;
        buckets = newBuckets;
    }



    @Override
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (containsKey(key)) {
            findPair(key).value = value;
        } else {
            if ((size() + 1) / Double.valueOf(size) > loadFactor) {
                resize();
            }
            int bucketNum = (key.hashCode() & 0x7FFFFFFF) % size;
            buckets.get(bucketNum).add(new Pair<K, V>(key, value));
            keys.add(key);
        }
    }
    @Override
    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
    @Override
    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /* HELPER FUNCTIONS */
    /* Assume key exists. */
    private Pair<K, V> findPair(K key) {
        int bucketNum = key.hashCode() % size;
        for (Pair<K, V> i : buckets.get(bucketNum)) {
            if (i.key.equals(key)) return i;
        }
        return null;
    }
    /**
    private class MyHashMapIterator implements Iterator<K> {
        @Override
        public boolean hasNext() {
            return keys.
        }
        @Override
        public K next() {

        }
    }*/

}
