import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

public class XMLWriter {

    public static final String INIT_ERROR = "Errore nell'inizializzazione del writer:";
    public static final String FIRST_TAG = "routes";
    XMLStreamWriter writer;

    public XMLWriter(String output_file){
        this.writer = writerInit(output_file);
    }

    public XMLStreamWriter getWriter(){
        return this.writer;
    }

    /**
     * inizializza un nuovo writer con il realtivo OutputStream
     * @param output_file String contenente il nome del file da scrivere
     * @return un nuovo XMLStreamWriter
     */
    public XMLStreamWriter writerInit(String output_file){

        XMLOutputFactory factory = null;
        XMLStreamWriter writer = null;

        try {
            factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(new FileOutputStream(output_file), "utf-8");
            writer.writeStartDocument("utf-8", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement(FIRST_TAG);
        } catch (Exception e) {
            System.out.println(INIT_ERROR);
            System.out.println(e.getMessage());
        }
        return writer;
    }

    /**
     * writes a Route on the output
     * @param r the route object to be written
     * @throws XMLStreamException
     */
    public void writeRoute(Route r) throws XMLStreamException {
        writer.writeStartElement("route");
        writer.writeAttribute("team", r.getTeam());
        writer.writeAttribute("cost", r.getFuel().toString());
        writer.writeAttribute("cities", Integer.toString(r.getRoute().size()));

        for (Settlement city : r.getRoute()) {
            writer.writeEmptyElement("city");
            writer.writeAttribute("id", Integer.toString(city.getId()));
            writer.writeAttribute("name", city.getName());
        }
        writer.writeEndElement();
    }

    /**
     * chiude lo stream di output
     * @throws XMLStreamException
     */
    public void endWriter() throws XMLStreamException {
        this.writer.writeEndDocument();
        this.writer.flush();
        this.writer.close();
    }

}
