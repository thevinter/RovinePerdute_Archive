package rovine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Cartina {
    private static final String NOME_FILE_OUTPUT="Routes.xml";
    private static final String STRINGA_INIZIO_STAMPA="mappa ottimale generata.\ninizio generazione del file di output";
    public Map<Arco,Double[]> mappaTerritorio;
    public Map<Nodo, Etichetta> mappaV1=new HashMap<>();
    public Map<Nodo, Etichetta> mappaV2=new HashMap<>();

    /**
     * costruttore della classe cartina
     * @param mappaTerritorio mappa formata da archi come chiavi e un array statico Double come valore
     */
    public Cartina(Map<Arco, Double[]> mappaTerritorio) {
        this.mappaTerritorio = mappaTerritorio;
    }

    /**
     * dato un insieme di nodi e una mappa contenente solo gli id dei nodi e i rispettivi id dei nodi di arrivo,
     * genera il percorso ottimale tra il nodo di partenza (con id = 0) e il nodo di arrivo (con l'id più alto).
     * il tutto utilizzando l'algoritmo di Dijkstra
     * @param insiemeNodi insieme dei nodi
     * @param mappaArchi mappa degli archi in base agli id
     */
    public void generaPercorsoOttimale(ArrayList<Nodo> insiemeNodi, Map<Integer, ArrayList<Integer>> mappaArchi){
        //creo 2 liste dove una conterrà i nodi da controllara (in coda) mentre l'altra conterra i nodi già presenti (in modo da evitare cicli infiniti
        LinkedList<Nodo> listaBFS = new LinkedList<>();
        ArrayList<Nodo> listaRiempimento = new ArrayList<>();

        //aggiungo alle 2 liste il nodo con id = 0 e inizio a popolare le 2 mappe con i rispettivi nodi ed etichette
        for (Nodo n: insiemeNodi) {
            if(n.getId()==0){
                mappaV1.put(n,new Etichetta(n,0,0));
                mappaV2.put(n,new Etichetta(n,0,0));
                listaBFS.add(n);
                listaRiempimento.add(n);
            }
            else{
                mappaV1.put(n,null);
                mappaV2.put(n,null);
            }
        }

        //continuo il ciclo finche la linkedList non è vuota
        while(!listaBFS.isEmpty()){
            //assegno ad n1 il primo elemento della lista, rimuovendolo da essa
            Nodo n1 = listaBFS.removeFirst();
            int i = n1.getId();
            //creò un ciclo per ogni elemento "attacato" al nodo n1, in base alla mappaArchi
            for(int j : mappaArchi.get(i)){
                //creo un nodo di riferimento con id j
                Nodo n2 = new Nodo(j);
                //se il nodo non è presente nell'ArrayList, lo aggiungo ad entrambi
                if(!listaRiempimento.contains(n2)){
                    listaBFS.add(n2);
                    listaRiempimento.add(n2);
                }
                //calcolo la distanza dal nodo d'origine e n2 per il primo veicolo
                double carburanteDaBaseaN2V1 = mappaTerritorio.get(new Arco(n1,n2))[0]+mappaV1.get(n1).getDistanza();
                assegnazioneMetodo(mappaV1, i, n1, j, n2, carburanteDaBaseaN2V1, insiemeNodi);
                //calcolo la distanza dal nodo d'origine e n2 per il secondo veicolo
                double carburanteDaBaseaN2V2 = mappaTerritorio.get(new Arco(n1,n2))[1]+mappaV2.get(n1).getDistanza();
                assegnazioneMetodo(mappaV2, i, n1, j, n2, carburanteDaBaseaN2V2, insiemeNodi);
            }
        }

        //creo le liste che conterrannò il percorso delle 2 macchine
        ArrayList<Nodo> listaVeicolo1 = new ArrayList<>();
        ArrayList<Nodo> listaVeicolo2 = new ArrayList<>();
        Nodo n = insiemeNodi.get(insiemeNodi.size()-1);
        int carburanteVeicolo1 = (int) mappaV1.get(n).getDistanza();
        int carburanteVeicolo2 = (int) mappaV2.get(n).getDistanza();
        //partendo dall'ultimo nodo guardo l'etichetta e aggiungo alla lista i nodi per entrambi i veicoli
        while(n.getId()!=0){
            listaVeicolo1.add(n);
            n=mappaV1.get(n).getFrom();
        }
        listaVeicolo1.add(n);
        n = insiemeNodi.get(insiemeNodi.size()-1);
        while(n.getId()!=0){
            listaVeicolo2.add(n);
            n=mappaV2.get(n).getFrom();
        }
        listaVeicolo2.add(n);
        //creo il file xml tramite la classe writerXML
        WriterXML scrittore = new WriterXML();
        System.out.println(STRINGA_INIZIO_STAMPA);
        scrittore.ScriviXML(listaVeicolo1, listaVeicolo2, carburanteVeicolo1, carburanteVeicolo2,Main.firstPath+NOME_FILE_OUTPUT);
    }

    /**
     * data la mappa dei nodi, i nodi con id i e j e il carburante, controllo la sua etichetta e in caso la modifico.
     * @param mappa mappa delle etichette dei nodi
     * @param i indice del nodo di partenza
     * @param n1 nodo di partenza
     * @param j indice del nodo di arrivo
     * @param n2 nodo di arrivo
     * @param carburanteDaBaseaN2 carburante dal nodo di origine ad n2
     * @param insiemeNodi insieme di tutti i nodi
     */
    private void assegnazioneMetodo(Map<Nodo, Etichetta> mappa, int i, Nodo n1, int j, Nodo n2, double carburanteDaBaseaN2, ArrayList<Nodo> insiemeNodi) {
        //assegno un etichetta nel caso non ci sia
        if(mappa.get(n2)==null){
            mappa.put(n2,new Etichetta(insiemeNodi.get(i), carburanteDaBaseaN2, mappa.get(n1).getNumeroCitta()+1));
            //altrimenti modifico l'etichetta in base al percorso che consuma meno carburante
        }else if(mappa.get(n2).getDistanza()!=carburanteDaBaseaN2){
            if(mappa.get(n2).getDistanza()>carburanteDaBaseaN2){
                mappa.put(n2,new Etichetta(insiemeNodi.get(i), carburanteDaBaseaN2, mappa.get(n1).getNumeroCitta()+1));
            }
            //altrinmenti modifico l'etichetta in base al minor numero di città passate
        }else{
            if(mappa.get(n1).getNumeroCitta()!=mappa.get(n2).getNumeroCitta()){
                if(mappa.get(n2).getNumeroCitta()>mappa.get(n1).getNumeroCitta()+1){
                    mappa.put(n2,new Etichetta(insiemeNodi.get(i), carburanteDaBaseaN2, mappa.get(n1).getNumeroCitta()+1));
                }
                //altrimenti modifico l'etichetta in base all'id piu basso tra i 2 percorsi
            }else{
                if(i>j) mappa.put(n2,new Etichetta(insiemeNodi.get(i), carburanteDaBaseaN2, mappa.get(n1).getNumeroCitta()+1));
            }
        }
    }
}
