package RovinePerdute;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

import java.util.ArrayList;


public class InputXml
{
    private static final String ERR_INIZ = "Errore nell'inizializzazione del reader: ";
    private static final String CITY = "city";
    private static final String ERR_NO_RIGA = "Errore: non esiste una nuova riga da leggere\n";
    public static final String MAP = "map";
    public static final String LINK = "link";


    private XMLStreamReader xmlr;
    private ArrayList<Nodo> vert =new ArrayList<>();
    private double [][] arc;


    /**
     * metodo per leggere il file di input
     * @param percorso percorso
     */
    public InputXml(String percorso)
    {
        int id = 0, x = 0, y = 0, h = 0;
        String nome = null;

        try
        {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(percorso, new FileInputStream(percorso));
        }
        catch (Exception e)
        {
            System.out.println(ERR_INIZ);
            System.out.println(e.getMessage());
        }

        try
        {
            while (xmlr.hasNext())
            {
                // prendo il numero di nodi che saranno presenti nella mappa
                if (xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase(MAP)) {
                    int numeroNodi = Integer.parseInt(xmlr.getAttributeValue(0)); // leggo dimensione mappa
                    arc = new double[numeroNodi][numeroNodi]; // creo la matrice delle interazioni
                    for (int i = 0; i < arc.length; i++)
                    {
                        for (int j = 0; j < arc.length; j++)
                        {
                            if(i==j) arc[i][j]=0; // arco a->a nullo
                            else arc[i][j]=-1; // di base arco a-b insesistente se <0
                        }
                    }
                }
                // cerco il tag "city" per iniziare il parsing della città
                else if (xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase(CITY)) {
                    // ciclo sugli attributi per essere sicuro di leggere quello giusto indipendentemente dall'ordine
                    for (int i = 0; i < xmlr.getAttributeCount(); i++)
                    {
                        if (xmlr.getAttributeLocalName(i).equalsIgnoreCase("h"))
                            h = Integer.parseInt(xmlr.getAttributeValue(i)); // caso h
                        else if (xmlr.getAttributeLocalName(i).equalsIgnoreCase("x"))
                            x = Integer.parseInt(xmlr.getAttributeValue(i)); // caso x
                        else if (xmlr.getAttributeLocalName(i).equalsIgnoreCase("y"))
                            y = Integer.parseInt(xmlr.getAttributeValue(i)); // caso y
                        else if (xmlr.getAttributeLocalName(i).equalsIgnoreCase("id"))
                            id = Integer.parseInt(xmlr.getAttributeValue(i)); // caso id
                        else if (xmlr.getAttributeLocalName(i).equalsIgnoreCase("name"))
                            nome = xmlr.getAttributeValue(i); // caso nome
                    }
                    // aggiungo il nodo che ho appena letto
                    vert.add(new Nodo(x, y, h, id, nome));
                }
                else if (xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase(LINK)) // leggo i link
                    {
                        // inserisco nella tabella delle connessioni l'informazione che da a(riga) a b(colonna) c'è un collegamento
                        arc[id][Integer.parseInt(xmlr.getAttributeValue(0))] = 1;
                    }
                        xmlr.next();
                    }
        }
        catch (Exception e)
        {
            System.out.println(ERR_NO_RIGA);
        }
    }


    public ArrayList<Nodo> getVert() {
        return vert;
    }

    public double [][] getArc()
    {
        return arc;
    }
}




