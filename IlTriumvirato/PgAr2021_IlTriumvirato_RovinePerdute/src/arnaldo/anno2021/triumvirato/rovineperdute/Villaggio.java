package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.ArrayList;

public class Villaggio{
	
	private int id;
	private String nome; 
	private Posizione coordinate;
	private ArrayList<Sentiero> collegamenti;
	
	private ArrayList<Integer> idCollegati;
	
	private boolean isLostRuins;
	
	private ArrayList<Integer> pathIds;
	private double dijsktraValue;
	
	
	public Villaggio() {
		this.dijsktraValue=Constants.DIJSKTRA_INFINITE_COST;
		isLostRuins=false;
	}
	
	public Villaggio(Villaggio copia) {
		super();
		this.id = copia.getId();
		this.nome = copia.getNome();
		this.coordinate = new Posizione(copia.getCoordinate());
		this.collegamenti=copia.getCollegamenti();
		this.pathIds=new ArrayList<Integer>();
		this.dijsktraValue=Constants.DIJSKTRA_INFINITE_COST;
		isLostRuins=false;
	}
	
	public Villaggio(int id, String nome, Posizione coordinate) {
		super();
		this.id = id;
		this.nome = nome;
		this.coordinate = new Posizione(coordinate);
		this.collegamenti = new ArrayList<Sentiero>();
		this.collegamenti = new ArrayList<Sentiero>();
		this.idCollegati=new ArrayList<Integer>();
		this.pathIds=new ArrayList<Integer>();
		this.dijsktraValue=Constants.DIJSKTRA_INFINITE_COST;
		isLostRuins=false;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Posizione getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Posizione coordinate) {
		this.coordinate = coordinate;
	}
	
	
	
	public double getX() {
		return coordinate.getX();
	}
	
	public double getY() {
		return coordinate.getY();
	}
	
	public double getH() {
		return coordinate.getH();
	}
	public double getDijsktraValue() {
		return dijsktraValue;
	}
	
	public void setDijsktraValue( double m) {
		 dijsktraValue= m;
	}
	

	public ArrayList<Integer> getIdCollegati() {
		return idCollegati;
	}
	public void setIdCollegati(ArrayList<Integer> idCollegati) {
		this.idCollegati = idCollegati;
	}

	//questi due metodi non vengono chiamati finché non avviene l'aggiornamento dei sentieri
	public ArrayList<Sentiero> getCollegamenti() {
		return collegamenti;
	}
	public void setCollegamenti(ArrayList<Sentiero> collegamenti) {
		this.collegamenti = collegamenti;
	}
	

	/**
	 * Serve per il calcolo dei cammini minimi, se il valore pathLength è migliore di quello attualmente salvato in dijsktraValue, ritorna true.
	 * @param pathLength è la lunghezza della strada per arrivare a questo nodo di cui si vuole sapere se sia migliore di quella attualmente salvata
	 * @return true se il percorso controllato è migliore
	 */
	public boolean nodeHasWorsePath(double pathLength) {
		return pathLength<this.dijsktraValue||this.dijsktraValue==Constants.DIJSKTRA_INFINITE_COST;
	}
	
	/**
	 * Aggiorna un nodo con un nuovo percorso ottimale(aggiornando quindi il costo per raggiungerlo)
	 * @param newDijsktraValue il nuovo costo del cammino verso questo nodo
	 * @param newPathIds la nuova lista di villaggi da attraversare per arrivare a questo nodo
	 */
	public void dijsktraOverWrite(double newDijsktraValue, ArrayList<Integer> newPathIds) {
		this.setDijsktraValue(newDijsktraValue);
		this.setPathIds(newPathIds);
	}

	/**
	 * Imposta il percorso di default per il nodo di partenza, ossia solo sé stesso
	 */
	public void setPathIds() {
		this.pathIds=new ArrayList<Integer>();
		this.pathIds.add(this.id);
	}
	
	/**
	 * Imposta il percorso fatto per arrivare a questo nodo(solo gli id)
	 */
	public void setPathIds(ArrayList<Integer> lista) {
		this.pathIds=new ArrayList<Integer>();
		for(int i=0;i<lista.size();i++) {
			pathIds.add(lista.get(i));
		}
		this.pathIds.add(this.id);
	}

	/**
	 * @return la lista degli id dei nodi percorsi per arrivare fino a questo nodo nel modo più breve possibile
	 */
	public ArrayList<Integer> getPathIds() {
		return pathIds;
	}

	/**
	 * Resetta questo nodo al valore iniziale in modo da poter ri-calcolare l'algoritmo dei cammini minimi di Dijsktra
	 */
	public void resetDijkstra() {
		dijsktraValue=Constants.DIJSKTRA_INFINITE_COST;
		pathIds=new ArrayList<Integer>();
	}
	
	/**
	 * Serve per vedere se questo villaggio corrisponde alle rovine perdute
	 * @return true se questo villaggio corrisponde alle rovine perdute
	 */
	public boolean isLostRuins() {
		return isLostRuins;
	}
	/**
	 * Serve per ad impostare che villaggio corrisponde alle rovine perdute
	 */
	public void becomeLostRuins() {
		isLostRuins=true;
		
	}
}
