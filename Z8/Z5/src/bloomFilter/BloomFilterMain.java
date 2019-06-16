package bloomfilter;

import java.util.Random;

import list.List;
import list.RecursiveList;

public class BloomFilterMain {

    private static final int HASHES = 7;
    private static final int BUCKETS = 50_000;

    private static List<Integer> initBloomFilter(List<Integer> list){
    	return new BloomFilterList<>(list, new BloomFilterImpl<>(new IntegerFactory(), HASHES, BUCKETS));
    }

    public static void main(String[] args) {
        List<Integer> list = new RecursiveList<>();
        List<Integer> bloomList = initBloomFilter(list);

        Random rnd = new Random(1337);

        for(int i = 0; i < 5_000; i++){
            bloomList.add(rnd.nextInt(100_000));
        }

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100_000; i++){
            bloomList.contains(rnd.nextInt(100_000));
        }
        long endTime = System.currentTimeMillis();

        System.out.println("BloomFilter Time: " + (endTime-startTime));

        startTime = System.currentTimeMillis();
        for(int i = 0; i < 100_000; i++){
            list.contains(rnd.nextInt(100_000));
        }
        endTime = System.currentTimeMillis();

        System.out.println("List Time: " + (endTime-startTime));

    }
}