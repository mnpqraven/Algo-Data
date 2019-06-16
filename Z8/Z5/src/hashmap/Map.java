package hashmap;

/**
 * Interface for a simplistic Map
 *
 * @param <K> the type of the Key
 * @param <V> the type of the Value
 */
public interface Map<K,V> {
	
	/**
	 * get the value for given key
	 * @param key
	 * @return the value for the given key, null if it doesn't exist
	 */
	V get(K key);
	
	/**
	 * put the given key value pair into the map, replaces any element with the same key
	 * @param key
	 * @param value
	 */
	void put(K key, V value);
	
	/**
	 * remove the key value pair with the given key
	 * @param key
	 */
	void remove(K key);

}
