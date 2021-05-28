package arnaldo.anno2021.triumvirato.rovineperdute;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class InputXML {

	private static void handleFilesNotFoundException() {
		System.out.println(Constants.ERROR_MESSAGE_FILE_ASSENTI);
		ProgramStatusInfo.setUnexecutable();
	}
	
	private static XMLStreamReader getXmlReader(String filename) {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			
		} catch (Exception e){
			handleFilesNotFoundException();
		}
		
		return xmlr;
	}
	
	
	
	/**
	 * Legge l'xml e costruisce un oggetto Mappa
	 * @param filename path del file.xml da leggere
	 * @return l'oggetto mappa costruito
	 */
	public static Mappa inputMappa(String filename) {
		
		//variabili di lettura
		XMLStreamReader xmlr=getXmlReader(filename);
		String selezione="";
		
		Mappa mappaTotale=new Mappa();
		ArrayList<Villaggio> villaggiMappa=mappaTotale.getVillaggi(); //riferimenti per migliorare la lettura
		ArrayList<Sentiero> sentieriMappa=mappaTotale.getSentieri();
		
		if(xmlr!=null)
		try {
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_ELEMENT:
						 
						 selezione=xmlr.getLocalName();
						 
						 /*
						 if(selezione.equals("map")) {
							//non ha bisogno di salvarsi il numero di villaggi perché alla fine fa un .size(), è da aggiungere in caso di controlli
							 
						 }else*/ 
						 if(selezione.equals("city")) {
							 
							 int idVillaggio=Integer.parseInt(xmlr.getAttributeValue(0));
							 String nomeVillaggio=xmlr.getAttributeValue(1);
							 Posizione coordinate=new Posizione(Integer.parseInt(xmlr.getAttributeValue(2)),Integer.parseInt(xmlr.getAttributeValue(3)),Integer.parseInt(xmlr.getAttributeValue(4)));
							 
							 villaggiMappa.add(new Villaggio(idVillaggio,nomeVillaggio,coordinate));
							 

						 }else if(selezione.equals("link")) {
							 
							 int idDestinazione=Integer.parseInt(xmlr.getAttributeValue(0));
							 Villaggio attuale=mappaTotale.getVillaggi().get(mappaTotale.getVillaggi().size()-1);
							 sentieriMappa.add(new Sentiero(attuale.getId(),idDestinazione));
							 attuale.getIdCollegati().add(idDestinazione);
						 }
						 
						 
					 break;
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento
						 //non ce ne sono, quindi il programma non fa niente

					 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 
						 //quando ha finito di leggere tutto
						 if(xmlr.getLocalName().equals("map")) {
							 mappaTotale.preparaMappa();						 
						 }
						 
					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		
		return mappaTotale;
		
	}
	
	

}
