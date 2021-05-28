package arnaldo.anno2021.triumvirato.rovineperdute;

public class RovinePerduteMain {
	
	/**
	 * Metodo di inizializzazione con cui si fanno le operazioni preliminari: informare l'utente e impostare a true la variabile che indica se l'esecuzione sta procedendo senza problemi
	 */
	private static void inizializzazione() {
		ProgramStatusInfo.setExecutable();
		ProgramStatusInfo.fornisciInformazioniUtilizzo();
	}

	
	//Prende in input la mappa, elabora i cammini minimi e li mette in ContenitoreDatiOutput risultati  e poi li da in output sul file fornito
	public static void main(String[] args) {
		
		inizializzazione();
		
		
		Mappa mappaTotale = InputXML.inputMappa(Constants.PATH_RELATIVO_MAPPA);
		mappaTotale.getLostRuins().becomeLostRuins();
		
		
		if(ProgramStatusInfo.canContinueExecution()) {
			
			ProgramStatusInfo.alertForPossibleSlowExecution(mappaTotale);
			
			
			
			ContenitoreDatiOutput risultati=CalcolatoreCammini.calcolaCamminiMinimi(mappaTotale);
			
			
			try {
				OutputXML.outputPaths(risultati, Constants.PATH_RELATIVO_OUTPUT);
			} catch (Exception e) {
				System.out.println(Constants.ERROR_MESSAGE_OUTPUT);
				ProgramStatusInfo.setUnexecutable();
			}
			
		}
		
		ProgramStatusInfo.notifyResults();
		
	}



}
