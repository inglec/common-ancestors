import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BSTTesting {
    @Test
    public void testInsert() {
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
        bst.insert(10, "D");
        bst.insert(6, "E");
        bst.insert(2, "F");
        bst.insert(4, "G");
        //  1
        //     \
        //        8
        //      /  \
        //    3   10
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
}
