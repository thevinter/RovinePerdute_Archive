package it.unibs.fp.rovineperdute;

import java.util.HashMap;

/**
 * Classe Citta, rappresenta i nodi.
 */
public class Citta {

    private int id;
    private String nome;
    private Punto coordinata;
    private HashMap<Integer, Double> percorsi;

    /**
     * Istanzia una nuova Citta.
     *
     * @param id         id
     * @param nome       nome
     * @param coordinata coordinata
     */
    public Citta(int id, String nome, Punto coordinata) {
        this.id = id;
        this.nome = nome;
        this.coordinata = coordinata;
    }

    /**
     * Get id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get percorsi.
     *
     * @return the percorsi
     */
    public HashMap<Integer, Double> getPercorsi() {
        return percorsi;
    }

    /**
     * Set percorsi.
     *
     * @param percorsi the percorsi
     */
    public void setPercorsi(HashMap<Integer, Double> percorsi) {
        this.percorsi = percorsi;
    }

    /**
     * Calcola peso percorso in base al team.
     *
     * @param team il team
     */
    public static void calcolaPesoPercorso(Team team) {
        switch (team.getVeicolo()) {

            case Costante.TEAM_NOME1: { // caso di Tonathiu
                for (int i = 0; i < Rovina.getRovina().size(); i++) { // scorre tutte le città del grafo
                    Citta citta_partenza = Rovina.getRovina().get(i); // prende la città all'i-esimo posto

                    citta_partenza.percorsi.forEach((key, value) -> { // per ogni citta va a scorrere i suoi percorsi(archi)
                        double peso; // peso dell'arco
                        Citta citta_arrivo = getCittaById(key); // citta di arrivo

                        peso = Math.sqrt(Math.pow(citta_arrivo.coordinata.getX() - citta_partenza.coordinata.getX(), Costante.C2) + Math.pow(citta_arrivo.coordinata.getY() - citta_partenza.coordinata.getY(), Costante.C2)); // calcola il peso tra l edue cittò secondo la formula "distanza tra i due punti"
                        citta_partenza.percorsi.replace(key, peso); // aggiorna il peso
                    });
                }
                break;
            }

            case Costante.TEAM_NOME2: { // caso di Metztli
                for (int i = 0; i < Rovina.getRovina().size(); i++) { // scorre tutte le città del grafo
                    Citta citta_partenza = Rovina.getRovina().get(i); // prende la città all'i-esimo posto

                    citta_partenza.percorsi.forEach((key, value) -> { // per ogni citta va a scorrere i suoi percorsi(archi)
                        double peso; // peso dell'arco
                        Citta citta_arrivo = getCittaById(key); // citta di arrivo

                        peso = Math.abs(citta_partenza.coordinata.getZ() - citta_arrivo.coordinata.getZ()); // calcola il peso tra due città in base alla coordinata z
                        citta_partenza.percorsi.replace(key, peso); // aggiorna il peso
                    });
                }
                break;
            }
        }
    }

    /**
     * Get citta by id.
     *
     * @param id_da_cercare id da cercare
     * @return citta by id
     */
    public static Citta getCittaById(int id_da_cercare) {

        Citta citta_cercato = null;

        for (int i = 0; i < Rovina.getRovina().size(); i++) { // va a scorrere tutte le città del grafo
            Citta citta_attuale = Rovina.getRovina().get(i); // prende la città all'i-esimo
            if (citta_attuale.id == id_da_cercare) citta_cercato = citta_attuale; // se l'id della citta corrisponde all'id cercato aggiorna la variabile citta_cercato
        }

        return citta_cercato;
    }
}