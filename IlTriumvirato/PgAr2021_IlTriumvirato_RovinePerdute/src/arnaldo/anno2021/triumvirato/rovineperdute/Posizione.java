package arnaldo.anno2021.triumvirato.rovineperdute;

import it.unibs.fp.mylib.CustomMath;

public class Posizione {
	private double x;
	private double y;
	private double h;
	
	Posizione(){
		super();
		this.x = 0;
		this.y = 0;
		this.h = 0;
	}
		
	Posizione(double x, double y, double h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;
	}

	Posizione(Posizione copia){
		super();
		this.x = copia.getX();
		this.y = copia.getY();
		this.h = copia.getH();
	}
	
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	/**
	 * calcola la distanza in orizzontale
	 * @param p il punto dal quale deve calcolare la distanza
	 * @return
	 */
	public double getHorizontalDistance(Posizione p) {
		return CustomMath.pitagora(x-p.getX(), y-p.getY());
	}
	/**
	 * calcola la distanza in verticale
	 * @param p il punto dal quale deve calcolare la distanza
	 * @return
	 */
	public double getVerticalDistance(Posizione p) {
		return Math.abs(h-p.getH());
	}
	
	
}
