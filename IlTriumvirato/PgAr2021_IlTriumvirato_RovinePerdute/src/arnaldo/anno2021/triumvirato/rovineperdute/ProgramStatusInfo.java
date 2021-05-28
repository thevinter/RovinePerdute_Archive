package arnaldo.anno2021.triumvirato.rovineperdute;

public class ProgramStatusInfo {
	public static boolean continueExectuion;
	
	/**
	 * Metodo che viene lanciato inizialmente inizialmente per indicare se il programma può essere eseguito
	 */
	public static void setExecutable() {
		continueExectuion=true;
	}
	
	
	/**
	 * Metodo che viene lanciato se si incontrano errori gravi che impediscono di continuare l'esecuzione del programma
	 */
	public static void setUnexecutable() {
		continueExectuion=false;
	}
	
	/**
	 * @return ritorna true se può continuare ad eseguire senza errori
	 */
	public static boolean canContinueExecution() {
		return continueExectuion;
	}
	
	/**
	 * da in output una breve descrizione del funzionamento del programma per l'utente
	 */
	public static void fornisciInformazioniUtilizzo() {
		System.out.println(Constants.INFORMATION_MESSAGE);
	}
	
	/**
	 * Informa l'utente sul risultato dell'esecuzione (se ha avuto successo o no)
	 */
	public static void notifyResults() {
		if(canContinueExecution()) {
			System.out.println("\n"+Constants.ALERT_MESSAGE_SUCCESSFUL_EXECUTION);
		}else {
			System.out.println("\n"+Constants.ERROR_MESSAGE_UNSUCCESSFUL_EXECUTION);
		}
	}
	
	/**
	 * In caso il file di input abbia molti dati(definito da Constants) avvisa l'utente che l'esecuzione potrebbe richiedere qualche minuto
	 * @param mappaTotale  riceve in ingresso la Mappa per contare quanti villaggi sono
	 */
	public static void alertForPossibleSlowExecution(Mappa mappaTotale) {
		if(mappaTotale.getVillaggi().size()>=Constants.CONCERNING_AMOUNT_OF_NODES) {
			System.out.println("\n"+Constants.ALERT_MESSAGE_POSSIBLE_SLOW_EXECUTION_ABOVE_CONCERNING_VALUE);
		}
	}
	
}
