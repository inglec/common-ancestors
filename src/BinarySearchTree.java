import java.util.ArrayList;

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

    /**
     * Search for a node within a subtree by its key, and then return its value.
     *
     * @param node - The node whose subtree is being searched.
     *        key  - The key of the node being searched for.
     * @return     - The value of the node, if found.
     */
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

    /**
     * Return the key of the lowest parent that two nodes have in common.
     * @param key1 - The key of the first node.
     * @param key2 - The key of the second node.
     * @return The key of the lowest common ancestor.
     */
    public Key lowestCommonAncestor(Key key1, Key key2) {
        ArrayList<Key> ancestors1 = getAncestors(key1);
        ArrayList<Key> ancestors2 = getAncestors(key2);

        if (ancestors1 == null || ancestors2 == null) {
            return null;
        }

        for (int i = ancestors1.size()-1; i >= 0; i--) {
            for (int j = ancestors2.size()-1; j >= 0; j--) {
                //Return lowest ancestor whose keys equal each other.
                if (ancestors1.get(i).compareTo(ancestors2.get(j)) == 0) {
                    return ancestors1.get(i);
                }
            }
        }

        return null;    //Should never return here.
    }

    /**
     * Return the trail of keys required to reach the required node from root.
     *
     * @param key - The key of the target node.
     * @return - Path to target node. Null if not in BST.
     */
    public ArrayList<Key> getAncestors(Key key) {
        if (root == null) {
            return null;    //BST is empty.
        }

        ArrayList<Key> ancestors = new ArrayList<Key>();

        Node current = root;

        //While not yet at destination node.
        while (current.key.compareTo(key) != 0) {
            ancestors.add(current.key);

            //Go left or right based on key.
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            }
            else if (cmp > 0) {
                current = current.right;
            }

            if (current == null) {
                return null;
            }
        }

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
     * Produces a String representation of the subtree where child element of a node
     * are placed in between brackets to the left and right of the parent node.
     *
     * @param depth - The number of levels of recursion the function is run.
     * @return      - A String representation of the BST.
     */
    private String toString(Node node, int depth) {
        if (node == null) {
            return "";
        }
        else {
            return "( " + toString(node.left, depth+1) + " ) " + node.value + "[" + depth + "] ( " + toString(node.right, depth+1) + " )";
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        bst.insert(10, "Owen");
        bst.insert(3, "Leon");
        bst.insert(6, "Brian");
        bst.insert(8, "Cian");
        bst.insert(4, "Ciarán");

        System.out.println(bst.toString());

        ArrayList ancestors = bst.getAncestors(4);

        System.out.println(bst.lowestCommonAncestor(4, 8));
    }
}
