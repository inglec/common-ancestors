import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DAGTests {
    @Test
    public void testConstructor() {
        DAG dag;

        dag = new DAG(5);
        assertEquals("0: \n1: \n2: \n3: \n4: \n", dag.toString());

        dag = new DAG(0);
        assertEquals("", dag.toString());

        dag = new DAG(1);
        assertEquals("0: \n", dag.toString());
    }

    @Test (expected = java.lang.NegativeArraySizeException.class)
    public void testNegativeConstructor() {
        DAG dag = new DAG(-5);
    }

    @Test
    public void testAddEdge() {
        DAG dag = new DAG(10);
        assertEquals("0: \n1: \n2: \n3: \n4: \n5: \n6: \n7: \n8: \n9: \n", dag.toString());
        dag.addEdge(0, 9);
        dag.addEdge(0, 4);
        dag.addEdge(2, 9);
        assertEquals("0: 9 4 \n1: \n2: 9 \n3: \n4: \n5: \n6: \n7: \n8: \n9: \n", dag.toString());

        //Attempt to add duplicate edge
        dag.addEdge(2, 9);
        assertEquals("0: 9 4 \n1: \n2: 9 \n3: \n4: \n5: \n6: \n7: \n8: \n9: \n", dag.toString());
    }

    @Test
    public void testAddCycle() {
        //Add edge that would result in a cycle in a bidirectional graph. Should successfully add.
        DAG dag = new DAG(10);  //     1
        dag.addEdge(1, 3);  //    /  \
        dag.addEdge(3, 7);  //   3   5
        dag.addEdge(1, 5);  //    \ /
        dag.addEdge(5, 7);  //     7
        dag.addEdge(7, 9);  //     |
                                  //     9
        assertEquals("0: \n1: 3 5 \n2: \n3: 7 \n4: \n5: 7 \n6: \n7: 9 \n8: \n9: \n", dag.toString());

        //Attempt to add edge that would result in a cycle.
        dag.addEdge(0, 4);
        dag.addEdge(4, 6);
        dag.addEdge(6, 0);  //Would complete cycle 0-4-6-0
        assertEquals("0: 4 \n1: 3 5 \n2: \n3: 7 \n4: 6 \n5: 7 \n6: \n7: 9 \n8: \n9: \n", dag.toString());
    }

    @Test
    public void testLowestCommonAncestor() {
        DAG dag = new DAG(10);
        assertEquals("[]", dag.lowestCommonAncestors(4, 7).toString());

        dag.addEdge(0, 3);  //     0
        dag.addEdge(0, 5);  //    / \
        dag.addEdge(3, 7);  //   3   5
        dag.addEdge(5, 7);  //    \ / \
        dag.addEdge(7, 8);  //     7   9
        dag.addEdge(5, 9);  //     |   |
        dag.addEdge(9, 4);  //     8   4

        // Test LCA of vertices that are not in graph.
        assertEquals(null, dag.lowestCommonAncestors(-1, 7));
        assertEquals(null, dag.lowestCommonAncestors(4, -1));
        assertEquals(null, dag.lowestCommonAncestors(10, 2));

        // Test LCA of vertices in graph.
        assertEquals("[]", dag.lowestCommonAncestors(5, 2).toString());
        assertEquals("[2]", dag.lowestCommonAncestors(2, 2).toString());
        assertEquals("[0]", dag.lowestCommonAncestors(0, 9).toString());
        assertEquals("[5]", dag.lowestCommonAncestors(7, 9).toString());
        assertEquals("[5]", dag.lowestCommonAncestors(8, 4).toString());
        assertEquals("[5]", dag.lowestCommonAncestors(5, 4).toString());
        assertEquals("[0]", dag.lowestCommonAncestors(3, 4).toString());
        assertEquals("[5]", dag.lowestCommonAncestors(7, 4).toString());
    }

    @Test
    public void lowestCommonAncestors() {
        DAG dag = new DAG(10);
        dag.addEdge(0, 3);
        dag.addEdge(0, 5);
        dag.addEdge(2, 3);
        dag.addEdge(2, 5);

        assertEquals("[0, 2]", dag.lowestCommonAncestors(3, 5).toString());
    }

}