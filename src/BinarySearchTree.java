import java.util.ArrayList;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Node left, right;   //Left and right children of this node. Null if empty.

        private Key key;    //Unique key of the node.
        private Value value;    //May have same value as other nodes.

        /**
         * Constructor.
         * @param key - Unique key of the node.
         * @param value - Non-unique value of the node.
         */
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        /*
         * Getter functions for the Node class private variables.
         */
        public Node getLeftChild() { return left; }

        public Node getRightChild() { return right; }

        public Key getKey() { return key; }

        public Value getValue() { return value; }

        public boolean equals(Node node) {
            return (left == node.left) && (right == node.right) && key.equals(node.key) && value.equals(node.value);
        }
    }

    /**
     * Insert a new node into the BST.
     *
     * @param key   - The key of the new node.
     * @param value - The value of the new node.
     */
    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    /**
     * Insert a new node into the BST below the passed node.
     *
     * @param node  - The node below which the new node will be instered.
     * @param key   - The key of the new node.
     * @param value - The value of the new node.
     * @return      - The node containing the new subtree.
     */
    private Node insert(Node node, Key key, Value value) {
        //If BST is empty, return new node as root.
        if (node == null) {
            return new Node(key, value);
        }

        //Decide where to place the new node in relation to the current node.
        int cmp = key.compareTo(node.key);

        //If cmp < 0, insert new node to left.
        //If cmp > 0, insert new node to right.
        //If cmp == 0, update current value.
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        }
        else {
            node.value = value;   //Update value of node to new value.
        }

        return node;    //Update all affected nodes with new subtrees.
    }

    /**
     * Search for a node by its key, and then return its value.
     *
     * @param key - The key of the node being searched for.
     * @return    - The value of the node, if found.
     */
    public Value getValue(Key key) {
        return getNode(key).value;
    }

    /**
     * Search for a node by its key, and then return it.
     *
     * @param key - The key of the node being searched for.
     * @return The node, if found.
     */
    public Node getNode(Key key) {
        return getNode(root, key);
    }

    /**
     * Search for a node within a subtree by its key, and then return it.
     *
     * @param node - The node whose subtree is being searched.
     *        key  - The key of the node being searched for.
     * @return The node, if found.
     */
    private Node getNode(Node node, Key key) {
        //If key does not exist in BST return null.
        if (node == null) {
            return null;
        }

        //Decide which subtree to check next for key.
        int cmp = key.compareTo(node.key);

        //If cmp < 0, go left.
        //If cmp > 0, go right.
        //If cmp == 0, return node.
        if (cmp < 0) {
            return getNode(node.left, key);
        }
        else if (cmp > 0) {
            return getNode(node.right, key);
        }
        else {
            return node;
        }
    }

    /**
     * Return the key of the lowest parent that two nodes have in common.
     * @param key1 - The key of the first node.
     * @param key2 - The key of the second node.
     * @return The lowest common ancestor.
     */
    public Node lowestCommonAncestor(Key key1, Key key2) {
        ArrayList<Node> ancestors1 = getAncestors(key1);
        ArrayList<Node> ancestors2 = getAncestors(key2);

        if (ancestors1 == null || ancestors2 == null) {
            return null;    //One or both of the keys do not exist in BST.
        }

        //Iterate in reverse order through ArrayLists to find lowest matching Node.
        for (int i = ancestors1.size()-1; i >= 0; i--) {
            for (int j = ancestors2.size()-1; j >= 0; j--) {
                //Return first ancestor that matches.
                if (ancestors1.get(i).equals(ancestors2.get(j))) {
                    return ancestors1.get(i);
                }
            }
        }

        return root;    //All nodes have the root in common. i.e. highest common ancestor
    }

    /**
     * Return the trail of nodes required to reach the required node from root.
     *
     * @param key - The key of the target node.
     * @return - Path to target node. Null if not in BST.
     */
    public ArrayList<Node> getAncestors(Key key) {
        if (root == null) {
            return null;    //BST is empty.
        }

        ArrayList<Node> ancestors = new ArrayList<Node>();
        Node current = root;

        //While not yet at destination node.
        while (!current.key.equals(key)) {
            ancestors.add(current);

            //Go left or right based on key.
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            }
            else if (cmp > 0) {
                current = current.right;
            }

            if (current == null) {
                return null;    //Key doesn't exist in BST.
            }
        }

        ancestors.add(current); //Add node itself to the end of the chain.

        return ancestors;
    }

    /**
     * Produces a String representation of the BST where child element of a node
     * are placed in between brackets to the left and right of the parent node.
     *
     * @return  - A String representation of the BST.
     */
    public String toString() {
        return toString(root, 0);
    }

    /**
     * Produces a String representation of the subtree where the children of a node
     * are placed in between brackets to the left and right of the parent node.
     * The key, value and depth of each node is displayed.
     *
     * @param depth - The number of levels of recursion the function is run.
     * @return      - A String representation of the BST.
     */
    private String toString(Node node, int depth) {
        if (node == null) {
            return "x";
        }
        else {
            return "( " + toString(node.left, depth+1) + " ) " + node.key + ":" + node.value + "[" + depth + "] ( " + toString(node.right, depth+1) + " )";
        }
    }
}
