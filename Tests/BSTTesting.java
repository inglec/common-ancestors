import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BSTTesting {
    @Test
    public void testInsert() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
        bst.insert(4, "Four");
        bst.insert(2, "Two");
        bst.insert(3, "Three");
        bst.insert(8, "Eight");

        assertEquals(bst.toString(), "( (  ) Two[1] ( (  ) Three[2] (  ) ) ) Four[0] ( (  ) Eight[1] (  ) )");
    }
}
