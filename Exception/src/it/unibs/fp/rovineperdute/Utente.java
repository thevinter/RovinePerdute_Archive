package it.unibs.fp.rovineperdute;

import it.unibs.fp.mylib.MyMenu;

/**
 * Classe Utente per scelta mappa.
 */
public class Utente {

    private static final MyMenu menu_file = new MyMenu(Costante.titolo_menu_file, Costante.voci_menu_file);

    /**
     * Scegli file da calcolare.
     *
     * @return scelta utente
     */
    public static int scegliFile() {

        return menu_file.scegli();
    }
}