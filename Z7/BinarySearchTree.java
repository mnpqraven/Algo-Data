import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

/**
 * COPIED FROM LAST WEEK, ONLY CHANGES ARE IN main() FOR TIME CALC
 * Represents a binary search tree
 *
 * @param <K> the element type
 */
public class BinarySearchTree<K extends Comparable<K>> {

    /**
     * Represents an inner node of the enclosing search tree
     */
    public class Node {
        /** The value of this node */
        public K value;
        /** The left child of this node */
        public Node left;
        /** The right child of this node */
        public Node right;

        /**
         * Constructs a new leaf node with the given value
         * @param value the value
         */
        public Node(K value) {
            this.value = value;
        }
    }

    /** The tree root */
    public Node root;

    /**
     * 1a
     * @param value
     */
    public void insertion(K value) {
        root = internalAdd(root, value);
    }

    /**
     * Adds the given value to the sub-tree rooted in n
     * @param n the root of the sub-tree
     * @param value the value to insertion
     * @return the (potentially) modified node
     */
    public Node internalAdd( Node n, K value ) {
        if ( n == null )
            return new Node(value);

        int cmp = value.compareTo(n.value);
        if ( cmp < 0 )
            n.left = internalAdd(n.left, value);
        else if ( cmp > 0 )
            n.right = internalAdd(n.right, value);

        return n;
    }

    /**
     * 1a
     * @param value the value to deletion;
     */
    public void deletion(K value) {
        root = internalRemove(root, value);
    }

    /**
     * Removes the given value  fron the sub-tree rooted in n
     * @param n the root of the sub-tree
     * @param value the value to deletion
     * @return the (potentially) modyfied node
     */
    public Node internalRemove( Node n, K value  ) {
        if ( n == null )
            return null;

        int cmp = value.compareTo(n.value);
        // left recursive
        if ( cmp < 0 )
            n.left = internalRemove(n.left, value);
            // right recursive
        else if ( cmp > 0 )
            n.right = internalRemove(n.right, value);
            // left sub-tree empty
        else if ( n.left == null )
            return n.right;
            // right sub-tree empty
        else if ( n.right == null )
            return n.left;
            // has two children
        else {
            removeSymSucc(n);
        }
        return n;
    }

    /**
     * Removes the value of node n by replacing it with its symmetric successor
     * @param n the node holding the value to deletion
     */
    public void removeSymSucc(Node n) {
        Node p = n;
        if (p.right.left != null) {
            p = p.right;
            while (p.left.left != null)
                p = p.left;

            n.value = p.left.value;
            p.left = p.left.right;
        }
        else {
            n.value = n.right.value;
            n.right = n.right.right;
        }
    }

    public String toString() {
        String values = toStringInt(root);
        if ( !values.isEmpty() )
            values = values.substring(1);
        return "BinarySearchTree (" + values + ")";
    }

    public String toStringInt(Node n) {
        if ( n == null )
            return "";
        else
            return toStringInt(n.left) + "," + n.value + toStringInt(n.right);
    }

    /**
     * 1b
     * @param value
     */
    public void search(K value) {
        System.out.println("That element has value " + nodeSearch(root, value).value);
    }

    public Node nodeSearch(Node n, K value) {
        if (n == null) {
            return root;
        } else {
            if (value == n.value) {
                //System.out.println(value + " EQ " + n.value);
                return n;
            } else {
                //System.out.println(value + " NEQ " + n.value);
                if ((Integer) value > (Integer) n.value) {
                    return nodeSearch(n.right, value);
                } else
                    return nodeSearch(n.left, value);
            }
        }
    }

    /**
     * 1c
     */
    public void max () {
        System.out.println("maximum number is "  + max(root).value);
        //max(root);
    }

    public Node max (Node n) {
        int max = (Integer) n.value;
        if (n != null)
            if (n.right != null) {
                if ((Integer) n.right.value > max) {
                    return max(n.right);
                }
            }
        return n;
    }

    public void min () {
        System.out.println("minimum number is "  + min(root).value);
        //max(root);
    }

    public Node min (Node n) {
        int min = (Integer) n.value;
        if (n != null)
            if (n.left != null) {
                if ((Integer) n.left.value < min) {
                    return min(n.left);
                }
            }
        return n;
    }

    /**
     * 1d
     * @param arrayList
     */
    public void toSortedArrayList(ArrayList arrayList) {
        sortArrayList(arrayList, root);
    }

    public ArrayList sortArrayList(ArrayList arrayList, Node n) {
        if ( n != null ) {
            sortArrayList(arrayList, n.left);
            arrayList.add(n.value);
            sortArrayList(arrayList, n.right);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insertion(4);
        tree.insertion(6);
        tree.insertion(2);
        tree.insertion(1);
        tree.insertion(3);
        tree.insertion(5);
        tree.insertion(7);

        System.out.println(tree);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 99999; i++) {
            arrayList.add(i);
        }
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
        for (int i = 0; i < arrayList.size(); i++) {
            tree2.insertion(i);
        }
        //tree.toSortedArrayList(arrayList);

        //for (int i = 0; i < arrayList.size(); i++) {
        //    System.out.print(arrayList.get(i) + ",");
        //}
        //System.out.println("\n max testing");
        //tree.max();
        //tree.min();
        //System.out.println("\n search testing");
        //tree.search(1);

    }
}
