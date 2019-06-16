import java.util.Random;
import java.util.ArrayList;

public class RBTree<K extends Comparable<K>> {

    // NIL as per vorlesung
	private RBNode<K> nil = new RBNode<K>();
	private RBNode<K> root = nil;

    public RBTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

	/**
	 * basic leftRotate and rightRotate with help method left/rightRotateFix to rebalance/fix tree
	 * @param x
	 */
	private void leftRotate(RBNode<K> x){

		// Call leftRotateFixup() which updates the numLeft
		// and numRight values.
		leftRotateFix(x);

		// Perform the left rotate as described in the algorithm
		// in the course text.
		RBNode<K> y;
		y = x.right;
		x.right = y.left;

		// Check for existence of y.left and make pointer changes
		if (!isNil(y.left))
			y.left.parent = x;
		y.parent = x.parent;

		// x's parent is nul
		if (isNil(x.parent))
			root = y;

		// x is the left child of it's parent
		else if (x.parent.left == x)
			x.parent.left = y;

		// x is the right child of it's parent.
		else
			x.parent.right = y;

		// Finish of the leftRotate
		y.left = x;
		x.parent = y;
	}

	private void rightRotate(RBNode<K> y){
		// Call rightRotateFixup to adjust numRight and numLeft values
		rightRotateFix(y);
		// Perform the rotate as described in the course text.
		RBNode<K> x = y.left;
		y.left = x.right;
		// Check for existence of x.right
		if (!isNil(x.right))
			x.right.parent = y;
		x.parent = y.parent;

		// y.parent is nil
		if (isNil(y.parent))
			root = x;
			// y is a right child of it's parent.
		else if (y.parent.right == y)
			y.parent.right = x;
			// y is a left child of it's parent.
		else
			y.parent.left = x;
		x.right = y;

		y.parent = x;

	}

	private void leftRotateFix(RBNode x){
		// Case 1: Only x, x.right and x.right.right always are not nil.
		if (isNil(x.left) && isNil(x.right.left)){
			x.numLeft = 0;
			x.numRight = 0;
			x.right.numLeft = 1;
		}
		// Case 2: x.right.left also exists in addition to Case 1
		else if (isNil(x.left) && !isNil(x.right.left)){
			x.numLeft = 0;
			x.numRight = 1 + x.right.left.numLeft +
					x.right.left.numRight;
			x.right.numLeft = 2 + x.right.left.numLeft +
					  x.right.left.numRight;
		}
		// Case 3: x.left also exists in addition to Case 1
		else if (!isNil(x.left) && isNil(x.right.left)){
			x.numRight = 0;
			x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;
		}
		// Case 4: x.left and x.right.left both exist in addtion to Case 1
		else{
			x.numRight = 1 + x.right.left.numLeft +
				     x.right.left.numRight;
			x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
			x.right.left.numLeft + x.right.left.numRight;
		}

	}
	private void rightRotateFix(RBNode y){
		// Case 1: Only y, y.left and y.left.left exists.
		if (isNil(y.right) && isNil(y.left.right)){
			y.numRight = 0;
			y.numLeft = 0;
			y.left.numRight = 1;
		}
		// Case 2: y.left.right also exists in addition to Case 1
		else if (isNil(y.right) && !isNil(y.left.right)){
			y.numRight = 0;
			y.numLeft = 1 + y.left.right.numRight +
				  y.left.right.numLeft;
			y.left.numRight = 2 + y.left.right.numRight +
				  y.left.right.numLeft;
		}
		// Case 3: y.right also exists in addition to Case 1
		else if (!isNil(y.right) && isNil(y.left.right)){
			y.numLeft = 0;
			y.left.numRight = 2 + y.right.numRight +y.right.numLeft;

		}
		// Case 4: y.right & y.left.right exist in addition to Case 1
		else{
			y.numLeft = 1 + y.left.right.numRight +
				  y.left.right.numLeft;
			y.left.numRight = 3 + y.right.numRight +
				  y.right.numLeft +
			y.left.right.numRight + y.left.right.numLeft;
		}

	}

	/**
	 * 1a insertion and deletion, both also needs help methods to rebalance/fix tree after insertion/deletion
	 * @param key
	 */
    public void insert(K key) {
        insert(new RBNode<K>(key));
    }

	private void insert(RBNode<K> z) {
			// Create a reference to root & initialize a node to nil
			RBNode<K> y = nil;
			RBNode<K> x = root;

			// While we haven't reached a the end of the tree keep
			// trying to figure out where z should go
			while (!isNil(x)){
				y = x;

				// if z.key is < than the current key, go left
				if (z.key.compareTo(x.key) < 0){

					// Update x.numLeft as z is < than x
					x.numLeft++;
					x = x.left;
				}

				// else z.key >= x.key so go right.
				else{

					// Update x.numGreater as z is => x
					x.numRight++;
					x = x.right;
				}
			}
			// y will hold z's parent
			z.parent = y;

			// Depending on the value of y.key, put z as the left or
			// right child of y
			if (isNil(y))
				root = z;
			else if (z.key.compareTo(y.key) < 0)
				y.left = z;
			else
				y.right = z;

			// Initialize z's children to nil and z's color to red
			z.left = nil;
			z.right = nil;
			z.color = RBNode.RED;

			// Call insertFixup(z)
			insertFix(z);

	}

	public void remove(RBNode<K> v){

		RBNode<K> z = search(v.key);

		// Declare variables
		RBNode<K> x = nil;
		RBNode<K> y = nil;

		// if either one of z's children is nil, then we must remove z
		if (isNil(z.left) || isNil(z.right))
			y = z;

			// else we must remove the successor of z
		else y = treeSuccessor(z);

		// Let x be the left or right child of y (y can only have one child)
		if (!isNil(y.left))
			x = y.left;
		else
			x = y.right;

		// link x's parent to y's parent
		x.parent = y.parent;

		// If y's parent is nil, then x is the root
		if (isNil(y.parent))
			root = x;

			// else if y is a left child, set x to be y's left sibling
		else if (!isNil(y.parent.left) && y.parent.left == y)
			y.parent.left = x;

			// else if y is a right child, set x to be y's right sibling
		else if (!isNil(y.parent.right) && y.parent.right == y)
			y.parent.right = x;

		// if y != z, trasfer y's satellite data into z.
		if (y != z){
			z.key = y.key;
		}

		// Update the numLeft and numRight numbers which might need
		// updating due to the deletion of z.key.
		fixNodeData(x,y);

		// If y's color is black, it is a violation of the
		// RedBlackTree properties so call removeFix()
		if (y.color == RBNode.BLACK)
			removeFix(x);
	}// end remove(RBNode z)

	private void insertFix(RBNode<K> z){

		RBNode<K> y = nil;
		// While there is a violation of the RedBlackTree properties..
		while (z.parent.color == RBNode.RED){

			// If z's parent is the the left child of it's parent.
			if (z.parent == z.parent.parent.left){

				// Initialize y to z 's cousin
				y = z.parent.parent.right;

				// Case 1: if y is red...recolor
				if (y.color == RBNode.RED){
					z.parent.color = RBNode.BLACK;
					y.color = RBNode.BLACK;
					z.parent.parent.color = RBNode.RED;
					z = z.parent.parent;
				}
				// Case 2: if y is black & z is a right child
				else if (z == z.parent.right){

					// leftRotaet around z's parent
					z = z.parent;
					leftRotate(z);
				}

				// Case 3: else y is black & z is a left child
				else{
					// recolor and rotate round z's grandpa
					z.parent.color = RBNode.BLACK;
					z.parent.parent.color = RBNode.RED;
					rightRotate(z.parent.parent);
				}
			}

			// If z's parent is the right child of it's parent.
			else{

				// Initialize y to z's cousin
				y = z.parent.parent.left;

				// Case 1: if y is red...recolor
				if (y.color == RBNode.RED){
					z.parent.color = RBNode.BLACK;
					y.color = RBNode.BLACK;
					z.parent.parent.color = RBNode.RED;
					z = z.parent.parent;
				}

				// Case 2: if y is black and z is a left child
				else if (z == z.parent.left){
					// rightRotate around z's parent
					z = z.parent;
					rightRotate(z);
				}
				// Case 3: if y  is black and z is a right child
				else{
					// recolor and rotate around z's grandpa
					z.parent.color = RBNode.BLACK;
					z.parent.parent.color = RBNode.RED;
					leftRotate(z.parent.parent);
				}
			}
		}
	// Color root black at all times
	root.color = RBNode.BLACK;

	}

	/**
	 * 1b search
	 * @param key
	 * @return
	 */
	public RBNode<K> search(K key){

		// Initialize a pointer to the root to traverse the tree
		RBNode<K> current = root;

		// While we haven't reached the end of the tree
		while (!isNil(current)){

			// If we have found a node with a key equal to key
			if (current.key.equals(key))

				// return that node and exit search(int)
				return current;

				// go left or right based on value of current and key
			else if (current.key.compareTo(key) < 0)
				current = current.right;

				// go left or right based on value of current and key
			else
				current = current.left;
		}

		// we have not found a node whose key is "key"
		return null;


	}// end search(int key)

	/**
	 * 1c min and max
	 * @param node
	 * @return
	 */
	public RBNode<K> min(RBNode<K> node){

		while (!isNil(node.left))
			node = node.left;
		return node;
	}

	public RBNode<K> max(RBNode<K> node){
		// while there is a smaller key, keep going right
		while (!isNil(node.right))
			node = node.right;
		return node;
	}

	/**
	 * parent getter
	 * @param x
	 * @return
	 */
	public RBNode<K> treeSuccessor(RBNode<K> x){
		// if x.left is not nil, call min(x.right)
		if (!isNil(x.left) )
			return min(x.right);

		RBNode<K> y = x.parent;

		// while x is it's parent's right child...
		while (!isNil(y) && x == y.right){
			// Keep moving up in the tree
			x = y;
			y = y.parent;
		}
		// Return successor
		return y;
	}// end min(RBNode x)


	private void fixNodeData(RBNode<K> x, RBNode<K> y){
		// help for travesal
		RBNode<K> current = nil;
		RBNode<K> track = nil;
		// if x is nil, then we will start updating at y.parent
		// Set track to y, y.parent's child
		if (isNil(x)){
			current = y.parent;
			track = y;
		}
		// if x is not nil, then we start updating at x.parent
		// Set track to x, x.parent's child
		else{
			current = x.parent;
			track = x;
		}
		// while we haven't reached the root
		while (!isNil(current)){
			// if the node we deleted has a different key than
			// the current node
			if (y.key != current.key) {

				// if the node we deleted is greater than
				// current.key then decrement current.numRight
				if (y.key.compareTo(current.key) > 0)
					current.numRight--;

				// if the node we deleted is less than
				// current.key thendecrement current.numLeft
				if (y.key.compareTo(current.key) < 0)
					current.numLeft--;
			}

			// if the node we deleted has the same key as the
			// current node we are checking
			else{
				// the cases where the current node has any nil
				// children and update appropriately
				if (isNil(current.left))
					current.numLeft--;
				else if (isNil(current.right))
					current.numRight--;

				// the cases where current has two children and
				// we must determine whether track is it's left
				// or right child and update appropriately
				else if (track == current.right)
					current.numRight--;
				else if (track == current.left)
					current.numLeft--;
			}

			// update track and current
			track = current;
			current = current.parent;
		}
	}

	/**
	 *
	 * @param x child of deleted node from remove(v)
	 * Restores the Red Black properties that may have been violated during
	 * the removal of a node in remove(RBNode v)
	 */
	private void removeFix(RBNode<K> x){

		RBNode<K> w;
		// While we haven't fixed the tree completely...
		while (x != root && x.color == RBNode.BLACK){
			// if x is it's parent's left child
			if (x == x.parent.left){
				// set w = x's sibling
				w = x.parent.right;

				// Case 1, w's color is red.
				if (w.color == RBNode.RED){
					w.color = RBNode.BLACK;
					x.parent.color = RBNode.RED;
					leftRotate(x.parent);
					w = x.parent.right;
				}
				// Case 2, both of w's children are black
				if (w.left.color == RBNode.BLACK &&
							w.right.color == RBNode.BLACK){
					w.color = RBNode.RED;
					x = x.parent;
				}
				// Case 3 / Case 4
				else{
					// Case 3, w's right child is black
					if (w.right.color == RBNode.BLACK){
						w.left.color = RBNode.BLACK;
						w.color = RBNode.RED;
						rightRotate(w);
						w = x.parent.right;
					}
					// Case 4, w = black, w.right = red
					w.color = x.parent.color;
					x.parent.color = RBNode.BLACK;
					w.right.color = RBNode.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			}
				// if x is it's parent's right child
			else {
				// set w to x's sibling
				w = x.parent.left;
				// Case 1, w's color is red
				if (w.color == RBNode.RED){
					w.color = RBNode.BLACK;
					x.parent.color = RBNode.RED;
					rightRotate(x.parent);
					w = x.parent.left;
				}

				// Case 2, both of w's children are black
				if (w.right.color == RBNode.BLACK &&
							w.left.color == RBNode.BLACK){
					w.color = RBNode.RED;
					x = x.parent;
				} else {
					// Case 3, w's left child is black
					 if (w.left.color == RBNode.BLACK){
						w.right.color = RBNode.BLACK;
						w.color = RBNode.RED;
						leftRotate(w);
						w = x.parent.left;
					}

					// Case 4, w = black, and w.left = red
					w.color = x.parent.color;
					x.parent.color = RBNode.BLACK;
					w.left.color = RBNode.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}// end while

		// set x to black to ensure there is no violation of
		// RedBlack tree Properties
		x.color = RBNode.BLACK;
	}// end removeFix(RBNode x)


	// @param: node, the RBNode we must check to see whether it's nil
	// @return: return's true of node is nil and false otherwise
	private boolean isNil(RBNode node){

		// return appropriate value
		return node == nil;

	}

	/**
	 * 1d toSortedArrayList
	 * @param arrayList
	 */
	public void toSortedArrayList(ArrayList arrayList) {
		sortArrayList(arrayList, root);
	}

	public ArrayList sortArrayList(ArrayList arrayList, RBNode n) {
		if (!isNil(n)) {
			sortArrayList(arrayList, n.left);
			arrayList.add(n.key);
			sortArrayList(arrayList, n.right);
		}
		return arrayList;
	}

	public static void main(String[] args) {

		/**
		 * 1e put elements from oneHundredK to empty RBTree and BinarySearchTree respectively
		 */
		ArrayList oneHundredK = new ArrayList();
		for (int i = 0; i <= 100000; i++) {
			oneHundredK.add(i);
		}
		//test console
		//System.out.print("Sorted array of the 100k: ");
		//for (int i = 1; i < oneHundredK.size(); i++) {
		//	System.out.print(oneHundredK.get(i) + ",");
		//}

		long begin = 0, end = 0;

		RBTree oneHKTree = new RBTree();
		//begin = System.nanoTime();
		for (int i = 0; i < oneHundredK.size(); i++) {
			int num = (int) oneHundredK.get(i);
			oneHKTree.insert(num);
		}
		//end = System.nanoTime();
		//System.out.println("time to add 100k elements into RBTree: " + (end - begin)); // gives 29353114

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
		//begin = System.nanoTime();
		for (int i = 0; i < 23702; i++) {//StackOverflowError at index 23703, not sure why
			int num = (int) oneHundredK.get(i);
			binarySearchTree.insertion(num);
		}
		//end = System.nanoTime();
		//System.out.println("time to add 23702 elements into BSTree: " + (end - begin)); // gives 1280429644
		//rough estimation should be (1280429644/23702)*100000 = 5402200844

		/**
		 * 1f
 		 */
		ArrayList oneHundredKPermutated = new ArrayList();
		for (int i = 0; i < oneHundredK.size(); i++) {
			oneHundredKPermutated.add(oneHundredK.get(oneHundredK.size() - i - 1));
		}

		/**
		 * RBTree
		 */
		RBTree oneHKTreeF = new RBTree();
		//begin = System.nanoTime();
		for (int i = 0; i < oneHundredK.size(); i++) {
			int num = (int) oneHundredK.get(i);
			oneHKTreeF.insert(num);
		}
		//end = System.nanoTime();
		//System.out.println("time to add 100k elements into permutated RBTree: " + (end - begin)); // gives 26345022
		//end = 0; begin = 0;

		//BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
		//begin = System.nanoTime();

		/**
		 * BSTree
		 */
		BinarySearchTree<Integer> binarySearchTreeF = new BinarySearchTree<>();
        //begin = System.nanoTime();
		for (int i = 0; i < 23706; i++) {//StackOverflowError at index 23707, not sure why
			int num = (int) oneHundredKPermutated.get(i);
			binarySearchTreeF.insertion(num);
		}
		end = System.nanoTime();
		//System.out.println("time to add 23702 elements into BSTree: " + (end - begin)); // gives 1249339262
		//rough estimation should be (1249339262/23716)*100000 = 56267917279

		/**
		 * 1g
		 */
		begin = System.nanoTime();
		for (int i = 0; i <= 1000; i++) {
			Random random = new Random();
			int z = random.nextInt(100000);
			System.out.print("searching value " + z + ", found ");
			System.out.println(oneHKTree.search(z).key);
		}
		end = System.nanoTime();
		System.out.println("Time elapsed for oneHKTree: " + (end - begin)); //gives 20695834

		begin = System.nanoTime();
		for (int i = 0; i <= 1000; i++) {
			Random random = new Random();
			int z = random.nextInt(100000);
			System.out.print("searching value " + z + ", found ");
			System.out.println(oneHKTreeF.search(z).key);
		}
		end = System.nanoTime();
		System.out.println("Time elapsed for oneHKTreeF: " + (end - begin)); //gives 15817778

        //can't calculate search time for 2 BST trees because Overflow Exception after ~23700 indexes

		// test console
		//ArrayList test = new ArrayList();
		//tree.toSortedArrayList(test); //change tree to the RedBlackTree you want to test
		//System.out.print("Sorted array of the RBTree: ");
		//for (int i = 0; i < test.size(); i++) {
		//	System.out.print(test.get(i) + ",");
		//}
		//System.out.println("");

	}
}

