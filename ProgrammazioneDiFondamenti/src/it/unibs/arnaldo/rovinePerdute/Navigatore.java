package it.unibs.arnaldo.rovinePerdute;

import java.util.*;

public class Navigatore {

    //ATTRIBUTI
    private final Grafo grafo;

    /**
     * <h3>Metodo costruttore</h3>
     * @param grafo grafo rappresentante la mappa su cui si sposta il navigatore
     */
    public Navigatore(Grafo grafo) {
        this.grafo = grafo;
    }

    /**
     * <h3>Metodo per il calcolo del cammino minimo (in base alla tipologia del veicolo che lo percorerrà) tra due luoghi</h3>
     * @param luogo_partenza corrisponde al campo base
     * @param luogo_arrivo corrisponde alla rovina perduta
     * @param veicolo ovvero il veicolo utilizzato (quello di metztli oppure tonathiu)
     * @return
     */
    public ArrayList<Luogo> trovaPercorso(Luogo luogo_partenza, Luogo luogo_arrivo, Veicolo veicolo){
        //creo una Queue che sarà il mio percorso
        ArrayList<NodoEsteso> percorso_array = new ArrayList<>();
        //creo una Map che associa al Luogo il suo NodoEsteso, ovvero il Luogo con le info aggiuntive
        //questa map salverà tutti i luoghi visitati fin ora
        Map<Luogo, NodoEsteso> nodi_visitati = new HashMap<>();
        double carburante = calcolaCarburante(luogo_partenza, luogo_arrivo, veicolo.getTipologia());
        //creo il primo nodo con formazioni aggiuntive, sarà il nodo di luogo_partenza
        NodoEsteso nodo_partenza = new NodoEsteso(luogo_partenza, null, 0d, carburante);
        //lo aggiungo al percorso
        percorso_array.add(0, nodo_partenza);
        //lo aggiungo ai nodi visitati
        nodi_visitati.put(luogo_partenza, nodo_partenza);

        //ciclo fino a quando ci sono elementi
        while (!percorso_array.isEmpty()){
            riordina(percorso_array);
            //prelevo il primo nodo dell'array
            NodoEsteso nodo_ripartenza = percorso_array.get(0);
            percorso_array.remove(0);
            //controllo che il luogo selezionato corrisponda al luogo di arrivo
            if(nodo_ripartenza.getLuogo_corrente().equals(luogo_arrivo)){
                ArrayList<Luogo> strada = new ArrayList<>();
                NodoEsteso nodo_corrente = nodo_ripartenza;
                veicolo.setFuel(nodo_ripartenza.getCarburante_utilizzato());
                do{
                    //inserisco ogni elemento in pos 0, affinchè gli altri si spostino di una pos avanti nell'arraylist
                    strada.add(0, nodo_corrente.getLuogo_corrente());
                    //il nodo corrente diventa il nodo precedente
                    nodo_corrente = nodi_visitati.get(nodo_corrente.getLuogo_precedente());
                }while (nodo_corrente != null);
                //ritornerà un arrayList contenente il percorso dal campo base fino alle rovinePerdute
                return strada;
            }
            //salvo il luogo del nodo da dove ero ripartito(ovvero il suo luogo esteso)
            Luogo luogo_nodo_ripartenza = nodo_ripartenza.getLuogo_corrente();
            //creo un set dei luoghi connessi al nodo di ripartenza
            Set<Luogo> luoghi_connessi_al_nodo_ripartenza = grafo.getArchi(luogo_nodo_ripartenza);
            //ciclo tutte le connessioni al nodo di ripartenza
            for(Luogo luogo_connesso : luoghi_connessi_al_nodo_ripartenza){
                //estendo il luogo appena analizzato nei luoghi connessi al luogo di ripartenza
                NodoEsteso nodo_esteso_default = new NodoEsteso(luogo_connesso);
                //nodo del luogo_connesso, in caso non ci sia diventa un nodo esteso tramite il costruttore di default
                NodoEsteso nodo_connesso = nodi_visitati.getOrDefault(luogo_connesso, nodo_esteso_default);
                //aggiunta del nodo visitato
                nodi_visitati.put(luogo_connesso, nodo_connesso);
                //calcolo il carburante tra il nodo di ripartenza e il luogo connesso
                double carburante_tappa_singola =  calcolaCarburante(nodo_ripartenza.getLuogo_corrente(), luogo_connesso, veicolo.getTipologia());
                //calcolo il carburante che ho speso dalla partenza al luogo connesso
                double carburante_fino_connessione = nodo_ripartenza.getCarburante_utilizzato() + carburante_tappa_singola;

                /*se sono già arrivato precedentemente al nodo connesso utilizzando meno carburante, allora non sarà la strada giusta
                  inizialmente il carburante utilizzato è = infininto, ogni volta che posso raggiungere il nodo conneso con meno carburante
                  allora aggiornerò il carburante utilizzato ad arrivare fino li, aggiornerò il luogo da dove arrivo al nodo connesso, ovvero
                  il nodo in cui mi trovo, e aggiorno il carburante stimato fino all'arrivo, infine aggiungo il nodo al percorso
                 */
                if(carburante_fino_connessione < nodo_connesso.getCarburante_utilizzato()){
                    nodo_connesso.setLuogo_precedente(nodo_ripartenza.getLuogo_corrente());
                    nodo_connesso.setCarburante_utilizzato(carburante_fino_connessione);
                    nodo_connesso.setCarburante_stimato(carburante_fino_connessione + calcolaCarburante(luogo_connesso, luogo_arrivo, veicolo.getTipologia()));
                    int id_nodo_prec = nodo_connesso.getLuogo_corrente().getId();
                    //se é giá presente questo luogo lo tolgo e lo reinserisco, cosí non avró doppioni nell'arraylist
                    for (int i=0; i<percorso_array.size(); i++){
                        if (percorso_array.get(i).getLuogo_corrente().getId() == id_nodo_prec){
                            percorso_array.remove(percorso_array.get(i));
                        }
                    }
                    percorso_array.add(nodo_connesso);
                }
            }
        }
        throw new IllegalStateException(Costanti.ERRORE_STRADA);
    }

    /**
     * <h3>Metodo che calcola il carburante tra due luoghi, in base alla tipologia del veicolo<br>
     *     visto che hanno modi differenti di condumare il carburante</h3>
     * @param luogo_partenza
     * @param luogo_arrivo
     * @param tipologia ovvero la tipologia del veicolo (metztli o tonathiu)
     * @return
     */
    private static double calcolaCarburante(Luogo luogo_partenza, Luogo luogo_arrivo, String tipologia){
        switch (tipologia){
            case Costanti.METZTLI:
                int h1 = luogo_partenza.getPosizione().getH();
                int h2 = luogo_arrivo.getPosizione().getH();
                return Math.abs(h1-h2);
            case Costanti.TONATHIU: //distanza euclidea
                int x1 = luogo_partenza.getPosizione().getX();
                int x2 = luogo_arrivo.getPosizione().getX();
                int y1 = luogo_partenza.getPosizione().getY();
                int y2 = luogo_arrivo.getPosizione().getY();
                return (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)));
            default: throw new IllegalArgumentException(Costanti.ERRORE_TIPOLOGIA);
        }
    }

    /**
     * <h3>Metodo per l'ordinamento dell'array in base al consumo instantaneo</h3>
     * @param array_nodi ovvero i nodi dell'array
     */
    private static void riordina(ArrayList<NodoEsteso> array_nodi){
        divide(0, array_nodi.size()-1, array_nodi);
    }

    /**
     * <h3>Metodo per l'ordinamento dell'array che divide la lista fino ai singoli elementi </h3>
     * @param startIndex l'elemento iniziale
     * @param endIndex l'elemento finale
     * @param array_nodi ovvero i nodi dell'array
     */
    private static void divide(int startIndex,int endIndex, ArrayList<NodoEsteso> array_nodi){
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid, array_nodi);
            divide(mid+1, endIndex, array_nodi);
            //merging Sorted array produce above into one sorted array
            merger(startIndex,mid,endIndex, array_nodi);
        }
    }

    /**
     * <h3>Metodo per l'ordinamento dell'array che riassembla l'array</h3>
     * @param startIndex valore iniziale
     * @param midIndex valore intermedio
     * @param endIndex valore finale
     * @param array_nodi ovvero i nodi dell'array
     */
    private static void merger(int startIndex,int midIndex,int endIndex, ArrayList<NodoEsteso> array_nodi){

        //Below is the merged array that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<NodoEsteso> mergedSortedArray = new ArrayList<NodoEsteso>();

        int leftIndex = startIndex;
        int rightIndex = midIndex+1;

        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(array_nodi.get(leftIndex).getCarburante_stimato()<array_nodi.get(rightIndex).getCarburante_stimato()){
                mergedSortedArray.add(array_nodi.get(leftIndex));
                leftIndex++;
            }else if(array_nodi.get(leftIndex).getCarburante_stimato()>array_nodi.get(rightIndex).getCarburante_stimato()){
                mergedSortedArray.add(array_nodi.get(rightIndex));
                rightIndex++;
            }else{
                if(array_nodi.get(leftIndex).getLuogo_corrente().getId()>array_nodi.get(rightIndex).getLuogo_corrente().getId()){
                    mergedSortedArray.add(array_nodi.get(leftIndex));
                    leftIndex++;
                }else {
                    mergedSortedArray.add(array_nodi.get(rightIndex));
                    rightIndex++;
                }
            }
        }

        //Either of below while loop will execute
        while(leftIndex<=midIndex){
            mergedSortedArray.add(array_nodi.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex<=endIndex){
            mergedSortedArray.add(array_nodi.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            array_nodi.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
}