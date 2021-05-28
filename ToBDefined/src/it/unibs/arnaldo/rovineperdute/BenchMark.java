package it.unibs.arnaldo.rovineperdute;

/***
 * Class to output benchmarks
 * @author ToBdefined
 */
public class BenchMark {
    public static final String TIME = "\nIl programma è stato eseguito in %d ms\n";
    public static final String SPACE = "Sono stati usati %.2f kb di memoria\n\n";

    private static long startTime;

    /***
     * Method to store the initial time
     */
    public static void start(){
        startTime = System.currentTimeMillis();
    }


    /***
     * Method to output time and memory used at the end of the program
     */
    public static void end(){
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.printf(TIME, elapsedTime);

        //Per la memoria usata, fa partire il garbage collector
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf(SPACE, (float) memory/1000);
    }
}
