package arnaldo.anno2021.triumvirato.rovineperdute;

public class Constants {
	//costanti relativi ai parametri delle due squadre
	private static final String TEAM1_NAME = "Tonatiuh";
	private static final String TEAM2_NAME = "Metztli";
	private static final Veicolo veicoloTeam1 = new Veicolo(TipologiaSpostamento.ORIZZONTALE, Constants.TEAM1_NAME);
	private static final Veicolo veicoloTeam2 = new Veicolo(TipologiaSpostamento.VERTICALE, Constants.TEAM2_NAME);
	
	public static final Squadra squadra1=new Squadra(TEAM1_NAME,veicoloTeam1);
	public static final Squadra squadra2=new Squadra(TEAM2_NAME,veicoloTeam2);
	
	
	public static final int CONCERNING_AMOUNT_OF_NODES=10000;
	public static final double EPSILON=Math.sqrt(Double.MIN_VALUE); //giusto per sicurezza
	public static final double DIJSKTRA_INFINITE_COST=-1;
	
	
	//costanti relative ai file di input e loro posizioni
	public static final String PATH_RELATIVO_INPUT_FOLDER="xmlInputFiles/";
	public static final String NOME_FILE_MAPPA="PgAr_Map_10000.xml"; //5 12 200 2000 10000
	public static final String PATH_RELATIVO_MAPPA=PATH_RELATIVO_INPUT_FOLDER+NOME_FILE_MAPPA;
	
	//costanti relative ai file di output e loro posizioni	
	public static final String PATH_RELATIVO_OUTPUT_FOLDER="";
	public static final String NOME_FILE_OUTPUT="Routes.xml";
	public static final String PATH_RELATIVO_OUTPUT=PATH_RELATIVO_OUTPUT_FOLDER+NOME_FILE_OUTPUT;
	
	
	
	
	//messaggi di sistema
	public static final String ALERT_MESSAGE_SUCCESSFUL_EXECUTION="L'esecuzione del programma e' avvenuta con successo.";
	public static final String ERROR_MESSAGE_OUTPUT="ERRORE: Non e' stato possibile fornire il risultato in output a causa di un errore, verificare che il path sia corretto.";
	public static final String ERROR_MESSAGE_UNSUCCESSFUL_EXECUTION="ERRORE: Non è stato possibile portare a termine l'esecuzione del programma, si prega di controllare i file di input.";
	public static final String ALERT_MESSAGE_POSSIBLE_SLOW_EXECUTION_ABOVE_CONCERNING_VALUE="Attenzione, per via del grande numero di citta' inserite, l'esecuzione potrebbe richiedere un paio di minuti, si richiede di attendere pazientemente il termine dell'esecuzione del programma, sarete a breve avvisati appena questa sarà conclusa.";
	public static final String ERROR_MESSAGE_FILE_ASSENTI=String.format("ERRORE: il file cercato risulta assente, si prega di controllare di averlo inserito nella cartella corretta e con il nome previsto, si ricorda di seguito il path previsto: %s", PATH_RELATIVO_MAPPA);
	public static final String INFORMATION_MESSAGE=String.format("Benvenuto nel nostro programma, per ottenere le informazioni sui cammini da seguire per entrambe le squadre(%s e %s) per raggiungere le rovine perdute, inserire le seguenti informazioni nel file %s, che si trova in %s all'interno della directory principale:"
	+"\n una mappa(tag map) contenente la lista di citta' da visitare(tag city);"
	+"\n ciascuna citta' deve avere gli attributi id e name indicanti un intero che faccia da identificativo univoco e un nome a piacere e all'interno del tag scrivere i sentieri(tag link);"
	+"\n ciascun sentiero(link) contiene un attributo indicante l'id della citta' a cui porta a partire da quella in cui e' contenuto questo tag link;"
	+"\n si noti in fine che la citta' con l'id maggiore rappresenta sicuramente le rovine perdute."
	+"\n\nI dati da lei forniti verranno elaborati e restituiti in formato xml nel file %s presente nella directory principale del programma."
	+"\nIl file di output conterrà i percorsi da seguire per ciascuna delle due squadre e i relativi costi in carburante."
	+"\nSi consideri che la prima squadra(%s) ha un veicolo che consuma carburante solo in base alla distanza orizzontale e la seconda squadra(%s) ha un veicolo che consuma carburante solo in base alla distanza verticale.",
	TEAM1_NAME,TEAM2_NAME,NOME_FILE_MAPPA,PATH_RELATIVO_INPUT_FOLDER, NOME_FILE_OUTPUT,TEAM1_NAME,TEAM2_NAME);
	
	
	
	
}
