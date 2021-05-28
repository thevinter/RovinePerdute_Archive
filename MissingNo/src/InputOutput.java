import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**Questa classe si preoccupa di tutte quelle operazioni legate alla lettura e scrittura di file xml
 * @author Giovanni
 */
public class InputOutput {

	
	private int x;
	private int y;
	private int h;
	private String nome;
	private String links ="";
	private int linkCounter=0;
	private ArrayList<City> cities = new ArrayList<City>();

	
	/**
	 * Questo metodo crea lettori dato un percorso valido di un xml
	 * @param filename
	 * @return xmlr
	 */
	public XMLStreamReader creaLettore(String filename) {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
		 xmlif = XMLInputFactory.newInstance();
		 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		 return null;
		}
		return xmlr;
	}
	

	/**
	 * Questo metodo legge un xml e crea un arraylist di città
	 * @param location
	 * @return ArrayList<City> cities
	 * @throws XMLStreamException
	 */
	public ArrayList<City> getData(String location) throws XMLStreamException {
		
		XMLStreamReader xmlr = creaLettore("test_file/PgAr_Map_" + location+".xml");
		while (xmlr.hasNext()) {
			switch(xmlr.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				//System.out.println("Tag " + xmlr.getLocalName());
				if (xmlr.getLocalName()=="city") {
					for (int i = 0; i < xmlr.getAttributeCount(); i++)
						switch(xmlr.getAttributeLocalName(i)) {
						case "name":
							//System.out.println("questo è il nome " + xmlr.getAttributeValue(i));
							nome=xmlr.getAttributeValue(i);
							break;
						case "x":
							//System.out.println(xmlr.getAttributeValue(i));
							x =  Integer. parseInt(xmlr.getAttributeValue(i));	
							break;
						case "y":	
							//System.out.println(xmlr.getAttributeValue(i));
							y =  Integer. parseInt(xmlr.getAttributeValue(i));
							
							break;
						case "h":
							//System.out.println(xmlr.getAttributeValue(i));
							h = Integer. parseInt(xmlr.getAttributeValue(i));
							break;
						}
				}
				
				if (xmlr.getLocalName()=="link") {
					//System.out.println("questo è il link " + xmlr.getAttributeValue(0));
					links+=xmlr.getAttributeValue(0) + "-"; //Questa oscenità sorge dalla mia iniziale incompetenza con gli ArraList
					linkCounter++;
					
				}
				
				break;
				
			case XMLStreamConstants.END_ELEMENT:
				if (xmlr.getLocalName()=="city") {
					City city= new City (x, y, h , nome, links, linkCounter);
					this.cities.add(city);
					links="";
					linkCounter=0;
					
				}
				
				break;
			}
			
			xmlr.next();
		}
		
		return cities;
			
		}
	
	/**
	 * Questo scrive i percorsi su test_file/Routes.xml
	 * @author Sara
	 * @param pathT
	 * @param pathM
	 * @param usedFuelT
	 * @param usedFuelM
	 * @param cities
	 */
	public void writeXML(ArrayList<Integer> pathT, ArrayList<Integer> pathM, double usedFuelT, double usedFuelM, ArrayList<City> cities) {
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {   
		xmlof = XMLOutputFactory.newInstance();
		xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("test_file/Routes.xml"), "utf-8");
		xmlw.writeStartDocument("utf-8", "1.0");
		xmlw.writeStartElement("routes"); //Inizio route
		xmlw.writeStartElement("route");//Inizio route
		xmlw.writeAttribute("team", "Tonathiu");
		xmlw.writeAttribute("cost", Double.toString(usedFuelT));
		if (pathT!=null) {
			xmlw.writeAttribute("cities", Integer.toString(pathT.size()));
			for (int i=0; i<pathT.size();i++) {
				xmlw.writeStartElement("city");//Inizio city
				xmlw.writeAttribute("id", Integer.toString(pathT.get(i)));
				xmlw.writeAttribute("name", cities.get(pathT.get(i)).getName());
				xmlw.writeEndElement();//Chiusura city
			}
		}
		else
			xmlw.writeAttribute("cities", "0");
		xmlw.writeEndElement();//Chiusura route
		
		xmlw.writeStartElement("route");//Inizio route
		xmlw.writeAttribute("team", "Metztli");
		xmlw.writeAttribute("cost", Double.toString(usedFuelM));
		if (pathM!=null) {
			xmlw.writeAttribute("cities", Integer.toString(pathM.size()));
			for (int i=0; i<pathM.size();i++) {
				xmlw.writeStartElement("city");//Inizio city
				xmlw.writeAttribute("id", Integer.toString(pathM.get(i)));
				xmlw.writeAttribute("name", cities.get(pathM.get(i)).getName());
				xmlw.writeEndElement();//Chiusura city
			}
		}
		else
			xmlw.writeAttribute("cities", "0");
		xmlw.writeEndElement();//Chiusura route
		xmlw.writeEndElement();//Chiusura routes
		xmlw.writeEndDocument();
		xmlw.flush();
		xmlw.close();
		System.out.println("Scritto!");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
			
		}
	}
	
}


	
	
	
	
	


