package it.unibs.fp.mylib;

import java.io.Console;

/**
 * Classe <tt>Util<tt> con metodi utili alla scrittura in {@linkplain Console}
 */

public class OutputArray {

    private static final String PROSSIMO_ELEMENTO = "< Per visualizzare il prossimo elemento, premi invio >";

    /**
     * Impossibile creare un istanza di questa classe
     */
    private OutputArray() {
    }

    /**
     * <b>Metodo statico</b> che permette di mostrare ogni sigolo elemento dell'<tt>array</tt> dopo che si preme invio
     *
     * @param array, e' un array di {@linkplain String}
     */
    public static void arrayStringConsoleConAttesa(String[] array) {
        for (int i = 0; i < array.length; i++) {
            InputDati.isInvioPremuto(array[i], PROSSIMO_ELEMENTO);

        }
    }

    /**
     * <b>Metodo statico</b> che permette di mostrare ogni sigolo elemento dell'<tt>array</tt>
     *
     * @param array, e' un array di {@linkplain String}
     */
    public static void arrayStringConsole(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}