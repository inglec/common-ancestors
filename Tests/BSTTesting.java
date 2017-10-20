import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BSTTesting {
    @Test
    public void testInsert() {
        //Test null key and null value.
        BinarySearchTree<String, String> stringBst = new BinarySearchTree<String, String>();
        stringBst.insert(null, "hello"); //null key should not be inserted.
        stringBst.insert("hello", null); //null value should not be inserted.
        assertEquals("x", stringBst.toString());

        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        //Test the empty BST.
        assertEquals("x", bst.toString());

        //Test the BST with one element.
        bst.insert(4, "Paul");
        assertEquals("( x ) 4:Paul[0] ( x )", bst.toString());

        //Test inserting 3 more elements into the BST.
        bst.insert(2, "Aaron");
        bst.insert(3, "Stephen");
        bst.insert(8, "Ryan");
        assertEquals("( ( x ) 2:Aaron[1] ( ( x ) 3:Stephen[2] ( x ) ) ) 4:Paul[0] ( ( x ) 8:Ryan[1] ( x ) )", bst.toString());

        //Test inserting element with existing key.
        bst.insert(2, "Harry"); //Replace Aaron with Harry.
        assertEquals("( ( x ) 2:Harry[1] ( ( x ) 3:Stephen[2] ( x ) ) ) 4:Paul[0] ( ( x ) 8:Ryan[1] ( x ) )", bst.toString());
    }

    @Test
    public void testLowestCommonAncestor() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        //Test lowest common ancestor in tree of no nodes.
        assertEquals(null, bst.lowestCommonAncestor(2, 4));

        //Test lowest common ancestor of two nodes.
        bst.insert(1, "A");
        bst.insert(8, "B");
        bst.insert(3, "C");
        bst.insert(10,"D");
        bst.insert(6, "E");
        bst.insert(2, "F");
        bst.insert(4, "G");
        //  1
        //     \
        //        8
        //      /  \
        //    3    10
        //   /  \
        //  2    6
        //      /
        //     4
        assertEquals(3, (int)bst.lowestCommonAncestor(2, 6));
        assertEquals(8, (int)bst.lowestCommonAncestor(4, 10));
        assertEquals(1, (int)bst.lowestCommonAncestor(1, 8));
        assertEquals(3, (int)bst.lowestCommonAncestor(3, 6));

        //Test lowest common ancestor of one node.
        assertEquals(2, (int)bst.lowestCommonAncestor(2, 2));

        //Test non-existent key in BST.
        assertEquals(null, bst.lowestCommonAncestor(5, 6));
    }

    @Test
    public void testContains() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        //Test contains on an empty BST.
        assertEquals(false, bst.contains(2));

        //Create tree for testing.
        bst.insert(1, "A");
        bst.insert(8, "B");
        bst.insert(3, "C");
        bst.insert(10, "D");
        bst.insert(6, "E");
        bst.insert(2, "F");
        bst.insert(4, "G");
        //  1
        //     \
        //        8
        //      /  \
        //    3    10
        //   /  \
        //  2    6
        //      /
        //     4

        //Test tree
        assertEquals(false, bst.contains(null));
        assertEquals(true, bst.contains(1));
        assertEquals(true, bst.contains(10));
        assertEquals(true, bst.contains(4));
        assertEquals(false, bst.contains(5));
    }

    @Test
    public void testGetValue() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();

        //Test contains on an empty BST.
        assertEquals(null, bst.getValue(2));

        //Create tree for testing.
        bst.insert(1, "A");
        bst.insert(8, "B");
        bst.insert(3, "C");
        bst.insert(10, "D");
        bst.insert(6, "E");
        bst.insert(2, "F");
        bst.insert(4, "G");
        //  1
        //     \
        //        8
        //      /  \
        //    3    10
        //   /  \
        //  2    6
        //      /
        //     4

        //Test tree
        assertEquals(null, bst.getValue(null));
        assertEquals("F", bst.getValue(2));
        assertEquals("A", bst.getValue(1));
        assertEquals("D", bst.getValue(10));
        assertEquals("G", bst.getValue(4));
        assertEquals(null, bst.getValue(5));
    }


}