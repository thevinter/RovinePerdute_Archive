package it.unibs.pgrArnaldo.CuoriSolitari.RovinePerdute;

import java.util.ArrayList;

public class City {

    private Position coord;
    private String name;
    private int id;
    private ArrayList<Integer> link;

    public City(Position coord, String name, int id, ArrayList<Integer> link) {
        this.coord = coord;
        this.name = name;
        this.id = id;
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Città{" + "coordinate: [" + coord.getX() + "; " + coord.getY() + "; " + coord.getH() + "], nome: '" + name + '\'' + ", id: " + id + ", link: [");

        for(int i=0; i<link.size(); i++){
            sb.append(String.format("%d ,", i+1, link.get(i)));
        }

        sb.append("\b\b]}");

        return sb.toString();
    }

    public Position getPosition() {
        return coord;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getLink() {
        return link;
    }
}

