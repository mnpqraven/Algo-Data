package bloomfilter;

public class BloomFilterImpl<E> implements BloomFilter<E>{

    private boolean[] filter;
    private HashFunction<E>[] functions;
    private int filterSize;

    public BloomFilterImpl(HashFunctionFactory<E> factory, int numberOfHashFunctions, int filterSize){
    	this.filterSize = filterSize;
    	functions = factory.generate(numberOfHashFunctions);
        filter = new boolean[filterSize];
        
    }
    
    private int getAddress(E e, int i) {
    	int hash = functions[i].hash(e) % filterSize;;
    	if ( hash < 0 ) hash += filterSize;
    	return hash;
    }

    public void add(E element){
        for(int i = 0; i<functions.length; i++){
            filter[getAddress(element, i)] = true;
        }

    }

    public boolean contains(E element){
        boolean result = true;
        for(int i = 0; i<functions.length; i++)
            result &= filter[getAddress(element, i)];
        return result;
    }

    public void empty(){
        for(int i = 0; i< filter.length; i++)
            filter[i] = false;
    }


}
