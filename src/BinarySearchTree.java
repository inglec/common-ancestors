public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Node left, right;   //Left and right children of this node. Null if empty.

        private Key key;        //Unique key of the node.
        private Value value;    //May have same value as other nodes.

        /**
         * Constructor.
         * @param key - Unique key of the node.
         * @param value - Non-unique value of the node.
         */
        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Insert a new node into the BST. If key exists, replace with new value.
     * Should not insert a new node with a null key.
     *
     * @param key   - The key of the new node.
     * @param value - The value of the new node.
     */
    public void insert(Key key, Value value) {
        if (key != null && value != null) {
            root = insert(root, key, value);
        }
    }

    /**
     * Insert a new node into the BST below the passed node.
     *
     * @param node  - The node below which the new node will be instered.
     * @param key   - The key of the new node.
     * @param value - The value of the new node.
     * @return        The node containing the new subtree.
     */
    private Node insert(Node node, Key key, Value value) {
        //If empty link found, create new node here.
        if (node == null) {
            return new Node(key, value);
        }

        //Decide where to place the new node in relation to the current node.
        int cmp = key.compareTo(node.key);
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
     * A null key has a null value.
     *
     * @param key - The key of the node being searched for.
     * @return      The value of the node, if found. Null if not found.
     */
    public Value getValue(Key key) {
        Node node = getNode(key);

        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * Search for a node by its key, and then return it.
     * A null key has a null value.
     *
     * @param key - The key of the node being searched for.
     * @return      The node, if found.
     */
    private Node getNode(Key key) {
        if (key == null) {
            return null;
        }
        return getNode(root, key);
    }

    /**
     * Search for a node within a subtree by its key, and then return it.
     *
     * @param node - The node whose subtree is being searched.
     *        key  - The key of the node being searched for.
     * @return       The node, if found. Null otherwise.
     */
    private Node getNode(Node node, Key key) {
        //If key does not exist in BST return null.
        if (node == null || key == null) {
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
     * Returns whether or not a given key exists in the BST.
     * @param key - The key of the node being searched for.
     * @return Whether or not the node of the key exists.
     */
    public boolean contains(Key key) {
        return getNode(key) != null;
    }

    /**
     * Return the key of the lowest parent that two nodes have in common.
     * Works by branching left or right until both nodes are on opposite sides of a subtree,
     * or until one of the nodes is reached.
     *
     * @param key1 - The key of the first node.
     * @param key2 - The key of the second node.
     * @return       The lowest common ancestor. Null if passes keys are invalid.
     */
    public Key lowestCommonAncestor(Key key1, Key key2) {
        if (!(contains(key1) && contains(key2))) {
            return null;    //If both keys are not in BST.
        }

        if (key1.equals(key2)) {
            return key1;
        }

        return lowestCommonAncestor(root, key1, key2);

}
    private Key lowestCommonAncestor(Node node, Key key1, Key key2) {
        //Check if this node is one of the two being searched for.
        if (key1.equals(node.key) || key2.equals(node.key)) {
            return node.key;
        }

        int cmp1 = key1.compareTo(node.key);
        int cmp2 = key2.compareTo(node.key);

        //Choose direction to branch.
        if (cmp1 < 0 && cmp2 < 0) {
            return lowestCommonAncestor(node.left, key1, key2); //Both nodes are on the left side of this node.
        }
        else if (cmp1 > 0 && cmp2 > 0) {
            return lowestCommonAncestor(node.right, key1, key2);    //Both nodes are on the right side of this node.
        }
        else {
            return node.key;    //Keys are on opposite sides of this subtree, so this is greatest commmon ancestor.
        }
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
            return "( " + toString(node.left, depth + 1) + " ) " + node.key + ":" + node.value + "[" + depth + "] ( " + toString(node.right, depth + 1) + " )";
        }
    }

}
