package it.unibs.arnaldo.rovinePerdute;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Grafo {
    //ATTRIBUTI costanti perchè il grafo non potrà essere modificato
    private final Set<Luogo> nodi;
    //              <id luogo, set di id luoghi connessi>
    private final Map<Integer, Set<Integer>> archi;

    //COSTRUTTORE
    public Grafo(Set<Luogo> nodi, Map<Integer, Set<Integer>> archi) {
        this.nodi = nodi;
        this.archi = archi;
    }

    /**
     * <h3>Metodo che comunica il numeor dei nodi(città) present nel grafo</h3>
     * @return numero dei nodi presenti nel set
     */
    public int getNumeroNodi(){
        return this.nodi.size();
    }

    //METODI
   /**
     * <h3>Metodo che dato l'id ritorna il luogo corrispondente</h3>
     * @param id_da_cercare rappresenta l'id del luogo da cercare
     * @return luogo che corrisponde all'id inserito
     */
    public Luogo getNodo(int id_da_cercare) {
        return nodi.stream() //genera un flusso di nodi (da analizzare)
                .filter(nodo -> nodo.getId() == id_da_cercare) //tramite un filtro controlla l'id di ogni nodo, se è uguale all'id da cercare crea uno stream con il nodo trovato
                .findFirst() //ritorna il primo elemento dello stream generato dal filtro
                .orElseThrow(() -> new IllegalArgumentException(Costanti.ERR_ID_NON_TROVATO)); //se lo trova ritorna il Luogo altrimenti se non lo trova lancia l'errore
    }

    /**
     * <h3>Metodo che dato il nodo ritorna tutti gli archi uscenti</h3>
     * @param nodo_da_cercare ovvero il luogo sul quale si vogliono conoscere le connessioni
     * @return tutte le connesioni del luogo
     */
    public Set<Luogo> getArchi(Luogo nodo_da_cercare) {
        return archi.get(nodo_da_cercare.getId()).stream() //ricava la value dalla key nodo e genera un flusso di connessioni
                    .map(this::getNodo) //ritorna uno stream dopo aver applicato il metodo getNode ad ogni oggetto
                    .collect(Collectors.toSet()); //trasforma lo stream in un Set

    }
}
    /*
    //salvo l'id del nodo da cercare
    int id_luogo_da_cercare = nodo_da_cercare.getId();
    //creo un set di id dei luoghi connessi
    Set<Integer> id_nodi_connessi = archi.get(id_luogo_da_cercare);
    //genero uno Stream con gli oggetti che corrispondono agli id_nodi: (con il map(::) applico il metodo a tutto lo stream)
    Stream<Luogo> luoghi_connessi = id_nodi_connessi.stream().map(this::getNode);
    //con il metodo .collect strasformo lo stream in un set con Collectors.toSet()
    Set<Luogo> connessioni_da_cercare = luoghi_connessi.collect(Collectors.toSet());
    return connessioni_da_cercare;
     */
