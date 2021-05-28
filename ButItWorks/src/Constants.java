import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Constants {
    private static ArrayList<Citta> mappa = new ArrayList<>();
    private static ArrayList<Citta> percorsoMinimoTonatiuh = new ArrayList<>();
    private static ArrayList<Citta> percorsoMinimoMetztli = new ArrayList<>();
    private static double benzinaTonathiu = 0;
    private static double benzinaMetztli = 0;

    public static void doEverything(String filename) throws XMLStreamException, FileNotFoundException {
        mappa = XmlUtilities.leggiMappaCittaXml(filename);
        percorsoMinimoTonatiuh = CalcoloPercorso.calcoloPercorsoMinimo(mappa, mappa.get(0), mappa.get(mappa.size()-1), true);
        benzinaTonathiu = mappa.get(mappa.size()-1).getDistanza();
        percorsoMinimoMetztli = CalcoloPercorso.calcoloPercorsoMinimo(mappa, mappa.get(0), mappa.get(mappa.size()-1), false);
        benzinaMetztli = mappa.get(mappa.size()-1).getDistanza();
    }

    public static ArrayList<Citta> getMappa() {
        return mappa;
    }

    public static ArrayList<Citta> getPercorsoMinimoTonatiuh() {
        return percorsoMinimoTonatiuh;
    }

    public static ArrayList<Citta> getPercorsoMinimoMetztli() {
        return percorsoMinimoMetztli;
    }

    public static double getBenzinaTonathiu() {
        return benzinaTonathiu;
    }

    public static double getBenzinaMetztli() {
        return benzinaMetztli;
    }

}
