import java.util.ArrayList;
import java.util.Objects;

public class Settlement implements Comparable<Settlement> {

    private static final int X = 0;
    private static final int Y = 1;
    private static final int HEIGHT = 2;

    private String name;
    private int id;
    private int[] coords = new int[3];
    private double fuelFromStart = Double.POSITIVE_INFINITY; //utility attribute meant to be used for pathfinding
    private Integer nearest_id = null;

    private ArrayList<Integer> connectedTo = new ArrayList<>();

    public Settlement(String name, int id, int[] coords) {
        this.name = name;
        this.id = id;
        this.coords = coords;
    }


    public Settlement() {
    }

    /**
     * utility method to shallow copy a settlement
     *
     * @param s the settlement to be copied
     * @return the s shallow copy
     */
    public static Settlement clone(Settlement s) {
        int[] coords = new int[3];
        coords[0] = s.getX();
        coords[1] = s.getY();
        coords[2] = s.getHeight();
        Settlement cloned = new Settlement(s.getName(), s.getId(), coords);
        cloned.addConnection((ArrayList<Settlement>) s.getConnected().clone());
        return cloned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return coords[X];
    }

    public void setX(int x) {
        this.coords[X] = x;
    }

    public int getY() {
        return coords[Y];
    }

    public void setY(int y) {
        this.coords[Y] = y;
    }

    public int getHeight() {
        return coords[HEIGHT];
    }

    public void setHeight(int h) {
        this.coords[HEIGHT] = h;
    }

    public void addConnection(Integer to) {

        this.connectedTo.add(to);
    }

    public void addConnection(ArrayList links) {

        this.connectedTo.addAll(links);
    }

    /**
     * @return the ArrayList containing the id of all the connected Settlements
     */
    public ArrayList<Integer> getConnected() {

        return this.connectedTo;
    }

    /**
     * Set the fuel used from the starting node to this, it is used for pathfinding purposes
     *
     * @param fuel the fuel from the starting settlement
     */
    public void setFuelFromStart(double fuel) {

        this.fuelFromStart = fuel;
    }

    /**
     * Get the fuel used from the starting node to this, used for pathfinding purposes
     *
     * @return the fuel from the starting settlement
     */
    public double getFuelFromStart() {

        return this.fuelFromStart;
    }

    /**
     * @return the Id of the closest Settlement in the map, for pathfinding purposes
     */
    public Integer getClosestId() {
        return nearest_id;
    }

    /**
     * set the id of the closest Settlement in the map, for pathfinding purposes
     *
     * @param id the id of the closest Settlement
     */
    public void setClosestId(int id) {
        this.nearest_id = id;
    }

    /**
     * Check if a given object is equal to this based on their ids
     *
     * @param o the object to be checked
     * @return true if o and this are equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settlement)) return false;
        Settlement that = (Settlement) o;
        return getId() == that.getId();
    }

    /**
     * Check if a given String is equal to this based on his name
     *
     * @param name the name to be checked
     * @return true if o and this are equal, else false
     */
    public boolean equals(String name) {
        return this.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }

    /**
     * Compares two settlements based on the fuel consumption from the start
     *
     * @param that Settlement to be compared with this
     * @return -1 if this is closer to the start than that, 0 if this and that are equal, 1 if that is closer to the start
     */
    @Override
    public int compareTo(Settlement that) {
        return Double.compare(this.getFuelFromStart(), that.getFuelFromStart());
    }
}
