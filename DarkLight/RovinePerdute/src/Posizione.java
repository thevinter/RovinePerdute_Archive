/**
 * Classe che rappresenta la poszione di una città tramite le sue coordinate.
 */
public class Posizione {

    private int x,y,z; //coordinate


    /**
     * Metodo costruttore che inizializza le coordinate.s
     * @param x
     * @param y
     * @param z
     */
    public Posizione(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Metodo che ritorna la coordinata x
     * @return x
     */
    public int getX() {
        return x;
    }


    /**
     * Metodo che setta la coordinata x
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * Metodo che ritorna la coordinata y
     * @return y
     */
    public int getY() {
        return y;
    }


    /**
     * Metodo che setta la coordinata y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Metodo che ritorna la coordinata z
     * @return z
     */
    public int getZ() {
        return z;
    }


    /**
     * Metodo che setta la coordinata z
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }
}
