package RovinePerdute.unibs.it;
import java.*;
import java.util.LinkedList;

public class Citta {

    private int X;
    private int Y;
    private int H;
    private String Nome;
    private int ID;
    private LinkedList<Integer> collegatoCon = new LinkedList<>();

    public Citta(int x, int y, int h, String nome, int id){
        this.X = x;
        this.Y = y;
        this.H = h;
        this.Nome = nome;
        this.ID = id;
    }

    public Citta(){
    }

    public void setX(int x){
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setH(int h) {
        this.H = h;
    }

    public void setNome(String nome) {
       this.Nome = nome;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LinkedList<Integer> getCollegatoCon() {
        return collegatoCon;
    }

    public void setCollegatoCon(LinkedList<Integer> collegatoCon) {
        this.collegatoCon = collegatoCon;
    }
    public void addDestinazione(int dest){
        this.collegatoCon.add(dest);
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getH() {
        return H;
    }

    public String getNome() {
        return Nome;
    }

    public int getID() {
        return ID;
    }

    public double distTonatiuh(Citta citta2){
        double x = this.getX() - citta2.getX();
        double y = this.getY() - citta2.getY();
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public double distMetzli(Citta citta2){
        return this.getH() - citta2.getH();
    }




    @Override
    public String toString() {
        return "Citta{" + "X=" + X + ", Y=" + Y + ", H=" + H + ", Nome='" + Nome + '\'' + ", ID=" + ID +
                ", collegato con" +
                '}';
    }

    }
