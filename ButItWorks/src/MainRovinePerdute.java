import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class MainRovinePerdute {

    private static boolean exit = false;
    private static final int MIN_CHOICE_VALUE = 1;
    private static final int MAX_CHOICE_VALUE = 6;

    public static void menuHeader(){
        System.out.println("+------------------------------------------------+");
        System.out.println("|                    BENVENUTO                   |");
        System.out.println("+------------------------------------------------+");
        System.out.println("Seleziona una dei seguenti file:");
        System.out.println("1)  PgAr_Map_5.xml ( Mappa con 5 citta )");
        System.out.println("2)  PgAr_Map_12.xml ( Mappa con 12 citta )");
        System.out.println("3)  PgAr_Map_50.xml ( Mappa con 50 citta )");
        System.out.println("4)  PgAr_Map_200.xml ( Mappa con 200 citta )");
        System.out.println("5)  PgAr_Map_2000.xml ( Mappa con 2000 citta )");
        System.out.println("6)  PgAr_Map_10000.xml ( Mappa con 10000 citta )");
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        menuHeader();
        int choice = InputDati.leggiIntero("Si prega di effettuare una scelta: ", MIN_CHOICE_VALUE, MAX_CHOICE_VALUE);

        switch (choice) {
            case 1:
                Constants.doEverything("PgAr_Map_5.xml");
                break;
            case 2:
                Constants.doEverything("PgAr_Map_12.xml");
                break;
            case 3:
                Constants.doEverything("PgAr_Map_50.xml");
                break;
            case 4:
                Constants.doEverything("PgAr_Map_200.xml");
                break;
            case 5:
                Constants.doEverything("PgAr_Map_2000.xml");
                break;
            case 6:
                Constants.doEverything("PgAr_Map_10000.xml");
                break;
            default:
                break;
        }

        XmlUtilities.produciOutput();
        System.out.println("Output generato....arrivederci!");
    }

}
