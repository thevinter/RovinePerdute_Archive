import java.util.ArrayList;

public class Route {

    private String team;
    private MyMap map;
    private double fuel;
    private ArrayList<Settlement> route;

    public Route(String team, MyMap map) {
        this.team = team;
        this.map = map;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public MyMap getMap() {
        return map;
    }

    public void setMap(MyMap map) {
        this.map = map;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public ArrayList<Settlement> getRoute() {
        return route;
    }

    public void setRoute(Vehicle v, int to) {
        this.route = v.findQuickestPath(to);
        this.fuel = map.getNode(map.getDestination()).getFuelFromStart();
    }

    public String getCityName(int id){
        return map.getNode(id).getName();
    }
}
