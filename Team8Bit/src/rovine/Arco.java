package rovine;

public class Arco {
    private Nodo nodoPartenza;
    private Nodo nodoArrivo;

    /**
     * costruttore della classe arco
     * @param nodoPartenza nodo da cui parte l'arco
     * @param nodoArrivo nodo da cui arriva l'arco
     */
    public Arco(Nodo nodoPartenza, Nodo nodoArrivo) {
        this.nodoPartenza = nodoPartenza;
        this.nodoArrivo = nodoArrivo;
    }

    /**
     * confronta 2 oggetti tramite il metodo toSting
     * @param o oggetto da confrontare
     * @return ritorna true o false in base a se coincidono o meno
     */
    @Override
    public boolean equals(Object o){
        return this.toString().equals(o.toString());
    }

    /**
     * metodo per generare una stringa in riferimento alla classe arco
     * @return ritona una stringa formata dall'id del primo nodo, seguita dall'id del secondo nodo ed entrambe separate da "-"
     */
    @Override
    public String toString(){
        return this.nodoPartenza.getId()+"-"+this.nodoArrivo.getId();
    }

    /**
     * ritorna l'hashCode della stringa del metodo toString
     * @return
     */
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }
}
