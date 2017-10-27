import java.util.ArrayList;
import java.util.Arrays;

public class DAG {
    public static final int UNVISITED = 0, IN_PROGRESS = 1, VISITED = 2;

    private final ArrayList<Integer>[] adjTable;    //Adjacency table for all adjacency lists.

    /**
     * Constructor.
     *
     * @param v - Number of vertices.
     */
    public DAG(int v) {
        //Create table of adjacency lists.
        adjTable = (ArrayList<Integer>[]) new ArrayList[v];
        for (int i = 0; i < v; i++)
            adjTable[i] = new ArrayList<Integer>();
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
                // Remove offending edge from adjacency list.
                for (int i = 0; i < adjList.size(); i++) {
                    if (adjList.get(i) == w)
                        adjList.remove(i);
                }
                System.out.println("Cycle-completing edge " + v + "->" + w + " ignored.");
            }
        }
        else System.out.println("Duplicate edge " + v + "->" + w + " ignored.");
    }

    /**
     * @return Whether or not the current graph contains a cycle.
     */
    public boolean containsCycle() {
        // 0 = unvisited, 1 = search in progress, 2 = visited by other search.
        int[] vertices =  new int[adjTable.length];
        Arrays.fill(vertices, UNVISITED);

        for (int v = 0; v < adjTable.length; v++) {
            if (containsCycle(v, vertices))
                return true;
        }
        return false;
    }

    private boolean containsCycle(int vertex, int[] vertices) {
        switch (vertices[vertex]) {
            case IN_PROGRESS:   // A cycle exists.
                return true;
            case VISITED:       // This path was already checked - ignore.
                return false;
            case UNVISITED:
                vertices[vertex] = IN_PROGRESS;
        }

        ArrayList<Integer> adjList = adjTable[vertex];  // Adjacency list for current vertex.
        for (int v : adjList) {
            if (containsCycle(v, vertices))
                return true;
        }
        vertices[vertex] = VISITED;  // Mark permanently as visited.
        return false;
    }

    public String toString() {
        String string = "";

        for (int i = 0; i < adjTable.length; i++) {
            ArrayList<Integer> adjList = adjTable[i];

            string += i + ": ";
            for (int j = 0; j < adjList.size(); j++)
                string += adjList.get(j) + " ";
            string += "\n";
        }
        return string;
    }

}