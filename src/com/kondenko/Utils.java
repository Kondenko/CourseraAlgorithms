package com.kondenko;

public class Utils {

    public long measureTime(Runnable algorithm) {
        long start = System.currentTimeMillis();
        algorithm.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public int[] randomIntArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
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
