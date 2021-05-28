import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MyMap{
    //todo aggiunto destination attribute(id di rovine)
    private HashMap<Integer, Settlement> map;
    private int destination;

    public MyMap() {
        this.map = new HashMap<>();
    }

    /**
     * Make a new MyMap out of the given Settlements array
     * @param settlements an ArrayList of Settlements
     */
    public MyMap(ArrayList<Settlement> settlements) {

        this.map = new HashMap<>();

        for (Settlement s : settlements) {
            this.map.put(s.getId(), s);
        }
        this.destination = (map.size() -1);
    }

    public int getDestination() {
        return destination;
    }

    /**
     * Adds the settlement to the map if not present
     *
     * @param s the settlement to be added
     */
    public void addNode(Settlement s){
        if(!this.map.containsKey(s.getId())) {
            this.map.put(s.getId(), s);
        }
    }

    /**
     * returns the settlement of the given id
     *
     * @param index the id of the wanted settlement
     * @return the settlement if present, null otherwise
     */
    public Settlement getNode(int index){
        return  this.map.get(index);
    }

    /**
     * @return the number of nodes contained in this map
     */
    public int getNodesNum(){
        return this.map.size();
    }

    /**
     * @param name String containing the name of the wanted node
     * @return the node if present, null otherwise
     */
    public Settlement getNodeByName(String name){
        for (Settlement s : map.values()) {
            if(s.equals(name))
                return s;
        }
        return null;
    }

    /**
     * @return the Collection of Settlements contained in this
     */
    public Collection<Settlement> getNodes(){
        return this.map.values();
    }

}
