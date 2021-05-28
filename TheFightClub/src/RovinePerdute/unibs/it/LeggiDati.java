package RovinePerdute.unibs.it;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.*;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LeggiDati {

    private ArrayList<Citta> citta;
    private Citta cittaBase;
    private Citta cittaPerduta;
    private int matAdj[][];

    public ArrayList<Citta> leggiDati(){
            citta = new ArrayList<>();
            XMLInputFactory xmlif = null;
            XMLStreamReader xmlr = null;

            try {
                //aggiungo i file xml
                xmlif = XMLInputFactory.newInstance();
                xmlr = xmlif.createXMLStreamReader("PgAr_Map_5.xml", new FileInputStream("PgAr_Map_5.xml"));
            } catch (Exception e) {
                System.out.println("Errore nell'inizializzazione del reader: ");
                System.out.println(e.getMessage());
            }

            try {
                String tag;
                Citta city = new Citta();
                while (xmlr.hasNext()) {
                    switch (xmlr.getEventType()) {
                        case XMLStreamConstants.START_ELEMENT:

                            tag = xmlr.getLocalName();
                            switch (tag) {
                                case "city":
                                    String nomeAttributo;
                                    for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                                        nomeAttributo = xmlr.getAttributeLocalName(i);
                                        switch (nomeAttributo) {
                                            case "id":
                                                city.setID(Integer.parseInt(xmlr.getAttributeValue(i)));
                                                break;
                                            case "x":
                                                city.setX(Integer.parseInt(xmlr.getAttributeValue(i)));
                                                break;
                                            case "y":
                                                city.setY(Integer.parseInt(xmlr.getAttributeValue(i)));
                                                break;
                                            case "name":
                                                city.setNome(String.valueOf(xmlr.getAttributeValue(i)));
                                                break;
                                            case "h":
                                                city.setH(Integer.parseInt(xmlr.getAttributeValue(i)));
                                        }
                                    }
                                    break;
                                case "link":
                                    if (xmlr.getAttributeLocalName(0).contentEquals("to"))
                                        city.addDestinazione(Integer.parseInt(xmlr.getAttributeValue(0)));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            if(xmlr.getLocalName().contentEquals("city")) {
                                citta.add(city);
                                city = new Citta();
                            }
                            break;

                        default:
                            break;
                    }
                    xmlr.next();
                }
            }
            catch (Exception e){
                System.out.println("Errore nella lettura dei Nodi:");
                System.out.println(e.getMessage());
            }
            return citta;
          }

          public LeggiDati(){
            cittaBase = citta.get(0);
            cittaPerduta = citta.get(citta.size()-1);
          }


          public void matAdiacenza(){
          matAdj= new int[citta.size()][citta.size()];
            for(int i=0; i < citta.size()-1; i++){
                for(int j=0; j < citta.size()-1; j++){
                    matAdj[i][j] = citta

                }
            }
          }



        }


