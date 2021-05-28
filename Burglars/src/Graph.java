import java.util.ArrayList;

public class Graph {

    private final ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public Graph(int v) {
        for (int i = 0; i < v; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public void addVertex(){
        this.adjList.add(new ArrayList<>());
    }

    /**
     * Gets the number of nodes in the graph
     *
     * @return number of nodes
     */
    public int getVertexNum() {
        return adjList.size();
    }

    /**
     * Gets the requested node with all of his edges
     *
     * @param v index of the wanted node
     * @return the wanted node with all of his edges
     */
    public ArrayList<Integer> getVertex(int v) {
        return adjList.get(v);
    }

    /**
     * Adds an Edge between the two specified nodes
     *
     * @param from starting node index
     * @param to   destination node index
     */
    public void addEdge(int from, int to) {
        adjList.get(from).add(to);
    }

    /**
     * gets the value of the edge between the two specified nodes
     *
     * @param from starting node index
     * @param to   destination node index
     * @return 1 if the Edge exists
     * @throws IllegalArgumentException if the same node is given as starting and dastination
     */
    public boolean areConnected(int from, int to) {
        if (from == to) {
            throw (new IllegalArgumentException());
        }
        return adjList.get(from).contains(to);
    }

}

