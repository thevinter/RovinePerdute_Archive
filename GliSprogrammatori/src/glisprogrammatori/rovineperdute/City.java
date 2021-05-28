package glisprogrammatori.rovineperdute;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * La classe <strong>Citta</strong> mi permette di creare oggetti che
 * rappresentano una qualsiasi città
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class City extends Nodo {
  /**
   * Indica il <strong>nome</strong> della <em>città</em>
   */
  private String name;
  /**
   * Indica la <strong>posizione</strong> della <em>città</em>
   */
  private Punto posizione;

  /**
   * Crea un oggetto della classe <strong>Citta</strong> specificando
   * l'<em>id</em>, gli <em>archi</em> che la collegano ad altre città, il
   * <em>nome</em> e la <em>posizione</em>
   * 
   * @param id        Indica l'id della <em>città</em>
   * @param archi     Indica gli archi della <em>città</em>
   * @param nome      Indica ilo nome della <em>città</em>
   * @param posizione Indica la posizione della <em>città</em>
   */
  public City(int id, HashMap<Integer, Integer[]> archi, String nome, Punto posizione) {
    super(id, archi);
    this.name = nome;
    this.posizione = posizione;
  }

  /**
   * Crea un oggetto della classe <strong>Citta</strong> specificando
   * l'<em>id</em>, il <em>nome</em> e la <em>posizione</em>
   * 
   * @param id        Indica l'id della <em>città</em>
   * @param nome      Indica ilo nome della <em>città</em>
   * @param posizione Indica la posizione della <em>città</em>
   */
  public City(int id, String nome, Punto posizione) {
    super(id);
    this.name = nome;
    this.posizione = posizione;
  }

  public City(int id, String nome, int x, int y, int h) {
    super(id);
    this.name = nome;
    this.posizione = new Punto(x, y, h);
  }

  public City() {
    super(-1);
    name = "Nome";
    posizione = new Punto(0, 0, 0);
  }

  /**
   * Restituisce il <strong>nome</strong> della <em>città</em>
   * 
   * @return Una <code>String</code> rappresentante il <strong>nome</strong>
   */
  public String getNome() {
    return name;
  }

  /**
   * Restituisce la <strong>posizione</strong> della <em>città</em>
   * 
   * @return Un <code>Punto</code> rappresentante la <strong>posizione</strong>
   */
  public Punto getPosizione() {
    return posizione;
  }

  /**
   * Imposta il <strong>nome</strong> della <em>città</em>
   * 
   * @param nome Valore da assegnare al <strong>nome</strong>
   */
  public void setNome(String nome) {
    this.name = nome;
  }

  /**
   * Imposta la <strong>posizione</strong> della <em>città</em>
   * 
   * @param posizione Valore da assegnare alla <strong>posizione</strong>
   */
  public void setPosizione(Punto posizione) {
    this.posizione = posizione;
  }

  @Override
  public String toString() {
    String citta_toString = String.format("Citta: [\n\tId: %d,\n\tNome: %s,\n\t%s\n]", getId(), name,
        posizione.toString());

    /*
     * String citta_toString =
     * String.format("Citta: [\n\tId: %d,\n\tNome: %s,\n\t%s,\n\tArchi: [\n\t\t",
     * getId(), name, posizione.toString()); int count = 0;
     * 
     * for (Map.Entry<Integer, Integer[]> entry : getArchi().entrySet()) { Integer[]
     * dist = entry.getValue();
     * 
     * citta_toString += String.format("To: %d,\n\t\tDist: %d|%d", entry.getKey(),
     * dist[0], dist[1]);
     * 
     * if (count < (getArchi().size() - 1)) citta_toString += ",\n\t\t"; else
     * citta_toString += "\n\t";
     * 
     * count++; }
     * 
     * citta_toString += "]\n]";
     */

    return citta_toString;
  }
}
