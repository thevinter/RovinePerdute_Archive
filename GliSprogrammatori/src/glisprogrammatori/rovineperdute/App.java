package glisprogrammatori.rovineperdute;

import it.unibs.fp.mylib.Menu;

/**
 * <p>
 * La classe <strong>App</strong> è la classe principale dove è racchiuso il
 * main e da cui parte il programma
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class App {
  public static void main(String[] args) throws Exception {
    GestoreXML gestoreXML = null;
    Menu program_menu = new Menu(OutputStringhe.TITOLO_MENU, OutputStringhe.VOCI_MENU, true);
    int scelta;

    scelta = program_menu.scegli();

    switch (scelta) {
      case 1:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_CINQUE_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;

      case 2:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_DODICI_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;

      case 3:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_CINQUANTA_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;

      case 4:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_DUECENTO_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;

      case 5:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_DUEMILA_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;

      case 6:
        gestoreXML = new GestoreXML(OutputStringhe.MAPPA_DIECIMILA_CITTA, OutputStringhe.MAPPA_OUTPUT,
            OutputStringhe.OUTPUT_ENCODING);
        break;
    }

    if (scelta > 0 && scelta <= OutputStringhe.VOCI_MENU.length) {
      Grafo grafo = gestoreXML.leggiXML();

      for (Nodo nodo : grafo.getNodi())
        System.out.println(nodo);
    }
  }
}
