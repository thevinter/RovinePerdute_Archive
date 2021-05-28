package rovine;

public class Nodo {
    private String name;
    private int id;
    private int x;
    private int y;
    private int h;

    /**
     * costruttore della classe nodo date ke coordinate, l'id e il nome del nodo
     * @param name nome del nodo
     * @param id id del nodo
     * @param x coordinata x del nodo
     * @param y coordinata y del nodo
     * @param h coordinata h del nodo
     */
    public Nodo(String name, int id, int x, int y, int h) {
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.h = h;
    }

    /**
     * costruttore del nodo dato SOLO l'id
     * @param id id del nodo
     */
    public Nodo(int id){
        this.id=id;
        this.name="";
        this.x=0;
        this.y=0;
        this.h=0;
    }

    /**
     * @return ritorna il nome del nodo
     */
    public String getName() {
        return name;
    }

    /**
     * data una stringa imposta il nome del nodo
     * @param name stringa da impostare
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ritorna l'id del nodo
     */
    public int getId() {
        return id;
    }

    /**
     * dato un id, imposta un nuovo id al nodo
     * @param id id da impostare
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ritorna la coordinata x del nodo
     */
    public int getX() {
        return x;
    }

    /**
     * data una coordinata x, imposta la nuova coordinata
     * @param x coordinata da impostare
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return ritorna la coordinata y del nodo
     */
    public int getY() {
        return y;
    }

    /**
     * data una coordinata y, imposta la nuova coordinata
     * @param y coordinata da impostare
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return ritorna la coordinata h del nodo
     */
    public int getH() {
        return h;
    }

    /**
     * data una coordinata h, imposta la nuova coordinata
     * @param h coordinata da impostare
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * confronta se 2 oggetti sono uguali in base al metodo hashCode
     * @param o oggetto da confrontare
     * @return ritorna true o false in base a se coincidono o meno
     */
    @Override
    public boolean equals(Object o){
        return this.hashCode()==o.hashCode();
    }

    /**
     * @return ritorna l'id del nodo
     */
    @Override
    public int hashCode(){
        return this.id;
    }

    /**
     * dato un altro nodo, calcola la distanza tra se stesso e quel nodo in base alle coordinate x e y
     * @param nodoArrivo nodo da confrontare
     * @return ritorna la distanza calcolata
     */
    public double distanzaNelPianoXY(Nodo nodoArrivo){
        double differenzaX = this.getX() - nodoArrivo.getX();
        double differenzaY = this.getY() - nodoArrivo.getY();
        double distanza = Math.sqrt((differenzaX * differenzaX) + (differenzaY * differenzaY));
        return distanza;
    }

    /**
     * dato un altro nodo, calcola la distanza tra se stesso e quel nodo in base alla coordinata h
     * @param nodoArrivo nodo da confrontare
     * @return ritorna la distanza calcolata
     */
    public double differenzaAltezze(Nodo nodoArrivo){
        return Math.abs(this.getH() - nodoArrivo.getH());
    }
}
