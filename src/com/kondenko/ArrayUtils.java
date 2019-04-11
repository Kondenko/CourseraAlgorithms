package com.kondenko;

public class ArrayUtils {

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

    public static <T extends Comparable<T>> boolean lt(T[] array, int a, int b) {
        return lt(array[a], array[b]);
    }

    public static <T extends Comparable<T>> boolean gt(T[] array, int a, int b) {
        return gt(array[a], array[b]);
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

}
