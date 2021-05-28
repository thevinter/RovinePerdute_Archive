package RovinePerdute;

public class Nodo extends Coordinata
{
	private int id;
	private String nome;

	/**
	 * Costruttore dei nodi, mi appoggio a coordinata: RovinePerdute.Nodo è un'evoluzione con ID e nome di RovinePerdute.Coordinata
	 * @param x x
	 * @param y y
	 * @param h h
	 * @param id id
	 * @param nome nome della città
	 */
	public Nodo(int x, int y, int h, int id, String nome)
	{
		super(x,y,h);
		this.id=id;
		this.nome=nome;
	}

	// getters di nome e id
	public String getNome()
	{
		return nome;
	}

	public int getId()
	{
		return id;
	}
}
