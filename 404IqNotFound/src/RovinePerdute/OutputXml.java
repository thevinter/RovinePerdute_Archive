package RovinePerdute;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class OutputXml {


	public static final String UTF = "utf-8";
	public static final String VERSION = "1.0";
	private static final String TEAM1 = "Tonathiu";
	private static final String TEAM2 = "Metztli";
	public static final String ROUTES = "Routes.xml";
	public static final String MESSAGGIO_FINALE = "File di nome " + ROUTES + " creato.";
	public static final String ERRORE = "Errore!";
	public static final String ROUTES_OPENTAG = "routes";
	public static final String ROUTE = "route";
	public static final String TEAM = "team";
	public static final String COST = "cost";
	public static final String CITIES = "cities";
	public static final String CITY = "city";
	public static final String ID = "id";
	public static final String NAME = "name";


	/**
	 * metodo che crea un file con il cammino più veloce a seconda della mappa scelta dall'utente
	 * @param risultatoXY risultato del carburante utilizzato per X e Y
	 * @param risultatoZ risultato del carburante usato per Z
	 */
	public OutputXml(RicercaCammino risultatoXY, RicercaCammino risultatoZ)
	{

        try
        {
			XMLOutputFactory outputter = XMLOutputFactory.newInstance();

     	    XMLStreamWriter xmlw = outputter.createXMLStreamWriter(new FileOutputStream(ROUTES), UTF);

            xmlw.writeStartDocument(UTF, VERSION);
            xmlw.writeStartElement(ROUTES_OPENTAG);//scrivo tag radice "routes"

            //xmlw.writeComment("Inizio Lista Città");//scrivo commento

	        stampaCammino(xmlw, risultatoXY, TEAM1);//stampo il cammino del team1
	        stampaCammino(xmlw, risultatoZ, TEAM2);//stampo il cammino del team2

	            xmlw.writeEndElement();//chiusura routes

	            xmlw.writeEndDocument();//scrittura fine documento
	            xmlw.flush(); //svuota buffer e procede alla scrittura
	            xmlw.close(); //chiusura del documento e delle risorse utilizzate
	        System.out.println(MESSAGGIO_FINALE);

        }
        catch(Exception e)
        {
            System.out.println(ERRORE);
        }
    }

	/**
	 * Stampa la radice route per ogni città in modo da creare correttamente l'output
	 * @param xmlw XMLStreamWriter
	 * @param cammino cammino
	 * @param team team
	 */
	private static void stampaCammino(XMLStreamWriter xmlw, RicercaCammino cammino, String team)
        {
			try
			{
				xmlw.writeStartElement(ROUTE);//scrittura tag route
				xmlw.writeAttribute(TEAM, team);//scrittura attributo team
				xmlw.writeAttribute(COST, Long.toString(Math.round(cammino.getCarburante())));//scrittura attributo cost
				ArrayList<Integer> nodiToccati = cammino.getCamminoMinimo();//calcolo numero nodi vicini
				xmlw.writeAttribute(CITIES, Integer.toString(nodiToccati.size()));//scrittura attributo cities

				for (Integer integer : nodiToccati) {
					xmlw.writeStartElement(CITY);//scrittura tag route
					xmlw.writeAttribute(ID, Integer.toString(integer));//scrittura attributo id

					xmlw.writeAttribute(NAME, Spedizione.getNomeDaID(integer));//scrittura attributo name

					xmlw.writeEndElement();//chiusura city
				}
				xmlw.writeEndElement(); //chiusura route
			}
			catch(Exception e)
	        {
		        System.out.println(ERRORE);
	        }
		}
}
