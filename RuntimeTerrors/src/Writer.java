import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

public class Writer
{
	private static final String SALUTO = "\nOutput generato correttamente, arrivederci";
	private static final String ERRORE = "\nErrore nel writer: ";
	
	public static void writeOutput()
	{
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try
		{
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Output/Percorso.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("output"); // scrittura del tag radice <output>
			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("routes");
			xmlw.writeCharacters("\n\t\t");
			xmlw.writeStartElement("route");
			xmlw.writeAttribute("team", "Tonathiu");
			xmlw.writeAttribute("cost", Double.toString(Math.round(DataProcessing.getDistTon())));
			xmlw.writeAttribute("cities", Integer.toString(DataProcessing.getPercorsoTon().size()));
			xmlw.writeCharacters("\n\t\t\t");
			for (int i = DataProcessing.getPercorsoTon().size() - 1; i >= 0; i--)
			{
				City c = DataProcessing.getPercorsoTon().get(i);
				xmlw.writeStartElement("città");
				xmlw.writeAttribute("id", Integer.toString(c.getId()));
				xmlw.writeAttribute("nome", c.getNome());
				xmlw.writeEndElement(); //</città>
				if (i != 0)
					xmlw.writeCharacters("\n\t\t\t");
				else
					xmlw.writeCharacters("\n\t\t");
			}
			xmlw.writeEndElement(); // </route>
			xmlw.writeCharacters("\n\n\t\t");
			xmlw.writeStartElement("route");
			xmlw.writeAttribute("team", "Metztli");
			xmlw.writeAttribute("cost", Double.toString(DataProcessing.getDistMet()));
			xmlw.writeAttribute("cities", Integer.toString(DataProcessing.getPercorsoMet().size()));
			xmlw.writeCharacters("\n\t\t\t");
			for (int i = DataProcessing.getPercorsoMet().size() - 1; i >= 0; i--)
			{
				City c = DataProcessing.getPercorsoMet().get(i);
				xmlw.writeStartElement("città");
				xmlw.writeAttribute("id", Integer.toString(c.getId()));
				xmlw.writeAttribute("nome", c.getNome());
				xmlw.writeEndElement(); //</città>
				if (i != 0)
					xmlw.writeCharacters("\n\t\t\t");
				else
					xmlw.writeCharacters("\n\t\t");
			}
			xmlw.writeEndElement(); // </route>
			xmlw.writeCharacters("\n\t");
			xmlw.writeEndElement(); // </routes>
			xmlw.writeCharacters("\n");
			xmlw.writeEndElement();//</output>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
			System.out.println(SALUTO);
		}
		catch (Exception e)
		{
			System.out.println(ERRORE);
			System.out.println(e.getMessage());
		}
	}
}
