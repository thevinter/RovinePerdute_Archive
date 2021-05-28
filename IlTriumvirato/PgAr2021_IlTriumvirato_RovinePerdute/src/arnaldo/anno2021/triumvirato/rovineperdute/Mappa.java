package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mappa {
	
	private ArrayList <Villaggio> villaggi;
	private ArrayList <Sentiero> sentieri;
	private Map<Integer,Integer> listaPosizioni;
	
	public Mappa(){
		super();
		villaggi=new ArrayList<Villaggio>();
		sentieri=new ArrayList<Sentiero>();
		listaPosizioni=new HashMap<Integer,Integer>();
	}
	
	
	public ArrayList<Villaggio> getVillaggi() {
		return villaggi;
	}
	public void setVillaggi(ArrayList<Villaggio> villaggi) {
		this.villaggi = villaggi;
	}
	
	
	public ArrayList<Sentiero> getSentieri() {
		return sentieri;
	}
	public void setSentieri(ArrayList<Sentiero> sentieri) {
		this.sentieri = sentieri;
	}
	
	/***
	 * Ritorna il villaggio che corrisponde al campo base, viene chiamato solo dopo che avviene l'ordinamento
	 * @return
	 */
	public Villaggio getStartCamp() { //must be sorted, it always is after input method
		return this.villaggi.get(0);
	}
	
	
	
	/***
	 * Ritorna il villaggio che corrisponde alle rovine perdute, viene chiamato solo dopo che avviene l'ordinamento
	 * @return
	 */
	public Villaggio getLostRuins() { //must be sorted, it always is after input method
		return this.villaggi.get(this.villaggi.size()-1);
	}
	
	public void sortMappa() {
		Collections.sort(this.villaggi, new VillaggioComparator());
		Collections.sort(this.sentieri, new SentieroComparator());
	}
	
	/***
	 * Fa una ricerca dicotomica per trovare il villaggio di id indicato, servirà per aggiornare le informazioni dei sentieri, fa uso di un'hashmap per velocizzare il processo
	 * @param idToFind è l'id del villaggio da individuare
	 * @return ritorna il villaggio dell'id indicato
	 */	
	public Villaggio findCityById(int idToFind) {
		//dev'essere chiamato solo se l'array villaggi è ordinato in base all'id
		Villaggio toReturn=null;
		
		Integer position=listaPosizioni.get(idToFind);
		
		if(position!=null) {
			toReturn=this.villaggi.get(position);
			
		}else {
			
			int start=0;
			int end=villaggi.size();
			int pos=(start+end)/2;
			
			while(villaggi.get(pos).getId()!=idToFind&&start!=end) {
					
				if(villaggi.get(pos).getId()<idToFind) {
					start=pos;
					pos=(start+end)/2;
				}else if(villaggi.get(pos).getId()>idToFind){
					end=pos;
					pos=(start+end)/2;
				}
			}
			
			//giusto per sicurezza
			if(start==end) {
				pos=start;
			}

			listaPosizioni.put(idToFind, pos);
			toReturn=villaggi.get(pos);
		}
		
		return toReturn;
	}
	


	/***
	 * Aggiorna le informazioni dei sentieri(da solo l'id di partenza e destinazione ai riferimenti ai villaggi di partenza e destinazione), aggiungendo inoltre a ogni villaggio la lista di sentieri che partono da esso
	 */
	public void aggiornaSentieri() {
		int lastCheckedPosition=0; //con questo non devo ciclare ogni volta per trovare dove iniziano i sentieri che partono da questo villaggio
		
		for(int i=0;i<villaggi.size();i++) {
			
			Villaggio attuale=villaggi.get(i);
			
			 
			for(int j=lastCheckedPosition;j<sentieri.size();j++) {
				 								 
				if(sentieri.get(j).getIdPartenza()!=attuale.getId()) {
					
					lastCheckedPosition=j;
					break;
					
				}else {
					
					int idAltroVillaggio=sentieri.get(j).getIdDestinazione();
					
					Villaggio altroVillaggio=this.findCityById(idAltroVillaggio);
					
					sentieri.get(j).aggiornaSentiero(attuale,altroVillaggio);
					attuale.getCollegamenti().add(sentieri.get(j));
				}
			}
			
		}
	}
	
	
	/***
	 * Questo metodo riorganizza la mappa in maniera da renderla più utile per l'algoritmo di calcolo dei cammini minimi: ordina i villaggi e riaggiorna le informazioni dei sentieri, aggiungendo inoltre a ogni villaggio la lista di sentieri che partono da esso
	 */
	public void preparaMappa() {
		//dovrebbe essere già ordinata, ma per sicurezza la sistema
		this.sortMappa();
		this.aggiornaSentieri();
	}
	
	public void resetDijkstra() {
		for(int i=0;i<this.villaggi.size();i++) {
			villaggi.get(i).resetDijkstra();
		}
	}
}
