public interface PriorityQueue<K extends Comparable<K>> {
     public void addElement(K elem);
     public K getFirst();
     public void deleteFirst();
}
