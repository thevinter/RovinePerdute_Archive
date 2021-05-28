package glisprogrammatori.rovineperdute;

import java.util.ArrayList;

/**
 * <p>
 * La classe <strong>Grafo</strong> mi permette di creare oggetti che
 * rappresentano un grafo pesato
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class Grafo {
  /**
   * Indica tutti i <strong>nodi</strong> del <em>grafo</em>
   */
  private ArrayList<Nodo> nodi;

  /**
   * Crea un oggetto della classe <strong>Grafo</strong> specificando i
   * <em>nodi</em> che lo compongono
   * 
   * @param nodi Indica i nodi del <em>grafo</em>
   */
  public Grafo(ArrayList<Nodo> nodi) {
    this.nodi = nodi;
  }

  /**
   * Crea un oggetto della classe <strong>Grafo</strong> inizializzando i
   * <em>nodi</em> come un ArrayList vuoto
   */
  public Grafo() {
    nodi = new ArrayList<Nodo>();
  }

  /**
   * Restituisce i <strong>nodi</strong> del <em>grafo</em>
   * 
   * @return Un <code>ArrayLIst</code> rappresentante i <strong>nodi</strong>
   */
  public ArrayList<Nodo> getNodi() {
    return nodi;
  }

  /**
   * Imposta i <strong>nodi</strong> del <em>grafo</em>
   * 
   * @param nodi Valore da assegnare ai <strong>nodi</strong>
   */
  public void setNodi(ArrayList<Nodo> nodi) {
    this.nodi = nodi;
  }

  /**
   * Inserisce un nuovo <strong>nodo</strong> nel ArrayList
   * 
   * @param nodo Indica il nodo da aggiungere al <em>grafo</em>
   */
  public boolean aggiungiNodo(Nodo nodo) {
    return nodi.add(nodo);
  }

  /**
   * Rimuove il <strong>nodo</strong> dal ArrayList
   * 
   * @param nodo Indica il nodo da rimuovere
   */
  public boolean rimuoviNodo(Nodo nodo) {
    return nodi.remove(nodo);
  }

  /**
   * Cerca un <em>nodo</em>, tramite l'<strong>id</strong>, all'interno dei nodi
   * del grafo
   * 
   * @param id Indica per cui cercare il <em>nodo</em>
   * @return Un <code>Nodo</code> rappresentante il nodo cercato, oppure
   *         <code>null</code> se non trova niente
   */
  public Nodo cercaNodoPerId(int id) {
    for (Nodo nodo : nodi) {
      if (nodo.getId() == id)
        return nodo;
    }

    return null;
  }

  /**
   * Verifica se <strong>questo grafo</strong> e <strong>obj</strong> sono uguali
   * 
   * @param obj Indica l'oggetto da confrontare con <strong>questo grafo</strong>
   * @return Un <code>boolean</code> che rappresenta se i due oggetti sono uguali
   *         o no
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Grafo) {
      Grafo grafo_obj = (Grafo) obj;

      for (Nodo nodo : grafo_obj.getNodi()) {
        if (cercaNodoPerId(nodo.getId()) == null)
          return false;
      }

    } else {
      return false;
    }

    return true;
  }
}
