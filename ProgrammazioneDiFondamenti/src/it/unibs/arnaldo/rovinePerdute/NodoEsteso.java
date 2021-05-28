package it.unibs.arnaldo.rovinePerdute;
//implements Comparable<NodoEsteso>
public class NodoEsteso {

    //attributi
    private final Luogo luogo_corrente;
    private Luogo luogo_precedente;
    private double carburante_utilizzato;
    private double carburante_stimato;

    /**
     * <h3>Metodo per la creazione <b>iniziale</b> di un nodo del grafo, infatti il metodo vuole solo il nodo corrente, senza le informazioni aggiuntive.<br>
     *     Questo metodo verrà invocato durante la visita inizale al nodo</h3>
     * @param luogo_corrente ovvero l'unico parametro che rappresenta il luogo del nodo da analizzare
     */
    public NodoEsteso(Luogo luogo_corrente) {
        this.luogo_corrente = luogo_corrente;
        this.luogo_precedente = null;
        this.carburante_utilizzato = Double.POSITIVE_INFINITY;
        this.carburante_stimato = Double.POSITIVE_INFINITY;
    }

    /**
     * <h3>Metodo per la creazione <b>completa</b> del nodo del grafo, con tutte le informazioni a riguardo</h3>
     * @param luogo_corrente luogo su cui ci troviamo
     * @param luogo_precedente luogo precedente al luogo corrente
     * @param carburante_utilizzato carburante utilizzato fino ad arrivare al luogo corrente
     * @param carburante_stimato carburante stimato fino ad arrivare a destinazione
     */
    public NodoEsteso(Luogo luogo_corrente, Luogo luogo_precedente, double carburante_utilizzato, double carburante_stimato) {
        this.luogo_corrente = luogo_corrente;
        this.luogo_precedente = luogo_precedente;
        this.carburante_utilizzato = carburante_utilizzato;
        this.carburante_stimato = carburante_stimato;
    }

    //GET E SET
    public Luogo getLuogo_corrente() {
        return luogo_corrente;
    }

    public Luogo getLuogo_precedente() {
        return luogo_precedente;
    }

    public void setLuogo_precedente(Luogo luogo_precedente) {
        this.luogo_precedente = luogo_precedente;
    }

    public double getCarburante_utilizzato() {
        return carburante_utilizzato;
    }

    public void setCarburante_utilizzato(double carburante_utilizzato) {
        this.carburante_utilizzato = carburante_utilizzato;
    }

    public double getCarburante_stimato() {
        return carburante_stimato;
    }

    public void setCarburante_stimato(double carburante_stimato) {
        this.carburante_stimato = carburante_stimato;
    }

    /**
     * <h3>Metodo per la comparazione del carburante stimato tra due Luoghi(nodi) per arrivare a destinazione</h3>
     * @param nodo_da_comparare altro nodo da valutare
     * @return un intero in base a quale nodo utilizza meno carburante, ritornerà:<br>
     *         1 - se il nodo implicito utilizza meno carburante <br>
     *        -1 - se il nodo passato come paramentro utilizza meno carburante <br>
     *         0 - se utilizzano lo stesso carburante per arrivare alla fine
     */
 /*    @Override
    public int compareTo(NodoEsteso nodo_da_comparare) {
        if (this.carburante_stimato > nodo_da_comparare.carburante_stimato) {
            return 1;
        } else if (this.carburante_stimato < nodo_da_comparare.carburante_stimato) {
            return -1;
        } else if (this.getLuogo_corrente().getId() > nodo_da_comparare.getLuogo_corrente().getId()){
            return -1;
        }else if(this.getLuogo_corrente().getId() < nodo_da_comparare.getLuogo_corrente().getId()){
            return 1;
        }else return 0;
    }

  */
}
