package it.unibs.fp.rovineperdute;

import java.util.*;

/**
 * Il Team.
 */
public class Team {

    private String veicolo;
    private Stack<Citta> percorso_minimo = new Stack<>();
    private double carburante_tot;

    /**
     * Instantiates a new Team.
     *
     * @param veicolo the veicolo
     */
    public Team(String veicolo) {
        this.veicolo = veicolo;
    }

    /**
     * Gets veicolo.
     *
     * @return the veicolo
     */
    public String getVeicolo() {
        return veicolo;
    }

    /**
     * Gets percorso minimo.
     *
     * @return the percorso minimo
     */
    public Stack<Citta> getPercorso_minimo() {
        return percorso_minimo;
    }

    /**
     * Set carburante totale.
     *
     * @param distanza the distanza
     */
    public void setCarburanteTotale(double[] distanza) {
        this.carburante_tot = distanza[distanza.length - Costante.C1];
    }

    /**
     * Gets carburante tot.
     *
     * @return the carburante tot
     */
    public double getCarburante_tot() {
        return carburante_tot;
    }

    /**
     * Algoritmo di Dijkstra.
     *
     * @param citta_partenza citta di partenza
     */
    public void dijkstra(Citta citta_partenza) {

        int numero_citta = Rovina.getRovina().size();
        double[] distanza = new double[numero_citta]; // rappresenta tutte le distanze dal sorgente
        int[] citta_precedenti = new int[numero_citta]; // rappresenta la citta(id) che deve passare per arrivare all'i-esima città
        boolean[] visitato = new boolean[numero_citta]; // rappresenta se all'i-esimo città è già stata visitata o meno

        ArrayList<Citta> citta = new ArrayList(Rovina.getRovina());

        for (int i = Costante.C0; i < numero_citta; i++) {
            distanza[i] = Double.POSITIVE_INFINITY; // set di tutte le distanze a infinito
            citta_precedenti[i] = -Costante.C1;
            visitato[i] = false;
        }

        distanza[citta_partenza.getId()] = Costante.C0; // la distanza per arrivare alla sorgente è 0

        for (int i = Costante.C0; i < numero_citta; i++) {
            int id_attuale = distanzaPiuBreve(distanza, visitato); // va a trovare l'id della città con distanza più breve

            visitato[id_attuale] = true; // set la citta a true, cioè è già stata visitata
            for (int j = Costante.C0; j < numero_citta; j++) {                                                            // condizione:
                if (!visitato[j] &&                                                                             // 1. la citta non è stata ancora visitata
                        citta.get(id_attuale).getPercorsi().containsKey(j) &&                                   // 2. verifica se la citta è collegata con se stessa
                        citta.get(id_attuale).getPercorsi().get(j) != Costante.C0 &&                                      // 3. la distanza non sia 0
                        !Double.isInfinite(distanza[id_attuale]) &&                                             // 4. la distanza non sia infinito
                        (distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j)) < distanza[j]) {    // 5. se distanza + distanza prossima città è minore alla distanza presente nella tabella

                    distanza[j] = distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j); // aggiorna la distanza per arrivare in quella città
                    citta_precedenti[j] = id_attuale; // aggiorna l'array di citta precedenti
                }
            }
        }
        setPercorsoMinimo(citta_precedenti); // va a settare il percorso minimo
        setCarburanteTotale(distanza); // setta il carburante totale per arrivare alle rovine perdute
    }

    /**
     * Calcolo della Distanza piu breve.
     *
     * @param distanza the distanza
     * @param visitato the visitato
     * @return posizione del nodo con distanza minore
     */
    public int distanzaPiuBreve(double[] distanza, boolean[] visitato) {

        double min = Double.POSITIVE_INFINITY;
        int pos = -Costante.C1;

        for (int i = Costante.C0; i < distanza.length; i++) { // scorre l'array di distanze
            if (distanza[i] <= min && !visitato[i]) { // se la distanza all'i-esima posizione è minore del min allora va ad aggiornare
                min = distanza[i];
                pos = i;
            }
            /*else if(distanza[i] == min){
                if(i >= pos){
                    min = distanza[i];
                    pos = i;
                }
            }*/
        }
        return pos;
    }

    /**
     * Set percorso minimo.
     *
     * @param citta_precedenti citta precedenti
     */
    public void setPercorsoMinimo(int[] citta_precedenti) {

        int indice = citta_precedenti.length - Costante.C1; // parte dalle rovine perdute

        percorso_minimo.push(Citta.getCittaById(indice));

        while (citta_precedenti[indice] != -Costante.C1) { // fa fichè non arriva alla sorgente
            percorso_minimo.push(Citta.getCittaById(citta_precedenti[indice]));
            indice = citta_precedenti[indice];
        }
    }
}