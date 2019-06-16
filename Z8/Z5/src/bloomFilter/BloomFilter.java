package bloomfilter;

/**
 * This interface represents a Bloom Filter
 */
public interface BloomFilter<E> {

    /**
     * Inserts an element into the Bloom Filter.
     *
     * @param element
     */
    void add(E element);

    /**
     * If the method returns false, the specified element was definitely not inserted into the BloomFilter.
     * If the method returns true, there is a chance, that the element was not inserted into the BloomFilter.
     * @param element - element whose presence in this BloomFilter is to be tested
     * @return false if the specified element was definitely not inserted into the BloomFilter; true otherwise
     */
    boolean contains(E element);

    /**
     * Removes all of the elements from this BloomFilter.
     */
    void empty();

}
