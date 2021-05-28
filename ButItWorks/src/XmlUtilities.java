import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XmlUtilities {

    public static ArrayList<Citta> leggiMappaCittaXml(String filename) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try {
            String filepath = "test_file/" + filename;
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filepath));

            ArrayList<Citta> mappa = new ArrayList<>();
            Citta citta = new Citta();

            while (xmlr.hasNext()) {
                if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("city")) {
                    for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                        switch (xmlr.getAttributeLocalName(i)) {
                            case "id" -> citta.setId(Integer.parseInt(xmlr.getAttributeValue(i)));
                            case "name" -> citta.setNome(xmlr.getAttributeValue(i));
                            case "x" -> citta.getPosizione().setX(Integer.parseInt(xmlr.getAttributeValue(i)));
                            case "y" -> citta.getPosizione().setY(Integer.parseInt(xmlr.getAttributeValue(i)));
                            case "h" -> citta.setAltezza(Integer.parseInt(xmlr.getAttributeValue(i)));
                        }
                    }
                }
                else if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("link")) {
                    citta.inserisciCollegamentoId(Integer.parseInt(xmlr.getAttributeValue(0)));
                }
                else if(xmlr.getEventType() == XMLStreamConstants.END_ELEMENT && xmlr.getLocalName().equals("city")) {
                    mappa.add(citta);
                    citta = new Citta();
                }
                xmlr.next();
            }

            for (Citta c : mappa) {
                for (int idCollegamento : c.getCollegamentiId()) {
                    c.getCollegamenti().add(mappa.get(idCollegamento));
                }
            }

            return mappa;

        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        return null;
    }


    public static void produciOutput() throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try
        {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("output/Routes.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
            xmlw.writeStartElement("output");
            xmlw.writeStartElement("routes");


            //Stampa percorso Tonatiuh
            xmlw.writeStartElement("route");
            xmlw.writeAttribute("team", "Tonatiuh");
            xmlw.writeAttribute("cost", Double.toString(Constants.getBenzinaTonathiu()));
            xmlw.writeAttribute("cities", Integer.toString(Constants.getPercorsoMinimoTonatiuh().size()));
            for (Citta citta : Constants.getPercorsoMinimoTonatiuh()) {
                xmlw.writeStartElement("città");
                xmlw.writeAttribute("id", Integer.toString(citta.getId()));
                xmlw.writeAttribute("nome", citta.getNome());
                xmlw.writeEndElement();
            }
            xmlw.writeEndElement();

            //Stampa percorso Metztli
            xmlw.writeStartElement("route");
            xmlw.writeAttribute("team", "Metztli");
            xmlw.writeAttribute("cost", Double.toString(Constants.getBenzinaMetztli()));
            xmlw.writeAttribute("cities", Integer.toString(Constants.getPercorsoMinimoMetztli().size()));
            for (Citta citta : Constants.getPercorsoMinimoMetztli()) {
                xmlw.writeStartElement("città");
                xmlw.writeAttribute("id", Integer.toString(citta.getId()));
                xmlw.writeAttribute("nome", citta.getNome());
                xmlw.writeEndElement();
            }
            xmlw.writeEndElement();


            xmlw.writeEndElement();
            xmlw.writeEndElement();
            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();
        }
        catch (Exception e)
        {
            System.out.println("Errore nella scrittura");
            System.out.println(e.getMessage());
        }


    }
}
