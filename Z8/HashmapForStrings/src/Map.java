import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Class to represent entire hash table
class Map<K, V> {
	// bucketArray is used to store array of chains
	private ArrayList<HashNode<K, V>> bucketArray;

	// Current capacity of array list
	private int numBuckets;

	// Current size of array list
	private int size;

	// Constructor (Initializes capacity, size and
	// empty chains.
	public Map(int numBuckets)
	{
		bucketArray = new ArrayList<>();
		this.numBuckets = numBuckets;
		size = 0;

		// Create empty chains
		for (int i = 0; i < numBuckets; i++)
			bucketArray.add(null);
	}

	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size() == 0;
	}

	// This implements hash function to find index
	// for a key
	private int getBucketIndex(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return index;
	}

	// Method to remove a given key
	public V remove(K key) {
		// Apply hash function to find index for given key
		int bucketIndex = getBucketIndex(key);

		// Get head of chain
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Search for key in its chain
		HashNode<K, V> prev = null;
		while (head != null) {
			// If Key found
			if (head.key.equals(key))
				break;

			// Else keep moving in chain
			prev = head;
			head = head.next;
		}

		// If key was not there
		if (head == null)
			return null;
		// Reduce size
		size--;
		// Remove key
		if (prev != null) {
			prev.next = head.next;
		} else bucketArray.set(bucketIndex, head.next);
		return head.value;
	}

	public V get(K key)	{
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		HashNode<K, V> head = bucketArray.get(bucketIndex);
		// Search key in chain
		while (head != null) {
			if (head.key.equals(key))
				return head.value;
			head = head.next;
		}
		// If key not found
		return null;
	}

	public K search(V value) { //K starting point, use index 0 to search whole map
		try {
			for (int i = 0; i < size; i++) { //bucketArray traverse
				HashNode<K, V> head = bucketArray.get(i);
				// Search value in chain
				while (head != null) {
					if (head.value.equals(value))
						return head.key;
					head = head.next;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		// If value not found
		return null;
	}

	// Adds a key value pair to hash
	public void add(K key, V value)	{
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Check if key is already present
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// Insert key in chain
		size++;
		head = bucketArray.get(bucketIndex);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);
	}

	// Driver method to test Map class
	public static void main(String[] args) throws IOException {
		/**
		 * 1b
		 */
		Map<Integer, String> map1000 = new Map<>(1000);
		Map<Integer, String> map10000 = new Map<>(10000);

		/**
		 * STRING BUILDER PART
		 */
		String filename = "OfficialScrabbleWordListGerman.txt";
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		int linecount = 1;
		long begin = System.nanoTime();
		while ((line = reader.readLine()) != null) {
		    //comment out 1 of these 2 lines when calculating runtime
			map1000.add(linecount, line);
			//map10000.add(linecount, line);
			linecount++;
		}
		long end = System.nanoTime();
		/** 1d*/
		System.out.println("Took "+ (end-begin)); // 1035920776 for 1000 elements,
		 										  // 64634818 for 10000 elements
		reader.close();

		/**
		 * 1c
		 */
		String filename2 = "AreMyFriendsCheating.txt";
		BufferedReader reader2 = new BufferedReader(new FileReader(filename));
		StringBuilder stringBuilder2 = new StringBuilder();
		String line2 = null;
		int linecount2 = 1;
		int fail = 0;
		begin = System.nanoTime();
		while ((line2 = reader2.readLine()) != null) {
			if (map1000.search(line2) == null) {
				System.out.println("FAILED SEARCH");
				fail++;
			}
			linecount2++;
		}
		System.out.println(fail + " failed searches"); //0 failed search
		end = System.nanoTime();
		/** 1d*/
		System.out.println("Took " +(end-begin)); //Took 975354383604
		reader2.close();

		/** map display*/
		//for (int i = 1; i <= map1000.size(); i++) {
		//	System.out.print(map1000.get(i) + " ");
		//}

	}
}


