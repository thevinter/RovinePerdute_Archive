import java.util.ArrayList;

/**
 * Classe che rappresenta una città letta dal file XML
 */
public class Citta {

    private int id; //id della città
    private String nome; //nome della città
    private Posizione posizione; //oggetto di classe Posizione
    private ArrayList<Integer> link; //arraylist contenente gli id delle città connesse


    /**
     * Metodo costruttore che inizializza gli attributi di città
     * @param id
     * @param nome
     * @param x
     * @param y
     * @param z
     */
    public Citta(int id, String nome, int x, int y, int z) {
        this.id = id;
        this.nome = nome;
        this.posizione = new Posizione(x,y,z);
        this.link = new ArrayList<Integer>();
    }

    /**
     * Metodo che ritorna l'id della città
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * Metodo che setta l'id della città
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Metodo che ritorna il nome della città
     * @return
     */
    public String getNome() {
        return nome;
    }


    /**
     * Metodo che setta il nome della città
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Metodo che ritorna l'oggetto posizione della città
     * @return
     */
    public Posizione getPosizione() {
        return posizione;
    }


    /**
     * Metodo che setta l'oggetto posizione della città
     * @param posizione
     */
    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }


    /**
     * Metodo che ritorna l'arraylist link della città
     * @return
     */
    public ArrayList<Integer> getLink() {
        return link;
    }


    /**
     * Metodo che setta l'arraylist link della città
     * @param link
     */
    public void setLink(ArrayList<Integer> link) {
        this.link = link;
    }


}
