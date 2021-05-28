package it.unibs.pgrArnaldo.CuoriSolitari.RovinePerdute;

public class Vertex {

    private City city;
    private int from;
    private double distance;

    public Vertex(City city, Integer from, double distance) {
        this.city = city;
        this.from = from;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "city=" + city +
                ", from=" + from +
                ", distance=" + distance +
                '}';
    }

    public City getCity() {
        return city;
    }

    public int getFrom() {
        return from;
    }

    public double getDistance() {
        return distance;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

