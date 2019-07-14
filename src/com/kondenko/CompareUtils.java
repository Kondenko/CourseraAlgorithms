package com.kondenko;

import java.util.Comparator;

public class CompareUtils {

    public static <T extends Comparable<T>> T min(T a, T b) {
        if (lt(a, b)) return a;
        else return b;
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        if (gt(a, b)) return a;
        else return b;
    }

    public static <T extends Comparable<T>> boolean lt(T[] array, int a, int b) {
        return lt(array, a, b, Comparable::compareTo);
    }

    public static <T> boolean lt(T[] array, int a, int b, Comparator<T> comparator) {
        return compare(array, a, b, comparator) < 0;
    }

    public static <T extends Comparable<T>> boolean gt(T[] array, int a, int b) {
        return gt(array, a, b, Comparable::compareTo);
    }

    public static <T> boolean gt(T[] array, int a, int b, Comparator<T> comparator) {
        return compare(array, a, b, comparator) > 0;
    }

    public static <T extends Comparable<T>> boolean gt(T a, T b, Comparator<T> comparator) {
        return comparator.compare(a, b) > 0;
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b, Comparator<T> comparator) {
        return comparator.compare(a, b) < 0;
    }

    public static <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T> int compare(T[] array, int a, int b, Comparator<T> comparator) {
        return comparator.compare(array[a], array[b]);
    }

}
