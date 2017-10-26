import java.util.ArrayList;

public class DAG {
    private final ArrayList<Integer>[] adjTable;    //Adjacency table for all adjacency lists.

    /**
     * Constructor.
     *
     * @param v - Number of vertices.
     */
    public DAG(int v) {
        //Create table of adjacency lists.
        adjTable = (ArrayList<Integer>[]) new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjTable[i] = new ArrayList<Integer>();
        }
    }

    /**
     * Add a directed edge from v -> w.
     *
     * @param v - Origin vertex.
     * @param w - Destination vertex.
     */
    public void addEdge(int v, int w) {
        ArrayList<Integer> adjList = adjTable[v];

        if (!adjList.contains(w)) {
            adjList.add(w);

            if (containsCycle()) {
                adjList.remove(w);
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
     * @return The destination vertices as an iterable, if any.
     */
    public Iterable<Integer> adjacent(int v) {
        return adjTable[v];
    }

    /**
     * @return The number of vertices in the graph.
     */
    public int vertexCount() {
        return adjTable.length;
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

        for (int i = 0; i < adjTable.length; i++) {
            ArrayList<Integer> adjList = adjTable[i];
            string += i + ": ";

            for (int j = 0; j < adjList.size(); j++) {
                string += adjList.get(j) + " ";
            }
            string += "\n";
        }

        return string;
    }

}