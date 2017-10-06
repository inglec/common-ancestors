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
}
