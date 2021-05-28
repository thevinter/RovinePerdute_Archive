package it.unibs.pgrArnaldo.CuoriSolitari.RovinePerdute;

public class Position {

    private double coord_x;
    private double coord_y;
    private double coord_h;

    public Position(double coord_x, double coord_y, double coord_h) {

        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.coord_h = coord_h;

    }

    public double getX() {
        return coord_x;
    }

    public double getY() {
        return coord_y;
    }

    public double getH() {
        return coord_h;
    }

}
