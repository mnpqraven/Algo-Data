// Java program to demonstrate implementation of our
// own hash table with chaining for collision detection
import java.util.ArrayList;

// A node of chains
class HashNode<K, V>
{
	K key;
	V value;

	// Reference to next node
	HashNode<K, V> next;

	// Constructor
	public HashNode(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
}
