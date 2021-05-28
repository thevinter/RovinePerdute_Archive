import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Reader
{
	private static final String ERRORE = "\nErrore in Input: ";
	private static final String FILE = "Input/test_file/";
	
	public static void readInput(String filename)
	{
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try
		{
			int id = -1, x = -1, y = -1, h = -1;
			String tagName = "";
			String nome = "";
			ArrayList<Integer> links = new ArrayList<>();
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(new FileInputStream(FILE + filename));
			while (xmlr.hasNext())
			{ // continua a leggere finche ha eventi a disposizione
				switch (xmlr.getEventType())
				{ // switch sul tipo di evento
					case XMLStreamConstants.START_ELEMENT: // inizio di un elemento
						tagName = xmlr.getLocalName();
						if (tagName.equals("city"))
						{
							id = Integer.parseInt(xmlr.getAttributeValue(0));
							nome = xmlr.getAttributeValue(1);
							x = Integer.parseInt(xmlr.getAttributeValue(2));
							y = Integer.parseInt(xmlr.getAttributeValue(3));
							h = Integer.parseInt(xmlr.getAttributeValue(4));
						}
						else if (tagName.equals("link"))
						{
							links.add(Integer.parseInt(xmlr.getAttributeValue(0)));
						}
						break;
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento
						if (xmlr.getLocalName().equals("city"))
						{
							DataProcessing.getCities().add(new City(nome, id, new Posizione(x, y, h), new ArrayList<Integer>(links)));
							links.removeAll(links);
						}
						break;
				}
				xmlr.next();
			}
			DataProcessing.blackMagic();
		}
		catch (Exception e)
		{
			System.out.println(ERRORE + filename);
			System.out.println(e.getMessage());
		}
	}
}