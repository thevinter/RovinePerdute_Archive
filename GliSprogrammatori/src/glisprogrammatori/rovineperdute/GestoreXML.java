package glisprogrammatori.rovineperdute;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.stream.XMLStreamConstants;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestoreXML {
  private static final XMLInputFactory xml_input_factory = XMLInputFactory.newInstance();
  private static final XMLOutputFactory xml_output_factory = XMLOutputFactory.newInstance();
  private XMLStreamReader xml_stream_reader;
  private XMLStreamWriter xml_stream_writer;
  private String output_encoding;

  public GestoreXML(String input_file_path, String output_file_path, String output_encoding)
      throws FileNotFoundException, XMLStreamException {
    xml_stream_reader = xml_input_factory.createXMLStreamReader(input_file_path, new FileInputStream(input_file_path));
    this.output_encoding = output_encoding;
    xml_stream_writer = xml_output_factory.createXMLStreamWriter(new FileOutputStream(output_file_path),
        output_encoding);
  }

  private ArrayList<Nodo> impostaPesiArchi(ArrayList<Nodo> nodi) {
    Integer[] value;
    Punto posizione_nodo_corrente, posizione_nodo_link;

    for (Nodo nodo : nodi) {
      for (Map.Entry<Integer, Integer[]> entry : nodo.getArchi().entrySet()) {
        value = new Integer[2];

        posizione_nodo_corrente = ((City) nodo).getPosizione();
        posizione_nodo_link = ((City) nodi.get(entry.getKey())).getPosizione();

        value[0] = posizione_nodo_corrente.calcolaDistanzaEuclidea(posizione_nodo_link);
        value[1] = posizione_nodo_corrente.calcoloDifferenzaDiAltitudine(posizione_nodo_link);

        entry.setValue(value);
      }
    }

    return nodi;
  }

  public Grafo leggiXML() throws XMLStreamException {
    ArrayList<Nodo> nodi = new ArrayList<Nodo>();
    HashMap<Integer, Integer[]> archi = new HashMap<Integer, Integer[]>();
    int id = -1, x = 0, y = 0, key;
    Punto posizione = null;
    String nome = "Nome";

    while (xml_stream_reader.hasNext()) {
      if (xml_stream_reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
        if (xml_stream_reader.getLocalName().equals("city")) {
          for (int i = 0; i < xml_stream_reader.getAttributeCount(); i++)
            switch (xml_stream_reader.getAttributeLocalName(i)) {
              case "id":
                id = Integer.parseInt(xml_stream_reader.getAttributeValue(i));
                break;

              case "name":
                nome = xml_stream_reader.getAttributeValue(i);
                break;

              case "x":
                x = Integer.parseInt(xml_stream_reader.getAttributeValue(i));
                break;

              case "y":
                y = Integer.parseInt(xml_stream_reader.getAttributeValue(i));
                break;

              case "h":
                posizione = new Punto(x, y, Integer.parseInt(xml_stream_reader.getAttributeValue(i)));
                break;
            }
        } else if (xml_stream_reader.getLocalName().equals("link")) {
          key = Integer.parseInt(xml_stream_reader.getAttributeValue(0));
          archi.put(key, new Integer[2]);
        }
      } else if (xml_stream_reader.getEventType() == XMLStreamConstants.END_ELEMENT
          && xml_stream_reader.getLocalName().equals("city")) {
        nodi.add(new City(id, archi, nome, posizione));

        archi = new HashMap<Integer, Integer[]>();
      }

      xml_stream_reader.next();
    }

    nodi = impostaPesiArchi(nodi);

    return new Grafo(nodi);
  }

  public void stampaXML(Grafo percorso_prima_squadra, Grafo percorso_seconda_squadra) throws XMLStreamException {
    int carburante_utilizzato_prima_squadra = 0, carburante_utilizzato_seconda_squadra = 0;
    ArrayList<Nodo> citta_toccate_prima_squadra, citta_toccate_seconda_squadra;
    City citta_corrente;

    citta_toccate_prima_squadra = percorso_prima_squadra.getNodi();
    citta_toccate_seconda_squadra = percorso_seconda_squadra.getNodi();

    for (Nodo citta : citta_toccate_prima_squadra) {
      citta_corrente = (City) citta;

      for (Map.Entry<Integer, Integer[]> entry : citta_corrente.getArchi().entrySet()) {
        carburante_utilizzato_prima_squadra += entry.getValue()[0];
        break;
      }
    }

    for (Nodo citta : citta_toccate_seconda_squadra) {
      citta_corrente = (City) citta;

      for (Map.Entry<Integer, Integer[]> entry : citta_corrente.getArchi().entrySet()) {
        carburante_utilizzato_seconda_squadra += entry.getValue()[1];
        break;
      }
    }

    xml_stream_writer.writeStartDocument(output_encoding, "1.0");
    xml_stream_writer.writeCharacters("\n");

    xml_stream_writer.writeStartElement("routes");
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeStartElement("route");
    xml_stream_writer.writeAttribute("team", "Tonathiu");
    xml_stream_writer.writeAttribute("cost", String.valueOf(carburante_utilizzato_prima_squadra));
    xml_stream_writer.writeAttribute("cities", String.valueOf(citta_toccate_prima_squadra.size()));
    xml_stream_writer.writeCharacters("\n\t\t");

    for (int i = 0; i < citta_toccate_prima_squadra.size(); i++) {
      citta_corrente = (City) citta_toccate_prima_squadra.get(i);

      xml_stream_writer.writeEmptyElement("city");
      xml_stream_writer.writeAttribute("id", String.valueOf(citta_corrente.getId()));
      xml_stream_writer.writeAttribute("name", citta_corrente.getNome());

      if (i != (citta_toccate_prima_squadra.size() - 1))
        xml_stream_writer.writeCharacters("\n\t\t");
      else
        xml_stream_writer.writeCharacters("\n\t");
    }

    xml_stream_writer.writeEndElement(); // route
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeStartElement("route");
    xml_stream_writer.writeAttribute("team", "Metztli");
    xml_stream_writer.writeAttribute("cost", String.valueOf(carburante_utilizzato_seconda_squadra));
    xml_stream_writer.writeAttribute("cities", String.valueOf(citta_toccate_seconda_squadra.size()));
    xml_stream_writer.writeCharacters("\n\t\t");

    for (int i = 0; i < citta_toccate_seconda_squadra.size(); i++) {
      citta_corrente = (City) citta_toccate_seconda_squadra.get(i);

      xml_stream_writer.writeEmptyElement("city");
      xml_stream_writer.writeAttribute("id", String.valueOf(citta_corrente.getId()));
      xml_stream_writer.writeAttribute("name", citta_corrente.getNome());

      if (i != (citta_toccate_seconda_squadra.size() - 1))
        xml_stream_writer.writeCharacters("\n\t\t");
      else
        xml_stream_writer.writeCharacters("\n\t");
    }

    xml_stream_writer.writeEndElement(); // route
    xml_stream_writer.writeCharacters("\n");

    xml_stream_writer.writeEndElement(); // routes

    xml_stream_writer.writeEndDocument();
  }
}
