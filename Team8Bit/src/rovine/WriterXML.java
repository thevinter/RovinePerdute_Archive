package rovine;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class WriterXML {
    private static final String MSG_ERROR_WRITE = "Errore nell'inizializzazione del writer:";
    private static final String OUTPUT = "routes";
    private static final String ROUTE = "route";
    private static final String TEAM = "team";
    private static final String NAME_TEAM1 = "Tonathiu";
    private static final String NAME_TEAM2 = "Metztli";
    private static final String COST = "cost";
    private static final String CITIES = "cities";
    private static final String CITY = "city";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STRINGA_FINE = "il file è stato generato con successo";

    /**Costruttore del WriterXML
     */
    public WriterXML(){}

    /**Metodo utilizzato per creare un file routes.xml
     * @param listaCittaV1 ArrayList di Nodo, dal quale verranno presi i nodi per cui passa il veicolo1 durante il tragitto con i loro id e nomi per poi essere scritti sul file xml.
     * @param listaCittaV2 ArrayList di Nodo, dal quale verranno presi i nodi per cui passa il veicolo2 durante il tragitto con i loro id e nomi per poi essere scritti sul file xml.
     * @param carburanteTotV1, consumo di carburante del primo veicolo per il tragitto percorso.
     * @param carburanteTotV2, consumo di carburante del secondo veicolo per il tragitto percorso.
     * @param filePath il file xml creato
     */
    public void ScriviXML(ArrayList<Nodo> listaCittaV1, ArrayList<Nodo> listaCittaV2, int carburanteTotV1 , int carburanteTotV2, String filePath) {
        //Questo frammento di codice serve a creare ed istanziare la variabile xmlw di tipo XMLStreamWriter, che
        //sarà  utilizzata per scrivere il file XML. Viene inoltre inizializzato il documento XML.
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filePath), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println(MSG_ERROR_WRITE);
            System.out.println(e.getMessage());
            return;
        }
        try {
            xmlw.writeCharacters("\n");
            xmlw.writeStartElement(OUTPUT);
            xmlw.writeCharacters("\n"+"\t");
            xmlw.writeStartElement(ROUTE);
            xmlw.writeAttribute(TEAM, NAME_TEAM1);
            stampaRoute(listaCittaV1, carburanteTotV1, xmlw);
        } catch (Exception e) { // se trova un errore viene eseguita questa parte
            return;
        }
        try {
            xmlw.writeCharacters("\n"+"\t");
            xmlw.writeStartElement(ROUTE);
            xmlw.writeAttribute(TEAM, NAME_TEAM2);
            stampaRoute(listaCittaV2, carburanteTotV2, xmlw);
            xmlw.writeCharacters("\n");
            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();
            System.out.println(STRINGA_FINE);
        } catch (Exception e) { // se trova un errore viene eseguita questa parte
            return;
        }
    }

    /**
     *
     * @param listaCittaV ArrayList di Nodo, dal quale verranno presi i nodi per cui passa il veicolo X.
     * @param carburanteTotV, consumo di carburante del veicolo X per il tragitto percorso.
     * @param xmlw, writer che andrà a stampare la route.
     * @throws XMLStreamException, quando scrive lancia un eccezione.
     */
    private void stampaRoute(ArrayList<Nodo> listaCittaV, int carburanteTotV, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeAttribute(COST, Integer.toString(carburanteTotV));
        xmlw.writeAttribute(CITIES,Integer.toString(listaCittaV.size()));
        xmlw.writeCharacters("\n");
        for (int i = listaCittaV.size()-1 ; i >= 0; i--) {
            xmlw.writeCharacters("\t"+"\t");
            xmlw.writeStartElement(CITY);
            xmlw.writeAttribute(ID, Integer.toString(listaCittaV.get(i).getId()));
            xmlw.writeAttribute(NAME,listaCittaV.get(i).getName());
            xmlw.writeEndElement();
            xmlw.writeCharacters("\n");
        }
        xmlw.writeCharacters("\t");
        xmlw.writeEndElement();
    }
}