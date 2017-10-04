public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Node left, right;

        private Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
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
            node.value = value;
        }

        return node;    //Update all nodes with new subtrees.
    }

    /**
     * Search for a node by its key, and then return its value.
     *
     * @param key - The key of the node being searched for.
     * @return    - The value of the node, if found.
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        //If key does not exist in BST return null.
        if (node == null) {
            return null;
        }

        //Decide which subtree to check next for key.
        int cmp = key.compareTo(node.key);

        //If cmp < 0, go left.
        //If cmp > 0, go right.
        //If cmp == 0, return node's value.
        if (cmp < 0) {
            return get(node.left, key);
        }
        else if (cmp > 0) {
            return get(node.right, key);
        }
        else {
            return node.value;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        bst.insert(3, "Owen");
        bst.insert(2, "Leon");
        bst.insert(1, "Brian");
        bst.insert(4, "Cian");
        bst.insert(5, "Ciarán");
    }
}
