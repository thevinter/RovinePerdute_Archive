package it.unibs.pgrArnaldo.CuoriSolitari.RovinePerdute;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Stack;

public class Xml {


    /**
     * Crea un arraylist di città dopo aver ricevuto in input un XML
     *
     * @param file
     * @return array_city
     */
    public static ArrayList<City> readCity(File file) {

        ArrayList<City> array_city = new ArrayList<>();
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(String.valueOf(file), new FileInputStream(file));
            int id = 0;
            String name = null;
            Double x, y, h;
            Position pos = null;
            ArrayList<Integer> link_id = new ArrayList<>();

            while (xmlr.hasNext()) {

                switch (xmlr.getEventType()) {

                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        if ((xmlr.getLocalName()) == "city") {

                            //Legge l'id e lo trasforma da stringa in intero
                            String _id = xmlr.getAttributeValue(0);
                            id = Integer.parseInt(_id);

                            //Legge il nome
                            name = xmlr.getAttributeValue(1);

                            //Legge le coordinate e poi le trasforma da stringhe a interi e infine crea una posizione
                            String _x = xmlr.getAttributeValue(2);
                            String _y = xmlr.getAttributeValue(3);
                            String _h = xmlr.getAttributeValue(4);

                            x = Double.parseDouble(_x);
                            y = Double.parseDouble(_y);
                            h = Double.parseDouble(_h);
                            pos = new Position(x, y, h);
                        } else if ((xmlr.getLocalName()) == "link") {

                            String _link = xmlr.getAttributeValue(0);
                            link_id.add(Integer.parseInt(_link));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if ((xmlr.getLocalName()) == "city") {
                            //crea e aggiunge la città all'array
                            City city = new City(pos, name, id, link_id);
                            array_city.add(city);
                            //svuota l'array di link
                            link_id = new ArrayList<>();
                        }

                        break;
                    case XMLStreamConstants.COMMENT:
                        System.out.println("// commento " + xmlr.getText());
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + xmlr.getEventType());
                }
                xmlr.next();
            }
            xmlr.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        return array_city;
    }


    /**
     * Ricevendo in input il veicolo scrive in un XML il percorso più efficiente per quel particolare veicolo
     *
     * @param
     */
    public static void writeRoad(ArrayList<Vertex> vertex1, ArrayList<Vertex> vertex2, Stack<Integer> stack1, Stack<Integer> stack2) {

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;

        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("RovinePerdute/src/it/unibs/pgrArnaldo/CuoriSolitari/RovinePerdute/Routes.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
            xmlw.writeCharacters("\n");

            xmlw.writeStartElement("routes");
            xmlw.writeCharacters("\n");
                //Route del veicolo Tonathiu
                xmlw.writeCharacters("  ");
                xmlw.writeStartElement("route");
                xmlw.writeAttribute("team", "Tonathiu");
                xmlw.writeAttribute("cost", String.valueOf(vertex1.get(vertex1.size() - 1).getDistance()));
                xmlw.writeAttribute("cities", String.valueOf(stack1.size()));
                xmlw.writeCharacters("\n");
                    for(int i = 0; i < stack1.size(); i++){
                        xmlw.writeCharacters("      ");
                        xmlw.writeStartElement("city");
                        xmlw.writeAttribute("id", String.valueOf(vertex1.get(stack1.get(i)).getCity().getId()));
                        xmlw.writeAttribute("name", String.valueOf(vertex1.get(stack1.get(i)).getCity().getName()));
                        xmlw.writeEndElement();
                        xmlw.writeCharacters("\n");
                    }
                xmlw.writeCharacters("  ");
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n");

                //Route del veicolo Metztli
                xmlw.writeCharacters("  ");
                xmlw.writeStartElement("route");
                xmlw.writeAttribute("team", "Metztli");
                xmlw.writeAttribute("cost", String.valueOf(vertex2.get(vertex2.size() - 1).getDistance()));
                xmlw.writeAttribute("cities", String.valueOf(stack2.size()));
                xmlw.writeCharacters("\n");
                    for(int i = 0; i < stack2.size(); i++){
                        xmlw.writeCharacters("      ");
                        xmlw.writeStartElement("city");
                        xmlw.writeAttribute("id", String.valueOf(vertex2.get(stack2.get(i)).getCity().getId()));
                        xmlw.writeAttribute("name", String.valueOf(vertex2.get(stack2.get(i)).getCity().getName()));
                        xmlw.writeEndElement();
                        xmlw.writeCharacters("\n");
                    }
                xmlw.writeCharacters("  ");
                xmlw.writeEndElement();

            xmlw.writeCharacters("\n");
            xmlw.writeEndElement();

            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();
        } catch (Exception e) {
            System.out.println("Errore nella scrittura");
        }
    }
}

