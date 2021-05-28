import java.util.*;

public abstract class Vehicle {

    private final MyMap map;
    private double fuelForQuickesPath;
    public Vehicle(MyMap map) {
        this.map = map;
    }

    /**
     * gets the minimum spanning three out of the map
     *
     * @return a MyMap instance containing the minimum spanning three
     */
    //TODO QUI COMINCIA LA MAGIA OSCURA, avadakedavra!
    //per me si va nell'etterno grafo,
    //per me si va tra il perduto percorso.
    //[...]
    //Lasciate ogne speranza, voi ch'intrate.
    //L'Inferno by NotValcake
    //All rights reserved
    public MyMap getMinimumPaths() {

        TreeSet<Settlement> mst = new TreeSet<>(); //our minimum spanning tree, initially empty

        PriorityQueue<Settlement> queue = new PriorityQueue<>();//priority q allows us to auto-choose the nearest node each time

        //first node setup
        mst.add(getMap().getNode(0));

        getMap().getNode(0).setClosestId(0);
        getMap().getNode(0).setFuelFromStart(0);

        queue.add(getMap().getNode(0));

        //keep iterating until all nodes are visited
        while(mst.size() != getMap().getNodesNum()){

            Settlement current_node = queue.poll(); //check the closest node to the previous one, based on the fuel_from_starting param
            int current_node_id = current_node.getId();
            double current_node_fuel = current_node.getFuelFromStart();
            mst.add(current_node); //add the current node to the visited set

            for (Integer s: getMap().getNode(current_node_id).getConnected()) {
                double temp_fuel = current_node_fuel + getFuel(getMap().getNode(s), getMap().getNode(current_node_id));
                if(temp_fuel <= getMap().getNode(s).getFuelFromStart()){ //if the new path is shorter, update the path
                    getMap().getNode(s).setFuelFromStart(temp_fuel);
                    getMap().getNode(s).setClosestId(current_node_id);
                    if(mst.contains(getMap().getNode(s))) continue; //if we already visited the next node we can skip it
                    queue.add(getMap().getNode(s)); //if not we put it into the priority q for the next iteration
                }

            }
        }
        return getMap();
    }

    /**
     * Finds the quickest path to the "to" destination
     *
     * @param to the destination we want to get the quickest path for
     * @return an array of int containing the list of nodes
     */
    public ArrayList<Settlement> findQuickestPath(int to){
        getMinimumPaths();
        ArrayList<Settlement> route = new ArrayList<>();
        Settlement current_node = getMap().getNode(to);
        while(current_node.getId() != 0){
            route.add(current_node);
            current_node = getMap().getNode(current_node.getClosestId());
        }
        route.add(current_node);
        Collections.reverse(route);
        return route;
    }

    /**
     * Calculates the fuel consumption to move from a node to another based on the kind of vehicle
     *
     * @param from the starting node
     * @param to the destination node
     * @return the fuel consumption
     */
    public abstract double getFuel(Settlement from, Settlement to);

    public MyMap getMap(){
        return map;
    }

}
