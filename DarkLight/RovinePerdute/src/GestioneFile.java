import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


/**
 * Classe che gestisce lettura e scrittura dei file XML.
 */
public class GestioneFile {

    private final static String ERRORE_READER = "Errore nell'inizializzazione del reader:";
    private final static String ERRORE_WRITER = "Errore nell'inizializzazione del writer:";
    private final static String ERRORE_SCRITTURA_FILE = "Errore nella scrittura del file";
    private final static String NOME_FILE_OUTPUT = "RovinePerdute/test_file/Routes.xml";

    private ArrayList<Citta> citta; //arraylist contenente oggetti di classe Citta creati con i dati letti dal file XML
    private Citta city; //oggetto di classe Citta
    private int id, numeroCitta, x, y, z; //id delle città lette dal file, numero città totali da leggere, coordinate delle città lette
    private Posizione posizione; //oggetto di classe Posizione
    private String nome; //nome delle città lette

    /**
     * Costruttore: inizializza ArrayList citta.
     */
    public GestioneFile(){
        citta = new ArrayList<Citta>();
    }


    /**
     * Metodo per la lettura del file XML: <br>
     * crea un oggetto city passando i dati letti e lo aggiunge nell'arraylist citta
     *
     * @param nomeFile
     */
    public void leggiFile(String nomeFile){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;

        //inizializzazione del reader con controllo eccezioni
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomeFile, new FileInputStream(nomeFile));
        } catch (Exception e) {
            errore = true;
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }

        if(!errore){
            try{

                while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione

                    switch (xmlr.getEventType()) { // switch sul tipo di evento

                        case XMLStreamConstants.START_ELEMENT: // inizio di un elemento

                            switch (xmlr.getLocalName()){

                                case "map":

                                    numeroCitta = Integer.parseInt(xmlr.getAttributeValue(0)); //imposta numero città = all'attributo di <map>

                                    break;

                                case "city": //assegno alle variabili gli attributi letti e creo un oggetto di classe Citta

                                    id = Integer.parseInt(xmlr.getAttributeValue(0));
                                    nome = xmlr.getAttributeValue(1);
                                    x = Integer.parseInt(xmlr.getAttributeValue(2));
                                    y = Integer.parseInt(xmlr.getAttributeValue(3));
                                    z =  Integer.parseInt(xmlr.getAttributeValue(4));

                                    city = new Citta(id,nome,x,y,z);

                                    break;

                                case "link": //aggiungo alla city letta i vari link alle città connesse

                                    city.getLink().add(Integer.parseInt(xmlr.getAttributeValue(0)));

                                    break;

                            }

                            break;

                        case XMLStreamConstants.END_ELEMENT: //aggiungo oggetto city ad arraylist citta

                            if (xmlr.getLocalName().equals("city"))

                                citta.add(city);

                                break;

                    }

                    xmlr.next();

                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


    /**
     * Metodo per la scrittura del documento finale Routes.xml
     * @param squadre
     * @return true se scrittura avviene correttamente, false se c'è qualche errore
     */
    public boolean scriviFile(ArrayList<Squadra> squadre){

            XMLOutputFactory xmlof = null;
            XMLStreamWriter xmlw = null;

            //inizializzazione del writer con controllo eccezioni
            try {
                xmlof = XMLOutputFactory.newInstance();
                xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NOME_FILE_OUTPUT), "utf-8");
                xmlw.writeStartDocument("utf-8", "1.0");
            } catch (Exception e) {
                System.out.println(ERRORE_WRITER);
                System.out.println(e.getMessage());
                return false;
            }

            try { // blocco try per raccogliere eccezioni
                xmlw.writeStartElement( "routes"); // scrittura del tag radice <routes>

                /*
                Scrittura delle rotte di ogni squadra
                 */
                for (int i=0; i<squadre.size(); i++){
                    generaTagRoute(squadre.get(i), xmlw);
                }

                xmlw.writeEndElement();//chiudo tag radice </routes>

                xmlw.writeEndDocument(); //chiusura documento

                xmlw.flush(); // svuota il buffer e procede alla scrittura
                xmlw.close(); // chiusura del documento e delle risorse impiegate

                return true;

            } catch (Exception e) { // se c’è un errore viene eseguita questa parte
                System.out.println(ERRORE_SCRITTURA_FILE);
                System.out.println(e.getMessage());
                return false;
            }

    }


    /**
     * Metodo che genera tag <route> per la squadra passata per parametro.
     * @param squadra
     * @param xmlw
     * @throws XMLStreamException
     */
    public void generaTagRoute(Squadra squadra, XMLStreamWriter xmlw) throws XMLStreamException {

        xmlw.writeStartElement("route"); // scrittura del tag <route> ...
        xmlw.writeAttribute("team" , squadra.getNome()); //... con attributo team = nome squadra
        xmlw.writeAttribute("cost" , Double.toString(squadra.getCarburanteConsumato())); //... con attributo cost = carburante consumato
        xmlw.writeAttribute("cities" , Integer.toString(squadra.getPercorso().getRotta().size())); //... con attributo cities = numero città toccate

        /*
        scrive in ordine ogni città toccata con id e nome come attributi
         */
        for (int i=0; i<squadra.getPercorso().getRotta().size();i++){
            xmlw.writeEmptyElement("city");
            xmlw.writeAttribute("id" , Integer.toString(squadra.getPercorso().getRotta().get(i).getId()));
            xmlw.writeAttribute("name" , squadra.getPercorso().getRotta().get(i).getNome());
        }

        xmlw.writeEndElement();//chiudo tag </route>

    }


    /**
     * Metodo che ritorna l'arraylist citta
     * @return citta
     */
    public ArrayList<Citta> getCitta(){
        return citta;
    }


    /**
     * Metodo che stampa tutti gli attributi di ogni città
     */
    public void stampaCitta(){

        for (int i=0; i<citta.size(); i++){
            System.out.println("id: "+citta.get(i).getId());
            System.out.println("nome: "+citta.get(i).getNome());
            System.out.println("x: "+citta.get(i).getPosizione().getX());
            System.out.println("y: "+citta.get(i).getPosizione().getY());
            System.out.println("z: "+citta.get(i).getPosizione().getZ());
            for (int j=0; j<citta.get(i).getLink().size(); j++){
                System.out.println("Link: " + citta.get(i).getLink().get(j));
            }
            System.out.println("\n");
        }


    }

}
