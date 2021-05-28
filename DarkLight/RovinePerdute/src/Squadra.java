import java.util.ArrayList;

/**
 * Classe che rappresenta una squadra di ricerca.
 */
public class Squadra {

    private String nome; //nome squadra
    private int tipoVeicolo; //tipo di consumo carburante del veicolo (0 se consuma in base a x,y; 1 se consuma in base a z)
    private double carburanteConsumato; //totale carburante consumato per il percorso
    private Percorso percorso; //oggetto di classe Percorso


    /**
     * Metodo costruttore che inizializza nome, tipoVeicolo e percorso
     * @param nome
     * @param tipoVeicolo
     * @param citta
     */
    public Squadra(String nome, int tipoVeicolo, ArrayList<Citta> citta) {
        setNome(nome);
        setTipoVeicolo(tipoVeicolo);
        percorso = new Percorso(citta);
    }


    /**
     * Metodo che ritorna il nome della squadra
     * @return nome
     */
    public String getNome() {
        return nome;
    }


    /**
     * Metodo che setta il nome della squadra
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Metodo che ritorna il tipoVeicolo della squadra
     * @return tipoVeicolo
     */
    public int getTipoVeicolo() {
        return tipoVeicolo;
    }


    /**
     * Metodo che setta il tipoVeicolo della squadra
     * @param tipoVeicolo
     */
    public void setTipoVeicolo(int tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
    }


    /**
     * Metodo che ritorna l'oggetto percorso
     * @return
     */
    public Percorso getPercorso() {
        return percorso;
    }


    /**
     * Metodo che ritorna il carburante consumato
     * @return carburanteConsumato
     */
    public double getCarburanteConsumato() {
        return carburanteConsumato;
    }


    /**
     * Metodo che setta il carburante consumato richiamando il metodo che crea anche il percorso meno dispendioso.
     */
    public void setCarburanteConsumato() {
        this.carburanteConsumato = percorso.creaRotta(tipoVeicolo);
    }


}
