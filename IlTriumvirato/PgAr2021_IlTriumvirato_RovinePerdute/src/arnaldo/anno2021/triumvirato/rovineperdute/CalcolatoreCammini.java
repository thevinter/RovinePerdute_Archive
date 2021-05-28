package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CalcolatoreCammini {
	
	/**
	 * calcola il cammino minimo per raggiungere la citt� perduta
	 * @param mappaTotale � la mappa delle citt�
	 * @param veicoloTeam � il veicolo utilizzato per percorrere il cammino
	 * @return  ritorna il cammino minimo trovato
	 */
	public static Cammino calcolaCamminoMinimo(Mappa mappaTotale, Veicolo veicoloTeam) {
		
		//fa partire un metodo che serve a lanciare(con la dovuta inizializzazione) il calcolo dei cammini minimi
		iniziaEsplorazione(mappaTotale,veicoloTeam);
		Cammino daRitornare=new Cammino();
			
		ArrayList<Integer> lista=mappaTotale.getLostRuins().getPathIds();
		
		for(int i=0;i<lista.size();i++) {
			daRitornare.aggiungiVillaggio(mappaTotale.getVillaggi().get(lista.get(i)));
		}
		
		return daRitornare;
	}
	
	
	

	/**
	 * 
	 * @param mappaTotale � la mappa delle citt�
	 * @param veicoloTeam � il veicolo utilizzato per percorrere il cammino
	 */
	private static void iniziaEsplorazione(Mappa mappaTotale,Veicolo v_utilizzato) {
		
		//abbiamo optato per differenziare cos� gli spostamenti per facilitare il compareTo per utilizzare il treeSet
		if(v_utilizzato.getMezzo()==TipologiaSpostamento.ORIZZONTALE) {
            Sentiero.veicoloOrizzontale=true;
        }else {
            Sentiero.veicoloOrizzontale=false;
        }

		//inizializza il campo base con percorso nullo e distanza nulla
		Villaggio campoBase=mappaTotale.getStartCamp();
		campoBase.setDijsktraValue(0);
		campoBase.setPathIds();
		//crea un percorso di appoggio da 0 a 0, con costo 0(che non verr� poi pi� utilizzato se non nella prima iterazione del ciclo principale del calcolo del percorso ottimale)
		Sentiero firstPath=new Sentiero(campoBase,campoBase);

		
		esploraPercorsi(firstPath,mappaTotale.getLostRuins(),v_utilizzato);
	}
	
	

	/**
	 * Calcola il percorso ottimale per raggiungere la citt� perduta, il percorso sar� salvato in forma di lista di identificativi interi nel villaggio che rappresenta la citt� perduta.<br>
	 * Per calcolarlo utilizza un treeSet, che andr� a contenere tutte le varie "opzioni" che ha come cammino da percorrere e che continua ad essere aggiornato con tutte le opzioni finch� eventualmente non le terminer� e si trover� col percorso ottimale.
	 */
	private static void esploraPercorsi(Sentiero firstPath,Villaggio theLostRuins,Veicolo v_utilizzato) {

		TreeSet<Sentiero> treeSentiero= new TreeSet<>();
		treeSentiero.add(firstPath);
		
		//finch� ha ancora possibili strade da percorrere continua ad eseguire
		while(!treeSentiero.isEmpty()) {
			
			//preleva la prima strada dal treeSet e si segna i parametri del nodo al termine, gli serviranno per aggiungere al treeSet tutti i possibili percorsi da quel nodo(se convenienti)
			Sentiero stradaPercorsa=treeSentiero.pollFirst();
			Villaggio currentNode=stradaPercorsa.getDestinazione();
			double nodeCost=currentNode.getDijsktraValue();
			
			//per ogni sentiero che parte dal nodo currentNode, lo aggiunger� al treeSet se � conveniente(aggiornando i dijsktraValue dei singoli nodi=villaggi)
			for(int i=0;i<currentNode.getCollegamenti().size();i++) {
				
				Sentiero pathWay=currentNode.getCollegamenti().get(i); //percorso i-esimo di quelli che partono da currentNode
				Villaggio endNode=pathWay.getDestinazione();
				double cost=pathWay.getCostoSentiero(v_utilizzato);
				double fullCost=nodeCost+cost;
				
				if(endNode.nodeHasWorsePath(fullCost)) {  //si veda il metodo utilizzato, ritorna true se questo spostamento permette di raggiungere il nodo endNode con costo migliore del precedente(si noti che -1 � interpretato come infinito)
					
					//il percorso � migliore, viene aggiornato ed aggiunto al treeSet
					endNode.dijsktraOverWrite(fullCost, currentNode.getPathIds());
					treeSentiero.add(pathWay);


					
				}else{
					//se � alla fine && gli viene == dijsktravalue, deve scegliere il percorso che passa per meno citt�, ma se hanno lo stesso numero di citt� sceglie quello che attraversa la citt� di id maggiore(noi l'abbiamo interpretato come "al primo punto in cui differiscono i percorsi, prevale quello che passa per una citt� di id maggiore")
					
					if(endNode==theLostRuins && Math.abs(fullCost-endNode.getDijsktraValue())<Constants.EPSILON) {
						if(currentNode.getPathIds().size()+1<theLostRuins.getPathIds().size()){
							
							//il percorso � migliore, viene aggiornato, ma non lo aggiunge al treeset perch� una volta arrivato alle rovine, non ha senso continuare
							endNode.dijsktraOverWrite(fullCost, currentNode.getPathIds());
							
						
						}else if(currentNode.getPathIds().size()+1==theLostRuins.getPathIds().size()){
							for(int check=0;check<currentNode.getPathIds().size();check++) {
								
								if(currentNode.getPathIds().get(check)>theLostRuins.getPathIds().get(i)) {
									
									//il percorso � migliore, viene aggiornato, ma non lo aggiunge al treeset perch� una volta arrivato alle rovine, non ha senso continuare
									endNode.dijsktraOverWrite(fullCost, currentNode.getPathIds());
									
								}
							}
							
						}
					
					}
				
					
				}
					
			
			}
			
		}

	}
	
	
	
	public static ContenitoreDatiOutput calcolaCamminiMinimi(Mappa mappaTotale) {
		ContenitoreDatiOutput risultato=new ContenitoreDatiOutput();
		
		
		risultato.setCamminoSquadra1(calcolaCamminoMinimo(mappaTotale, Constants.squadra1.getVeicolo()));
		
		mappaTotale.resetDijkstra();
		
		risultato.setCamminoSquadra2(calcolaCamminoMinimo(mappaTotale, Constants.squadra2.getVeicolo()));
		
		
		
		risultato.aggiornaCosti(Constants.squadra1.getVeicolo(), Constants.squadra2.getVeicolo());
		
		return risultato;
	}
	
	

}
