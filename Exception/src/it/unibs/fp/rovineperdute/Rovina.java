package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

/**
 * Il tipo Rovina, rappresenta il grafo.
 */
public class Rovina {

    private static ArrayList<Citta> rovina = new ArrayList<>();

    /**
     * Instantiates a new Rovina.
     *
     * @param rovina the rovina
     */
    public Rovina(ArrayList<Citta> rovina) {
        this.rovina = rovina;
    }

    /**
     * Gets rovina.
     *
     * @return the rovina
     */
    public static ArrayList<Citta> getRovina() {
        return rovina;
    }
}