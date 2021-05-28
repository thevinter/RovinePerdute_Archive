package it.unibs.arnaldo.rovinePerdute;

public class Costanti {

    //GESTIONE FILE XML
    //nomi file
    public static final String FILE_5_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_5.xml";
    public static final String FILE_12_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_12.xml";
    public static final String FILE_50_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_50.xml";
    public static final String FILE_200_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_200.xml";
    public static final String FILE_2000_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_2000.xml";
    public static final String FILE_10000_CITTA = "src/it/unibs/arnaldo/rovinePerdute/test_file/PgAr_Map_10000.xml";
    public static final String FILE_PERCORSO = "src/it/unibs/arnaldo/rovinePerdute/Routes.xml";
    //tag
    public static final String MAP = "map";
    public static final String CITY = "city";
    public static final String LINK = "link";
    public static final String ROUTES = "routes";
    public static final String ROUTE = "route";
    //attributi dei tag
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String H = "h";
    public static final String TEAM = "team";
    public static final String COST = "cost";
    public static final String CITIES = "cities";
    //comunicazioni di inzio/fine lettura/scrittura file
    public static final String INIZIO_FILE = "\nInizio a %s il file: %s ...\n";
    public static final String FINE_FILE = "\nFine della %s del file :)\n";
    public static final String LETTURA = "lettura";
    public static final String SCRITTURA = "scrittura";
    //errori vari nei servizi xml
    public static final String ERRORE_INIZIALIZZAZIONE_READER = "Errore nell'inizializzazione del reader: ";
    public static final String ERRORE_LETTURA_FILE = "Errore nella lettura del file: %s, per ulteriori info: %s\n";
    public static final String ERRORE_STRADA = "Errore strada non trovata :(";
    public static final String ERRORE_TIPOLOGIA = "Tipologia non esistente";
    public static final String ERRORE_INIZIALIZZAZIONE_WRITER = "Errore nell'inizializzazione del writer: %s\n";
    public static final String ERRORE_SCRITTURA_FILE = "Errore nella scrittura del file: %s\n";
    //altro
    public static final String ENCODING = "utf-8";
    public static final String VERSION = "1.0";

    //GESTIONE MAIN
    //errori vari nel main
    public static final String ERR_ID_NON_TROVATO = "id non trovato:(";
    public static final String ERRORE_NUM_CITTA = "Il numero per le città inserito non è corretto";
    //benvenuto
    public static final String CORNICE_SUP = "┌———————————————————————————————————————————————————————————————————————————————————————┐";
    public static final String BENVENUTO = "Benvenut* nel programma RovinePerdute del gruppo Programmazione di fondamenti :)";
    public static final  String CORNICE_INF = "└———————————————————————————————————————————————————————————————————————————————————————┘";
    //fase iniziale
    public static final String CINQUE = "5 citta";
    public static final String DODICI = "12 citta";
    public static final String CINQUANTA = "50 citta";
    public static final String DUECENTO = "200 citta";
    public static final String DUEMILA = "2000 citta";
    public static final String DIECIMILA = "10000 citta";
    //altro
    public static final String NUM_CITTA = "numero citta";
    public static final String INSERIMENTO_NUM_CITTA = "Inserisci il numero delle città per la generazione del grafo: ";
    public static final String TONATHIU = "tonathiu";
    public static final String METZTLI = "metztli";
    public static final String CALCOLO_IN_CORSO = "\nSto calcolando il percorso del veicolo %s, potrebbe volerci del tempo :)\n";

}
