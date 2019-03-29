package com.kondenko;


import java.util.Random;

public class Utils {

    public static long measureTime(Runnable algorithm) {
        long start = System.currentTimeMillis();
        algorithm.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static int randomIntArray(int[] into) {
        Random rnd = new Random();
        int item = rnd.nextInt();
        int max = item;
        for (int i = 0; i < into.length; i++) {
            into[i] = item;
            if (item > max) {
                max = item;
            }
            item = rnd.nextInt();
        }
        return max;
    }

    public static void println(String s, Object... args) {
        System.out.println(String.format(s, args));
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void println(int s) {
        System.out.println(s);
    }

}