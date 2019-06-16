package bloomfilter;

public class IntegerFactory implements HashFunctionFactory<Integer> {
    @Override
    public HashFunction<Integer>[] generate(int numberOfHashFunctions) {
        HashFunction<Integer> h1 = new HashFunction<Integer>() {
            @Override
            public int hash(Integer element) {
                return (17*element);
            }
        };

        HashFunction<Integer> h2 = new HashFunction<Integer>() {
            @Override
            public int hash(Integer element) {
                return (31*element);
            }
        };

        @SuppressWarnings("unchecked")
		HashFunction<Integer>[] result =  new HashFunction[numberOfHashFunctions];

        for(int i = 0; i < numberOfHashFunctions; i++){
            final int j = i;
            result[i] = new HashFunction<Integer>() {

                @Override
                public int hash(Integer element) {
                    return (h1.hash(element) + j*h2.hash(element));
                }
            };

        }
        return result;
    }
}
