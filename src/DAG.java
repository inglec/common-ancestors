import java.util.ArrayList;

public class DAG {
    private final int vertices;    //Number of vertices in DAG.
    private final ArrayList<Integer>[] adjacencyList;

    /**
     * Constructor.
     *
     * @param v - Number of vertices.
     */
    public DAG(int v) {
        if (v > 0) {
            vertices = v;
        }
        else {
            vertices = 0;
        }

        //Create list of adjacent vertices for each vertex.
        adjacencyList = (ArrayList<Integer>[]) new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
    }

    /**
     * Add a directed edge from v -> w.
     *
     * @param v - Origin vertex.
     * @param w - Destination vertex.
     */
    public void addEdge(int v, int w) {
        ArrayList<Integer> current = adjacencyList[v];

        if (!current.contains(w)) {
            current.add(w);

            if (containsCycle()) {
                current.remove(w);
                System.out.println("Cycle-completing edge " + v + "->" + w + " ignored.");
            }
        }
        else {
            System.out.println("Duplicate edge " + v + "->" + w + " ignored.");
        }
    }

    /**
     * Return the vertices pointing from v.
     *
     * @param v - The origin vertex.
     * @return The destination vertices, if any.
     */
    public Iterable<Integer> adjacent(int v) {
        //TODO
        return null;
    }

    /**
     * @return The number of vertices in the graph.
     */
    public int vertexCount() {
        //TODO
        return -1;
    }

    /**
     * @return Whether the current graph contains a cycle.
     */
    public boolean containsCycle() {
        //TODO
        return false;
    }

    /**
     * @return The number of edges in the graph.
     */
    public int edgeCount() {
        //TODO
        return -1;
    }

    public String toString() {
        String string = "";

        for (int i = 0; i < vertices; i++) {
            ArrayList<Integer> current = adjacencyList[i];
            string += i + ": ";

            for (int j = 0; j < current.size(); j++) {
                string += current.get(j) + " ";
            }
            string += "\n";
        }

        return string;
    }
}