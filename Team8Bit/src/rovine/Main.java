package rovine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner lettore = creaScanner();
    private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
    private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
    private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
    public final static String firstPath="src/xmlFile/";
    private final static String STRINGA_MAPPA= "%d) mappa con %d citta";
    private final static String mappaFile5 = "PgAr_Map_5.xml";
    private final static String mappaFile12 = "PgAr_Map_12.xml";
    private final static String mappaFile50 = "PgAr_Map_50.xml";
    private final static String mappaFile200 = "PgAr_Map_200.xml";
    private final static String mappaFile2000 = "PgAr_Map_2000.xml";
    private final static String mappaFile10000 = "PgAr_Map_10000.xml";

    public static void main(String[] args) {
        //input dell'utente per scegliere il numero di citta
        System.out.println("scegli tra le seguenti opzioni: ");
        System.out.println(String.format(STRINGA_MAPPA,0,5));
        System.out.println(String.format(STRINGA_MAPPA,1,13));
        System.out.println(String.format(STRINGA_MAPPA,2,50));
        System.out.println(String.format(STRINGA_MAPPA,3,200));
        System.out.println(String.format(STRINGA_MAPPA,4,2000));
        System.out.println(String.format(STRINGA_MAPPA,5,10000));

        //creazione percorso file
        String path=firstPath;
        switch (leggiIntero(">",0,5)){
            case 0:path+=mappaFile5;break;
            case 1:path+=mappaFile12;break;
            case 2:path+=mappaFile50;break;
            case 3:path+=mappaFile200;break;
            case 4:path+=mappaFile2000;break;
            case 5:path+=mappaFile10000;break;
        }
        //lettura da file e creazione cartina
        ReaderXML lettore = new ReaderXML();
        lettore.leggiXML(path);
        Cartina mappa=new Cartina(lettore.getMappaTerritorio());
        //generazione del percorso ottimale e scrittura del file di output (all'interno del metodo)
        mappa.generaPercorsoOttimale(lettore.getElencoNodi(), lettore.getMappaArchi());
    }

    /**
     * inizializza lo scanner
     * @return ritorna lo scanner
     */
    private static Scanner creaScanner () {
        Scanner creato = new Scanner(System.in);
        return creato;
    }

    /**
     * stampa una stringa datagli e chiede un input di input dall'utente
     * @param messaggio stringa da stampare
     * @return ritorna il numero digitato dall'utente
     */
    public static int leggiIntero (String messaggio) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            System.out.print(messaggio);
            try{
                valoreLetto = lettore.nextInt();
                finito = true;
            } catch (InputMismatchException e) {
                System.out.println(ERRORE_FORMATO);
                String daButtare = lettore.next();
            }
        } while (!finito);
        return valoreLetto;
    }

    /**
     * stampa una stringa datagli e, dato un minimo e un massimo, chiede all'utente un nomero compreso tra un minimo e un massimo
     * @param messaggio stringa da stampare
     * @param minimo numero minimo
     * @param massimo numero massimo
     * @return ritorna un numero intero rientrante tra il minimo e il massimo
     */
    public static int leggiIntero(String messaggio, int minimo, int massimo) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            valoreLetto = leggiIntero(messaggio);
            if (valoreLetto >= minimo && valoreLetto<= massimo) finito = true;
            else if (valoreLetto < minimo) System.out.println(ERRORE_MINIMO + minimo);
            else System.out.println(ERRORE_MASSIMO + massimo);
        } while (!finito);

        return valoreLetto;
    }
}