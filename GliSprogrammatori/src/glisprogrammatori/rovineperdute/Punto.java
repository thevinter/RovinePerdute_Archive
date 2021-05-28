package glisprogrammatori.rovineperdute;

/**
 * <p>
 * La classe <strong>Punto</strong> mi permette di creare oggetti che
 * rappresentano un qualsiasi punto del piano
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class Punto {
  /**
   * Indica la coordinata <strong>x</strong> del <em>punto</em>
   */
  private int x;
  /**
   * Indica la coordinata <strong>y</strong> del <em>punto</em>
   */
  private int y;
  /**
   * Indica l'<strong>altitudine</strong> del <em>punto</em>
   */
  private int h;

  /**
   * Crea un oggetto della classe <strong>Punto</strong> specificando le
   * coordinate <em>x</em>, <em>y</em> e l'<em>altitudine</em>
   * 
   * @param x Indica la x del <em>punto</em>
   * @param y Indica la y del <em>punto</em>
   * @param h Indica l'altitudine del <em>punto</em>
   */
  public Punto(int x, int y, int h) {
    this.x = x;
    this.y = y;
    this.h = h;
  }

  /**
   * Restituisce la coordinata <strong>x</strong> del <em>punto</em>
   * 
   * @return Un <code>int</code> rappresentante la <strong>x</strong>
   */
  public int getX() {
    return x;
  }

  /**
   * Restituisce la coordinata <strong>y</strong> del <em>punto</em>
   * 
   * @return Un <code>int</code> rappresentante la <strong>y</strong>
   */
  public int getY() {
    return y;
  }

  /**
   * Restituisce l'<strong>altitudine</strong> del <em>punto</em>
   * 
   * @return Un <code>int</code> rappresentante l<strong>altitudine</strong>
   */
  public int getH() {
    return h;
  }

  /**
   * Imposta la coordinata <strong>x</strong> del <em>punto</em>
   * 
   * @param x Valore da assegnare alla <strong>x</strong>
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Imposta la coordinata <strong>y</strong> del <em>punto</em>
   * 
   * @param y Valore da assegnare alla <strong>y</strong>
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Imposta l'<strong>altitudine</strong> del <em>punto</em>
   * 
   * @param h Valore da assegnare all'<strong>altitudine</strong>
   */
  public void setH(int h) {
    this.h = h;
  }

  /**
   * Calcola la distanza euclidea tra <strong>questo punto</strong> e
   * <strong>punto</strong>
   * 
   * @param punto Indica l'altro punto
   * @return Un <code>int</code> rappresentante la distanza euclide tra i due
   *         punti
   */
  public int calcolaDistanzaEuclidea(Punto punto) {
    return (int) Math.sqrt(Math.pow((punto.getX() - x), 2) + Math.pow((punto.getY() - y), 2));
  }

  /**
   * Calcola la differenza tra l'<em>altitudine</em> di <strong>questo
   * punto</strong> e l'<em>altitudine</em> di <strong>punto</strong>
   * 
   * @param punto Indica l'altro punto
   * @return Un <code>int</code> rappresentante la differenza tra le altitudini
   *         dei due punti
   */
  public int calcoloDifferenzaDiAltitudine(Punto punto) {
    return Math.abs(punto.getH() - h);
  }

  /**
   * Verifica se <strong>questo punto</strong> e <strong>obj</strong> sono uguali
   * 
   * @param obj Indica l'oggetto da confrontare con <strong>questo punto</strong>
   * @return Un <code>boolean</code> che rappresenta se i due oggetti sono uguali
   *         o no
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Punto) {
      Punto punto_obj = (Punto) obj;

      if (punto_obj.getX() == x && punto_obj.getY() == y && punto_obj.getH() == h)
        return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return String.format("Punto: [x: %d, y: %d, h: %d]", x, y, h);
  }
}
