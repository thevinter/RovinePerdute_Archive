package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.ArrayList;

public class Cammino {
	
	private ArrayList<Villaggio> listaVillaggi;
	private double costo;
	
	public Cammino() {
		listaVillaggi=new ArrayList<Villaggio>();
	}
	
	public Cammino(Cammino cammino) {
		listaVillaggi=new ArrayList<Villaggio>();
		for(int i=0; i<cammino.getListaVillaggi().size();i++) {
			listaVillaggi.add(cammino.getListaVillaggi().get(i));
		}
	}
	
	
	public ArrayList<Villaggio> getListaVillaggi() {
		return listaVillaggi;
	}
	public void setListaVillaggi(ArrayList<Villaggio> listaVillaggi) {
		this.listaVillaggi = listaVillaggi;
	}
	
	
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
	public void aggiungiVillaggio(Villaggio v) {
		this.listaVillaggi.add(v);
	}
	
	/**
	 * Imposta un valore al costo del cammino pari alla somma dei costi di tutti gli spostamenti da un villaggio all'altro sui rispettivi sentieri
	 * @param v  è il veicolo utilizzato per percorrere il cammino, questo determina i costi dei singoli sentieri(in quanto veicoli diversi consumano diversamente in 
	 */
	public void aggiornaCosto(Veicolo v) {
		
		double costoAppoggio=0;
		for(int i=0; i<listaVillaggi.size()-1;i++) {
			Sentiero s = new Sentiero(listaVillaggi.get(i),listaVillaggi.get(i+1));
			costoAppoggio += s.getCostoSentiero(v);
		}
		
		this.costo=costoAppoggio;
	}
	
}
