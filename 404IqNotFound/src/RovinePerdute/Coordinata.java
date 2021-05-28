package RovinePerdute;

public class Coordinata
{
	private int x;
	private int y;
	private int h;

	/**
	 * costruttore di coordinata
	 * @param _x  x
	 * @param _y y
	 * @param _h h
	 */
	public Coordinata(int _x, int _y, int _h){
		this.x = _x;
		this.y = _y;
		this.h = _h;
	}

	//getters e setters di x,y,h
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getH(){
		return h;
	}
}
