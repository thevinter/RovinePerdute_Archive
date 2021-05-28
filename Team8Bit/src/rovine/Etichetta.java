package rovine;

public class Etichetta {
    private Nodo from;
    private double distanza;
    private int numeroCitta;

    /**
     * costruttore della classe etichetta
     * @param from nodo di provenienza
     * @param distanza distanza tra il nodo di riferimento e from
     * @param numeroCitta numero delle città attraversate
     */
    public Etichetta(Nodo from, double distanza, int numeroCitta) {
        this.from = from;
        this.distanza = distanza;
        this.numeroCitta = numeroCitta;
    }

    /**
     * @return ritorna il nodo di provenienza
     */
    public Nodo getFrom() {
        return from;
    }

    /**
     * @return ritorna la distanza tra i 2 nodi
     */
    public double getDistanza() {
        return distanza;
    }

    /**
     * @return ritorna il numero di città attraversate
     */
    public int getNumeroCitta() {
        return numeroCitta;
    }
}
