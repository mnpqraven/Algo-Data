package hashmap;

/**
 * 
 * This class encapsulates a key value pair
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class MapEntry<K, V> {

	private K key;
	private V value;

	/**
	 * create a new key value pair
	 * @param key
	 * @param value
	 */
	public MapEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * get the key
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * set the key
	 * @param key
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * get the value
	 * @return value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * set the value
	 * @param value
	 */
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MapEntry
				&& ((MapEntry<?, ?>) obj).getKey().equals(key);
	}
	
	public String toString(){
		return "<" + key + ", " + value + ">";
	}

}
