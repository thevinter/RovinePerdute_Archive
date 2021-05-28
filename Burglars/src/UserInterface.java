import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private static Scanner lettore = creaScanner();
    private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";

    public static void printMenu(){
        System.out.printf("Benvenuti nel programma! Scegliere il file da elaborare: " +
                "%n 0 - PgAr_Map_5.xml" +
                "%n 1 - PgAr_Map_12.xml" +
                "%n 2 - PgAr_Map_50.xml" +
                "%n 3 - PgAr_Map_200.xml " +
                "%n 4 - PgAr_Map_2000.xml" +
                "%n 5 - PgAr_Map_10000.xml");

    }


    private static Scanner creaScanner ()
    {
        Scanner creato = new Scanner(System.in);
        return creato;
    }

    public static int controllaScelta(int maxNotInclusive){//from 0 inclusive to max Not Inclusive
        System.out.println("Inserire l'indice: ");
        int scelta = leggiIntero("Inserire l'indice: ");
        while(scelta < 0 || scelta >= maxNotInclusive) {
            scelta = leggiIntero("Inserire l'indice: ");
        }
        return scelta;
    }


    public static int leggiIntero (String messaggio)
    {
        boolean finito = false;
        int valoreLetto = 0;
        do
        {
            System.out.print(messaggio);
            try
            {
                valoreLetto = lettore.nextInt();
                finito = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println(ERRORE_FORMATO);
                String daButtare = lettore.next();
            }
        } while (!finito);
        return valoreLetto;
    }

}
