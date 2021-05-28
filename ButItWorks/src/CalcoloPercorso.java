import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CalcoloPercorso {
    private static void resetMappaCitta(ArrayList<Citta> listaCitta) {
        for (Citta c : listaCitta) {
            c.setDistanza(Double.MAX_VALUE);
            c.setCittaPrecedente(new Citta());
            c.getIdPercorso().clear();
        }
    }

    private static void modificaCitta(Citta cittaPartenza, Citta cittaArrivo, double distanzaTotale) {
        cittaArrivo.setDistanza(distanzaTotale);
        cittaArrivo.setCittaPrecedente(cittaPartenza);
        /*
        cittaArrivo.setNumeroCittaPrecedenti(cittaPartenza.getNumeroCittaPrecedenti() + 1);
        for (int id : cittaPartenza.getIdPercorso()) {
            cittaArrivo.inserisciIdPercorso(id);
        }
        cittaArrivo.inserisciIdPercorso(cittaPartenza.getId());
        */
    }

    private static Citta getCittaDistanzaMinore(Set<Citta> cittaDisponibili) {
        Citta cittaDistanzaMinima = new Citta();
        double distanzaMinima = Integer.MAX_VALUE;

        for (Citta citta: cittaDisponibili) {
            double distanzaCitta = citta.getDistanza();
            if (distanzaCitta < distanzaMinima) {
                distanzaMinima = distanzaCitta;
                cittaDistanzaMinima = citta;
            }
            else if(distanzaCitta == distanzaMinima && (citta.getNumeroCittaPrecedenti() + 1) < cittaDistanzaMinima.getNumeroCittaPrecedenti()) {
                cittaDistanzaMinima = citta;
            }
            else if(distanzaCitta == distanzaMinima && (citta.getNumeroCittaPrecedenti() + 1) == cittaDistanzaMinima.getNumeroCittaPrecedenti() && citta.getMaxIdPercorso() > cittaDistanzaMinima.getMaxIdPercorso()) {
                cittaDistanzaMinima = citta;
            }
        }

        return cittaDistanzaMinima;
    }

    private static void calcolaDistanzaMinima(Citta p, Citta a, double distanza) {
        double distanzaTotale = p.getDistanza() + distanza;
        if (distanzaTotale < a.getDistanza()) {
            modificaCitta(p, a, distanzaTotale);
        }
        else if(distanzaTotale == a.getDistanza() && (p.getNumeroCittaPrecedenti() + 1) < a.getNumeroCittaPrecedenti()) {
            modificaCitta(p, a, distanzaTotale);
        }
        /*
        else if(distanzaTotale == a.getDistanza() && (p.getNumeroCittaPrecedenti() + 1) == a.getNumeroCittaPrecedenti() && p.getId() > a.getCittaPrecedente().getId() - (p.getMaxIdPercorso() > a.getMaxIdPercorso() || p.getId() > a.getMaxIdPercorso())) {
            modificaCitta(p, a, distanzaTotale);
        }
        */
    }

    public static ArrayList<Citta> calcoloPercorsoMinimo(ArrayList<Citta> mappa, Citta partenza, Citta arrivo, boolean isPercorsoTonatiuh) {
        resetMappaCitta(mappa);
        partenza.setDistanza(0);
        partenza.setNumeroCittaPrecedenti(0);
        Set<Citta> cittaValutate = new HashSet<>();
        Set<Citta> cittaDaValutare = new HashSet<>();
        ArrayList<Citta> percorsoMinore = new ArrayList<>();
        cittaDaValutare.add(partenza);

        while (cittaDaValutare.size() != 0) {
            Citta cittaCorrente = getCittaDistanzaMinore(cittaDaValutare);
            cittaDaValutare.remove(cittaCorrente);
            for (Citta cittaAdiacente: cittaCorrente.getCollegamenti()) {
                double distanza = 0;

                if(isPercorsoTonatiuh) {
                    distanza = cittaCorrente.getPosizione().distanzaEuclidea(cittaAdiacente.getPosizione());
                } else {
                    distanza = cittaCorrente.dislivello(cittaAdiacente.getAltezza());
                }

                if (!cittaValutate.contains(cittaAdiacente)) {
                    calcolaDistanzaMinima(cittaCorrente, cittaAdiacente, distanza);
                    cittaDaValutare.add(cittaAdiacente);
                }
            }
            cittaValutate.add(cittaCorrente);
        }

        do {
            percorsoMinore.add(arrivo);
            arrivo = arrivo.getCittaPrecedente();
        } while(arrivo.getId() != partenza.getId());
        percorsoMinore.add(partenza);
        Collections.reverse(percorsoMinore);

        return percorsoMinore;
    }

}
