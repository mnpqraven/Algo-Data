package list;

import java.util.Iterator;

/**
 * Created by seeger_l on 30.04.2016.
 */
public class RecursiveList<E> implements List<E>, Iterable<E> {

    private static class ListNode<T> {
        private T item;
        private ListNode<T> next;

        /**
         * constructor to build a node with no successor
         * @param element the value to be stored by this node
         */
        private ListNode(T element) {
            item = element;
            next = null;
        }

        /**
         * constructor to build a node with specified (maybe null) successor
         * @param element the value to be stored by this node
         * @param reference the next field for this node
         */
        private ListNode(T element, ListNode<T> reference) {
            item = element;
            next = reference;
        }
    }

    // this is the start of the linked list.  If the list is empty, it is null
    private ListNode<E> head = null;

    /** recursive private method, called by the public wrapper method
     * @param current the head of the list (may be null if we are at the end)
     * @return the size of the list
     */
    private int size(ListNode<E> current) {
        if (current == null) {
            return 0;  // an empty list has size 0
        }  // a non-empty list has size 1 more than the rest of the list:
        return 1 + size (current.next);
    }

    @Override
    public int getSize() {
        return size(head);
    }

    @Override
    public void add(E element) throws IndexOutOfBoundsException {
        head = new ListNode<E>(element, head);
    }

    /** recursive private method, called by the public wrapper method
     * @param node the head of the list (may be null if we are at the end)
     * @param index the number of nodes to skip before removing
     * @return the list, with the value removed
     */
    private ListNode<E> remove(ListNode<E> node, int index) {
        // Precondition: element exists
        if (index == 0) {
            return node.next;  // match, so remove this node
        }
        node.next = remove(node.next, index-1);
        return node;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        E element = get(index);         //  a bit of inefficiency, but only a factor of 2
        head = remove(head, index);
        return element;
    }

    /**
     *
     * @param node the head of the list, maybe null
     * @param element the value to be searched
     * @return search is succesful
     */
    private boolean contains(ListNode<E> node, E element){
        if (node == null) return false;
        return node.item.equals(element) || contains(node.next, element);
    }

    @Override
    public boolean contains(E element) {
        return contains(head, element);
    }

    /**
     * recursive private method to get the node at a position
     * @param node the head of the list, maybe null
     * @param index the position in the list
     * @return the lement found at the position
     * @throws IndexOutOfBoundsException
     */
    private E get(ListNode<E> node, int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            if (node == null) throw new IndexOutOfBoundsException();
            return node.item;
        }
        else {
            return get(node.next, index - 1);
        }
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return get(head, index);
    }


    /** recursive private method, called by the public wrapper method
     * @param node the head of the list (may be null if we are at the end)
     * @return the string representing the list
     */
    private String toString(ListNode<E> node) {
        if (node == null) {
            return "";
        }
        if (node.next == null) {
            return node.item.toString();
        }
        return node.item.toString() + " -> " + toString(node.next);
    }

    @Override
    public String toString() {
        return toString(head);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            ListNode<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E element = node.item;
                node = node.next;
                return element;
            }
        };
    }
}