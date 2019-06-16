package bloomfilter;

/**
 * Interface proves a method to hash an Object of type E
 *
 * @param <E> the Type to hash in the hash method
 */
public interface HashFunction<E> {

    /**
     * computes the hash value of object
     * @param e the object for which to calculate the hash value
     * @return the hash value of e
     */
    int hash(E e);

}
