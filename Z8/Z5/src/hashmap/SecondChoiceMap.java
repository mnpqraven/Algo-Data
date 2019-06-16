package hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a map that uses second choice hashing
 *
 * @param <K>
 * @param <V>
 */
public class SecondChoiceMap<K, V> implements Map<K, V> {

    private final List<List<MapEntry<K, V>>> table1;

    private final List<List<MapEntry<K, V>>> table2;

    private final HashFunction<K> h1;

    private final HashFunction<K> h2;

    public final int numBuckets;

    public static void main(String[] args) {
        SecondChoiceMap<Integer, String> mymap = new SecondChoiceMap<>(20, x -> x, x->x*3);
        for (Integer i = 0; i < 15; i++) {
            mymap.put(i, i.toString());
            if (i % 3 == 0) {
                mymap.remove(i);
            }
            System.out.println(mymap.get(i));
        }
    }


    public SecondChoiceMap(int numBuckets, HashFunction<K> h1, HashFunction<K> h2) {
        this.numBuckets = numBuckets;
        this.h1 = h1;
        this.h2 = h2;
        table1 = new ArrayList<>();
        table2 = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            table1.add(new LinkedList<>());
            table2.add(new LinkedList<>());
        }
    }


    @Override
    public V get(K key) {
        int bucket1 = h1.hash(key) % numBuckets;
        int bucket2 = h2.hash(key) % numBuckets;

        List<MapEntry<K, V>> list1 = table1.get(bucket1);
        List<MapEntry<K, V>> list2 = table2.get(bucket2);

        for (MapEntry<K, V> entry : list1) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        for (MapEntry<K, V> entry : list2) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int bucket1 = h1.hash(key) % numBuckets;
        int bucket2 = h2.hash(key) % numBuckets;

        List<MapEntry<K, V>> list1 = table1.get(bucket1);
        List<MapEntry<K, V>> list2 = table2.get(bucket2);

        for (MapEntry<K, V> entry : list1) {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return;
            }
        }
        for (MapEntry<K, V> entry : list2) {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return;
            }
        }
        if (list1.size() > list2.size()) {
            list2.add(new MapEntry<>(key, value));
        } else {
            list1.add(new MapEntry<>(key, value));
        }


    }

    @Override
    public void remove(K key) {
        int bucket1 = h1.hash(key) % numBuckets;
        int bucket2 = h2.hash(key) % numBuckets;

        List<MapEntry<K, V>> list1 = table1.get(bucket1);
        List<MapEntry<K, V>> list2 = table2.get(bucket2);

        for (MapEntry<K, V> entry : list1) {
            if (key.equals(entry.getKey())) {
                list1.remove(entry);
                return;
            }
        }
        for (MapEntry<K, V> entry : list2) {
            if (key.equals(entry.getKey())) {
                list2.remove(entry);
                return;
            }
        }

    }

    public String toString() {
        List<List<MapEntry<K, V>>>[] tables = (List<List<MapEntry<K, V>>>[]) new Object[]{table1, table2};
        String string = "";
        for (List<List<MapEntry<K, V>>> table : tables) {
            for (List<MapEntry<K, V>> bucket : table) {
                string += "[";
                for (MapEntry<K, V> entry : bucket) {
                    string += entry;
                }
                string += "]";
            }
        }
        return string;
    }

}
