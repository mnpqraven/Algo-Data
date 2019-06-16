package list;
/**
 * Interface for lists.
 * @param <T> The list's element type.
 */
public interface List<T> extends Iterable<T> {
	
	/**
	 * @return The current size of this list.
	 */
	int getSize();
	
	/**
	 * @return <code>true</code> if this list contains no elements, <code>false</code> otherwise.
	 */
	default boolean isEmpty() {
		return getSize() == 0;
	}

	/**
	 * Appends the given element to this list.
	 * @param elem The element to append.
	 */
	void add(T elem);
	
	
	/**
	 * Retrieves the element at the given position in the list.
	 * @param index The index of the element to retrieve.
	 * @return The element at the given index.
	 * @throws IndexOutOfBoundsException If the given index is negative or exceeds this list's size.
	 */
	T get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Checks if this list contains the given element.
	 * @param elem The element to check.
	 * @return <code>true</code> if this list contains the given element, <code>false</code> otherwise .
	 */
	boolean contains(T elem);
	
	/**
	 * Removes the element at the given position from this list. Shifts any subsequent elements to 
	 * the left (subtracts one from their indices). Returns the element that was removed from the list.
	 * @param index The index of the element to remove.
	 * @throws IndexOutOfBoundsException If the given index is negative or exceeds this list's size.
	 */
	T remove(int index) throws IndexOutOfBoundsException;

}
