package glisprogrammatori.rovineperdute;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * La classe <strong>Nodo</strong> mi permette di creare oggetti che
 * rappresentano un qualsiasi nodo di un grafo pesato
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class Nodo {
  /**
   * Indica l'<strong>id</strong> univoco del <em>nodo</em>
   */
  private int id;
  /**
   * Indica tutti gli <strong>archi pesati</strong> del <em>nodo</em>
   */
  private Map<Integer, Integer[]> archi;
  private int from_id;
  private long dist_origine;

  /**
   * Crea un oggetto della classe <strong>Nodo</strong> specificando l'<em>id</em>
   * e gli <em>archi</em>
   * 
   * @param id    Indica l'id del <em>nodo</em>
   * @param archi Indica gli archi del <em>nodo</em>
   */
  public Nodo(int id, HashMap<Integer, Integer[]> archi) {
    this.id = id;
    this.archi = archi;
  }

  /**
   * Crea un oggetto della classe <strong>Nodo</strong> specificando l'<em>id</em>
   * e inizializzando gli <em>archi</em> come una HashMap vuota
   * 
   * @param id Indica l'id del <em>nodo</em>
   */
  public Nodo(int id) {
    this.id = id;
    archi = new HashMap<Integer, Integer[]>();
  }

  /**
   * Restituisce l'<strong>id</strong> del <em>nodo</em>
   * 
   * @return Un <code>int</code> rappresentante l'<strong>id</strong>
   */
  public int getId() {
    return id;
  }

  /**
   * Restituisce gli <strong>archi</strong> del <em>nodo</em>
   * 
   * @return Una <code>HashMap</code> rappresentante gli <strong>archi</strong>
   */
  public Map<Integer, Integer[]> getArchi() {
    return archi;
  }

  public int getFrom_id() {
    return from_id;
  }

  public long getDist_origine() {
    return dist_origine;
  }

  /**
   * Imposta gli <strong>archi</strong> del <em>nodo</em>
   * 
   * @param archi Valore da assegnare agli <strong>archi</strong>
   */
  public void setArchi(HashMap<Integer, Integer[]> archi) {
    this.archi = archi;
  }

  public void setFrom_id(int from_id) {
    this.from_id = from_id;
  }

  public void setDist_origine(long dist_origine) {
    this.dist_origine = dist_origine;
  }

  /**
   * Inserisce un nuovo arco nella HashMap specificando il <strong>nodo</strong> a
   * cui se collega e il <strong>peso</strong> dell'arco
   * 
   * @param nodo Indica il nodo a cui si collega <em>questo nodo</em>
   * @param peso Indica il peso dell'arco
   */
  public void aggiungiArco(Integer id_nodo, Integer[] peso) {
    archi.put(id_nodo, peso);
  }

  /**
   * Rimuove l'arco collegato al <strong>nodo</strong> specificato dalla HashMap
   * 
   * @param nodo Indica il nodo a cui l'arco da rimuovere è collegato
   */
  public void rimuoviArco(Integer id_nodo) {
    archi.remove(id_nodo);
  }

  /**
   * Verifica se <strong>questo nodo</strong> e <strong>obj</strong> sono uguali
   * 
   * @param obj Indica l'oggetto da confrontare con <strong>questo nodo</strong>
   * @return Un <code>boolean</code> che rappresenta se i due oggetti sono uguali
   *         o no
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof String) {
      Nodo nodo_obj = (Nodo) obj;

      if (nodo_obj.getId() == id)
        return true;
    }

    return false;
  }
}
