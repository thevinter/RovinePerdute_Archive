package it.unibs.fp.mylib;

import java.util.concurrent.TimeUnit;

public class MyTime {

    public static void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}