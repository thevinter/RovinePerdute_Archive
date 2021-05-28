package it.unibs.fp.rovineperdute;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe per la lettura e la scrittura di file Xml.
 */
public class Xml {

    /**
     * legge un file xml e salva i dati delle citta in un ArrayList di tipo citta.
     * Il link alle altre citta viene preimpostato a 0.
     *
     * @param nome_file nome del file
     * @param citta     ArrayList di citta da popolare
     */
    public static void leggiCitta(String nome_file, ArrayList<Citta> citta) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        int id;
        String nome;
        Punto coordinata;
        HashMap<Integer, Double> percorso;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) {
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals(Costante.CITTA)) { //interessano solo i dati relativi alle citta
                    percorso = new HashMap<>();
                    coordinata = new Punto();

                    id = Integer.parseInt(xmlr.getAttributeValue(0)); // get ID
                    nome = xmlr.getAttributeValue(1); // get nome
                    coordinata.setX(Integer.parseInt(xmlr.getAttributeValue(2))); //get coordinate
                    coordinata.setY(Integer.parseInt(xmlr.getAttributeValue(3)));
                    coordinata.setZ(Integer.parseInt(xmlr.getAttributeValue(4)));

                    citta.add(new Citta(id, nome, coordinata)); //ottenuti tutti i valori dell'xml di una citta. Creazione citta senza link

                    xmlr.nextTag();

                    while ((xmlr.getEventType() == XMLStreamConstants.START_ELEMENT)) { // salvataggio link
                        percorso.put(Integer.parseInt(xmlr.getAttributeValue(Costante.C0)), Costante.C0_DOUBLE); // peso impostato a 0
                        xmlr.nextTag();
                        xmlr.nextTag();
                    }

                    citta.get(citta.size() - Costante.C1).setPercorsi(percorso); // finiti i link si aggiorna la citta
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scrivi il percorso dei vari team.
     *
     * @param nome_file nome del file da scrivere
     * @param team1     team 1
     * @param team2     team 2
     */
    public static void scriviPercorso(String nome_file, Team team1, Team team2) {

        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw;

        try { // blocco try per raccogliere eccezioni
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nome_file), Costante.ENCODING);
            xmlw.writeStartDocument(Costante.ENCODING, Costante.VERSION);
            xmlw.writeStartElement(Costante.ROUTES); // scrittura del tag radice Routes

            scriviTeam(xmlw, team1); //scrittura team
            scriviTeam(xmlw, team2);

            xmlw.writeEndElement(); // chiusura di </routes>
            xmlw.writeEndDocument(); // scrittura della fine del documento

            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(Costante.ERRORE_SCRITTURA);
            System.out.println(e.getMessage());
        }
    }

    private static void scriviTeam(XMLStreamWriter xmlw, Team team) throws XMLStreamException {
        xmlw.writeStartElement(Costante.ROUTE); // apertura del tag <route>
        xmlw.writeAttribute(Costante.TEAM, team.getVeicolo()); // scrittura attributo veicolo
        xmlw.writeAttribute(Costante.COSTO, String.format(Costante.FORMAT, team.getCarburante_tot())); // scrittura attributo carburante
        xmlw.writeAttribute(Costante.NUMERO_CITTA, Integer.toString(team.getPercorso_minimo().size())); // scrittura attributo numero citta percorse

        for (int i = team.getPercorso_minimo().size() - Costante.C1; i >= Costante.C0; i--) {
            xmlw.writeStartElement(Costante.CITTA); // apertura del tag <city>
            xmlw.writeAttribute(Costante.ID, Integer.toString(team.getPercorso_minimo().get(i).getId())); // attributo id
            xmlw.writeAttribute(Costante.NOME, team.getPercorso_minimo().get(i).getNome()); // attributo nome
            xmlw.writeEndElement(); // chiusura di </city>
        }
        xmlw.writeEndElement(); // chiusura di </route>
    }

    /**
     * Formatta file xml.
     *
     * @param file the file
     * @throws Exception the exception
     */
    public static void formatXMLFile(String file) throws Exception { //prende un xml non formattato e lo formatta

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, Costante.METODO_FORMATTAZIONE); //tipo di file generato
        transformer.setOutputProperty(OutputKeys.INDENT, Costante.INDENT_FORMATTAZIONE); //indentazione
        transformer.setOutputProperty(Costante.HTTPS_FORMATTAZIONE, Costante.LIVELLO_INDENTAZIONE); //formattazione
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, Costante.DICHIARAZIONE_FORMATTAZIONE);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(file));
        transformer.transform(source, result);
    }
}