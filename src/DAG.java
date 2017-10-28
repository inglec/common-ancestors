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
        addEdge(adjTable, v, w);
    }

    private void addEdge(ArrayList<Integer>[] adjTable, int v, int w) {
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

    /**
     * Recursively mark all vertices in adjacency table as IN_PROGRESS.
     * If an IN_PROGRESS node is encountered, then a cycle exists.
     * @param vertex - The current vertex whose adjacency table is to be searched.
     * @param vertices - The status of each vertex in the graph. UNVISITED, IN_PROGRESS or VISITED.
     * @return Whether or not a cycle exists for said vertex.
     */
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
        for (int v : adjList) { // Check each vertex in adjacency list for cycles.
            if (containsCycle(v, vertices))
                return true;
        }
        vertices[vertex] = VISITED;  // Mark permanently as visited.
        return false;
    }

    /**
     * Returns the lowest common ancestor of 2 nodes: v1 and v2.
     * Returns -1 if no such nodes exist or if they are unconnected.
     * @param v1 - vertex 1
     * @param v2 - vertex 2
     * @return The vertex which is the closest connected vertex to both v1 and v2.
     */
    public int lowestCommonAncestor(int v1, int v2) {
        ArrayList<Integer>[] reversed = reverseAdjacencyTable();

        return -1;
    }

    /**
     * Reverses adjacency table to instead show the ancestor of each node rather than the descendant.
     * @return The reversed adjacency table.
     */
    private ArrayList<Integer>[] reverseAdjacencyTable() {
        ArrayList<Integer>[] reversed = (ArrayList<Integer>[]) new ArrayList[adjTable.length];
        Arrays.fill(reversed, new ArrayList<Integer>());    // Fill each index with empty adjacency list.

        // For each v->w, add w->v to reversed adjacency table.
        for (int v = 0; v < adjTable.length; v++) {
            for (int w : adjTable[v])
                addEdge(reversed, w, v);
        }
        return reversed;
    }

    /**
     * The String representation of the adjacency table.
     * Returns the adjacency list for each vertex in the graph in the following form:
     * 1: 3, 5
     * 2:
     * 3: 6, 7, 2
     * 4 :
     * etc...
     */
    public String toString() {
        String string = "";
        for (int v = 0; v < adjTable.length; v++) {
            string += v + ": ";
            for (int vertex : adjTable[v])  // Add each vertex in the current adjacency list.
                string += vertex + " ";
            string += "\n";
        }
        return string;
    }

}