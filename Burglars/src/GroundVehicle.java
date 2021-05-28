public class GroundVehicle extends Vehicle {

    public GroundVehicle(MyMap map) {
        super(map);
    }

    /**
     * Returns the fuel consumption which is equal to the euclidean distance between two settlements
     *
     * @param from the starting node
     * @param to   the destination node
     * @return
     */
    @Override
    public double getFuel(Settlement from, Settlement to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }
}
