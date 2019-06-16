package hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a map that uses linear hashing to expand the underlying hash table
 *
 * @param <K>
 * @param <V>
 */
/*
 * Schöne Lösung.
 */
public class LinearHashingMap<K, V> implements Map<K, V> {

    private final int initialBucketCount;

    private final double alphaMax;

    private final List<List<MapEntry<K, V>>> buckets;

    private final HashFunction<K> hashFunction;

    private int currentLevel = 0;

    private int expansionPointer = 0;

    private int numberOfElements;

    public static void main(String[] args) {
    	/*
    	 * Super Hashfunktion :)
    	 */
        LinearHashingMap<Integer, String> mymap = new LinearHashingMap<>(3, 0.8, x -> x);
        for (Integer i = 0; i < 15; i++) {
            mymap.put(i, i.toString());
            System.out.println(mymap.getAlpha());
            if (i % 3 == 0) {
                mymap.remove(i);
            }
            System.out.println(mymap.get(i));
        }
    }


    public LinearHashingMap(final int initialBucketCount, double alphaMax, HashFunction<K> h) {
        this.initialBucketCount = initialBucketCount;
        this.alphaMax = alphaMax;

        buckets = new ArrayList<>();

        for (int i = 0; i < initialBucketCount; i++)
            buckets.add(i, new LinkedList<>());

        hashFunction = h;

        numberOfElements = 0;
    }

    /**
     * get the address for the given key with respect to current level
     *
     * @param key
     * @return the address for the given key with respect to current level
     */
    
    public int getAddress(K key) {
        int address = hashFunction.hash(key) % (initialBucketCount* (1 << currentLevel+1));
        if (address > initialBucketCount* (1 << currentLevel) + expansionPointer - 1) {
            return address % (initialBucketCount * (1 << currentLevel));
        }
        
        return address;
    }

    /**
     * get the current alpha value
     *
     * @return the current alpha value
     */
    public double getAlpha() {

        return (double) numberOfElements / ((1 << currentLevel) * initialBucketCount + expansionPointer);
    }

    /**
     * check if number of elements in hash table exceeds threshold
     *
     * @return true if the hash table needs to be extended
     */
    public boolean checkOverflow() {
        return alphaMax <= getAlpha();
    }

    /**
     * expands the hash table
     */
    protected void split() {
        LinkedList<MapEntry<K, V>> newList = new LinkedList<>();
        buckets.add(newList);
        for (MapEntry<K, V> entry : buckets.get(expansionPointer)) {

            if (hashFunction.hash(entry.getKey()) % (initialBucketCount * (1 << currentLevel + 1)) != expansionPointer) {
                buckets.get(expansionPointer).remove(entry);
                newList.add(entry);
            }
        }
        expansionPointer += 1;
        if (expansionPointer == (1<< currentLevel) * initialBucketCount) {
            currentLevel += 1;
            expansionPointer = 0;
        }

    }

    public V get(K key) {
        int bucket = getAddress(key);
        for (MapEntry<K, V> entry : buckets.get(bucket)) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }


    public void put(K key, V value) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                next.setValue(value);
                return;
            }
        }

        buckets.get(getAddress(key)).add(new MapEntry<>(key, value));
        numberOfElements++;
        
        while (checkOverflow()) {
            split();
        }
    }


    public void remove(K key) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                iterator.remove();
                numberOfElements--;
                break;
            }
        }

    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (List<MapEntry<K, V>> bucket : buckets) {
            string.append("[");
            for (MapEntry<K, V> entry : bucket) {
                string.append(entry);
            }
            string.append("]");
        }
        return string.toString();
    }

}
