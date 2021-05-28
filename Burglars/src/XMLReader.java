import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Set;

public class XMLReader {
    private static final String INIT_ERROR = "Errore nell'inizializzazione del reader:";
    private static final String CITY = "city";
    private static final String LINK = "link";


    /**
     * XMLStreamReader initializer
     *
     * @param input_file String containing the relative path to the file to be read
     * @return a new instance of XMLStreamReader
     */
    private static XMLStreamReader streamReaderInit(String input_file) {
        XMLInputFactory factory = null;
        XMLStreamReader reader = null;
        try {
            factory = XMLInputFactory.newInstance();
            reader = factory.createXMLStreamReader(input_file, new FileInputStream(input_file));
        } catch (Exception e) {
            System.out.println(INIT_ERROR);
            System.out.println(e.getMessage());
        }
        return reader;
    }


    /**
     * reads the map given as an XML file formatted:
     * <map size="13">
     * <city id="0" name="campo base" x="0" y="0" h="0">
     * <link to="1"/>
     * <link to="2"/>
     * <link to="6"/>
     * </city>
     * </map>
     *
     * @param input_file String containing the relative path to the map file
     * @return an ArrayList of Settlements
     * @throws XMLStreamException
     */
    public static ArrayList<Settlement> readMap(String input_file) throws XMLStreamException {

        ArrayList<Settlement> settlements = new ArrayList<>();

        XMLStreamReader reader = streamReaderInit(input_file);

        while (reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals(CITY)) {
                settlements.add(readSettlement(reader));
            } else reader.next();
        }
        return settlements;
    }

    /**
     * Parse Settlement data from an XML file
     *
     * @param reader an instance of XMLStreamReader to read from
     * @return a parsed Settlement
     * @throws XMLStreamException
     */
    private static Settlement readSettlement(XMLStreamReader reader) throws XMLStreamException {

        Settlement s = new Settlement();

        s.setId(Integer.parseInt(reader.getAttributeValue(0)));
        s.setName(reader.getAttributeValue(1));
        s.setX(Integer.parseInt(reader.getAttributeValue(2)));
        s.setY(Integer.parseInt(reader.getAttributeValue(3)));
        s.setHeight(Integer.parseInt(reader.getAttributeValue(4)));
        reader.next();
        reader.next();

        while (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals(LINK)) {
            s.addConnection(Integer.parseInt(reader.getAttributeValue(0)));
            reader.next();
            reader.next();
            reader.next();
        }

        return s;
    }
}
