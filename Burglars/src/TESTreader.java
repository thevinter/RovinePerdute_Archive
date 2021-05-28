import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class TESTreader {
    private static final String file = "test_file/PgAr_Map_12.xml";
    public static void main(String[] args) throws XMLStreamException {

        ArrayList<Settlement> settlements = XMLReader.readMap(file);


        for (int i = 0; i < settlements.size(); i++) {
            Settlement s = settlements.get(i);
            System.out.println("Ciao");
            System.out.println("Id: " + s.getId());
            System.out.println("Nome: " + s.getName());
            System.out.println("X: " + s.getX());
            System.out.println("Y: " + s.getY());
            System.out.println("H: " + s.getHeight());
            System.out.println("links: " + s.getConnected().size());
            System.out.println("-------------------");

        }

    }


}
