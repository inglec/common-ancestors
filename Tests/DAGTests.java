import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DAGTests {
    @Test
    public void testConstructor() {
        DAG dag;

        dag = new DAG(5);
        assertEquals("0: \n1: \n2: \n3: \n4: \n", dag.toString());

        dag = new DAG(-1);
        assertEquals("", dag.toString());

        dag = new DAG(0);
        assertEquals("", dag.toString());

        dag = new DAG(1);
        assertEquals("0: \n", dag.toString());
    }

    public void testAddEdge() {
        DAG dag = new DAG(10);
        dag.addEdge(0, 9);
        dag.addEdge(0, 4);
        dag.addEdge(2, 9);
        assertEquals("0: 9 4 \n1: \n2: 9 \n3: \n4: \n5: \n6: \n7: \n8: \n9: \n", dag.toString());

        //Attempt to add duplicate edge
        dag.addEdge(2, 9);
        assertEquals("0: 9 4 \n1: \n2: 9 \n3: \n4: \n5: \n6: \n7: \n8: \n9: \n", dag.toString());

        //Attempt to add edge that would result in a cycle.
        dag.addEdge(4, 6);
        dag.addEdge(6, 0);  //Would complete cycle 0-4-6-0
        assertEquals("0: 9 4 \n1: \n2: 9 \n3: \n4: 6 \n5: \n6: \n7: \n8: \n9: \n", dag.toString());
    }

}