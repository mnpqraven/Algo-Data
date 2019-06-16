/**
 */ // class RBNode
class RBNode<T extends Comparable<T>> {

    //color modes, only 2 colors so binary is favorable
    public static final int BLACK = 0;
    public static final int RED = 1;

	public T key;

    RBNode<T> parent;
    RBNode<T> left;
    RBNode<T> right;

    // the number of elements to the left/right of each node
    public int numLeft = 0;
    public int numRight = 0;

    // the color of a node
    public int color;

    RBNode(){
        //default color is BLACK
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

	RBNode(T key){
        this();
        this.key = key;
	}
}

