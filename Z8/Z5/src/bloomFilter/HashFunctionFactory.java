package bloomfilter;

public interface HashFunctionFactory<E> {

    HashFunction<E>[] generate(int numberOfHashFunctions);

}
