package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        int scelta = Utente.scegliFile();

        while (scelta != Costante.C0) { // scelta utente, quando seleziona 0 il programma termina

            ArrayList<Citta> citta = new ArrayList<>();

            Xml.leggiCitta(String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - Costante.C1]), citta); //Lettura dei documenti informativi

            Team team1 = new Team(Costante.TEAM_NOME1); // Creazione team
            Team team2 = new Team(Costante.TEAM_NOME2);

            Rovina rovina = new Rovina(citta); // setup

            Citta.calcolaPesoPercorso(team1); // calcolo pesi
            team1.dijkstra(citta.get(Costante.C0)); // algoritmo dijkstra

            Citta.calcolaPesoPercorso(team2); // calcolo pesi
            team2.dijkstra(citta.get(Costante.C0)); // algoritmo dijkstra

            Xml.scriviPercorso(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[scelta - Costante.C1]), team1, team2); // Scrittura del documento finale
            Xml.formatXMLFile(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[scelta - Costante.C1])); // formattazione file

            scelta = Utente.scegliFile();
        }
    }
}