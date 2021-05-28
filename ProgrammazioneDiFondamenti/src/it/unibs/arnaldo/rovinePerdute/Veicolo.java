package it.unibs.arnaldo.rovinePerdute;

import java.util.ArrayList;

public class Veicolo{
    //ATTRIBUTI
    private double fuel;
    private ArrayList<Luogo> route;
    private String tipologia;

    //Metodo costruttore
    public Veicolo(ArrayList<Luogo> route, String tipologia) {
        this.fuel = 0d;
        this.route = route;
        this.tipologia = tipologia;
    }

    //GETTERS AND SETTERS
    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        //arrotondo il numero a 2 numeri decimali
        this.fuel = Math.round(fuel * 100.0) / 100.0;
    }

    public ArrayList<Luogo> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Luogo> route) {
        this.route = route;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

}
