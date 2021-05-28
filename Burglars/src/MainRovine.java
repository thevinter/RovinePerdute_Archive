import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class MainRovine {

    // private static final ArrayList<String> INPUT_FILES = new
    private static  final String teamJump = "Metztli";
    private static  final String teamGround = "Tonatiuh";
    private static  final String OUTPUT_FILE = "test_file/Routes.xml";

    private static final String[] INPUT_FILES = {
            "test_file/PgAr_Map_5.xml",
            "test_file/PgAr_Map_12.xml",
            "test_file/PgAr_Map_50.xml",
            "test_file/PgAr_Map_200.xml",
            "test_file/PgAr_Map_2000.xml",
            "test_file/PgAr_Map_10000.xml"};


    public static void main(String[] args) throws XMLStreamException {

        UserInterface.printMenu();
        String input_file= INPUT_FILES[UserInterface.controllaScelta( INPUT_FILES.length) ];

        ArrayList<Settlement> settlements1 = XMLReader.readMap(input_file);
        MyMap mapJ = new MyMap(settlements1);

        ArrayList<Settlement> settlements2 = new ArrayList<>();

        for (Settlement s: settlements1) {
            settlements2.add(Settlement.clone(s));
        } //shallow copying the array and its objects

        MyMap mapG = new MyMap(settlements2);  //da usare per ciascun veicolo

        JumpingVehicle vJ = new JumpingVehicle(mapJ);
        GroundVehicle vG = new GroundVehicle(mapG);

        //Route rottaJ = new Route(teamJump, mapJ);
        //rottaJ.setRoute(vJ, mapJ.getDestination());

        Route rottaG = new Route(teamGround, mapG);
        rottaG.setRoute(vG, mapG.getDestination());


        XMLWriter writer = new XMLWriter(OUTPUT_FILE);
        writer.writeRoute(rottaG);
        //writer.writeRoute(rottaJ);
        writer.endWriter();
    }
}