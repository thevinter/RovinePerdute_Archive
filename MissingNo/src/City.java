import java.util.ArrayList;

/**Questa classe descrive una città
 * @author Giovanni
 *
 */
public class City {
	
	private int x;
	private int y;
	private int h;
	private String name;
	private String links;
	private int linkCounter;
	private ArrayList<Integer> linksArray= new ArrayList<Integer>();
	public City(int x, int y, int h, String name, String links,int linkCounter) {
		
		this.x=x;
		this.y=y;
		this.h=h;
		this.name=name;
		this.links=links;
		this.linkCounter=linkCounter;
		setLinkArray();
	}
	
	/**Ritorna la coordinata x
	 * @return x
	 */
	public int getX() {
		return x;
	}




	/**Ritorna la coordinata y
	 * @return y
	 */
	public int getY() {
		return y;
	}




	/**Ritorna la coordinata h (chi poi la chiama h? dovrebbe essere z)
	 * @return h
	 */
	public int getH() {
		return h;
	}




	/**Ritorna il nome della città
	 * @return
	 */
	public String getName() {
		return name;
	}




	/**Ritorna l'insieme di indici di città a cui questa città è collegata
	 * @return ArrayList<Integer> collegamenti
	 */
	public ArrayList<Integer> getLinksArray() {
		return linksArray;
	}
	

	/**
	 * Perchè lavorare con gli ArrayList è difficile
	 */
	private void setLinkArray() {
		String[] parts = links.split("-");
		
		for (int i=0; i<linkCounter; i++) {
			linksArray.add(Integer.parseInt(parts[i]));
		}
	}



	/**
	 * il tuo classico toString()
	 */
	public String toString() {
		String s = "";
		s += x + " " + y + " " + h +" " + name + " " + linksArray;
		return s;
	}

}
