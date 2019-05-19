import java.util.ArrayList;

public class PriorityQueueMaxHeap<K extends Comparable<K>> implements PriorityQueue {
    ArrayList<K> al = new ArrayList<K>();

    //Constructor
    PriorityQueueMaxHeap(K[] al) {
        for (int i =0; i < al.length; i++){
            this.al.add(al[i]);
            for(int k = al.length/2; k>= 0; k--){
                heapify(k);
            }
        }
    }

    public void heapify(int i) {
        int left = i;     //1st child
        int right = i+1;  //the other child
        int peak = 0;       //parent

        if (left < al.size() && al.get(left).compareTo(al.get(i)) > 0 ) {
            peak = left;
        } else {
            peak = i; //VL page 55
        }
        if (left < al.size() && al.get(right).compareTo(al.get(peak)) > 0 ) {
            peak = right;
        }

        if (peak != i) {
        K temp = al.get(i); //prepare to swap i and peak
        al.add(i, al.get(peak));
        al.add(peak, temp); //swap in
        heapify(peak);
        }
    }

    public void addElement(Comparable element) {
        al.add(0, (K) element);
        al.add(al.get(0));
        heapify(0);
    }

    public K getFirst() {
        return al.get(0);
    }

    public void deleteFirst() {
        al.remove(0);   //squash the first element
        heapify(0);     //heapify again to not violate the heap ordering
    }
}
