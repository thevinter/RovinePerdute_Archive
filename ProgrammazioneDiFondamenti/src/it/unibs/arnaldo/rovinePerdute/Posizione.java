package it.unibs.arnaldo.rovinePerdute;

public class Posizione {

    //attributi
    private int x;
    private int y;
    private int h;

    //costruttore
    public Posizione(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    //metodo cosrtruttore di una Posizione con gli attributi settati ai valori di default
    public Posizione(){ }

    //get e set
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
