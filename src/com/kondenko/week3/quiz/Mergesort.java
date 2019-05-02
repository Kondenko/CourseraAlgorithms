package com.kondenko.week3.quiz;

import java.util.Comparator;

import static com.kondenko.CompareUtils.lt;

public class Mergesort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        sort(array, 0, array.length - 1, comparator);
    }

    private static <T> void sort(T[] arr, int lo, int hi, Comparator<T> comparator) {

    }

    protected static <T> void merge(T[] arr, T[] aux, final int lo, final int mid, final int hi, Comparator<T> comparator) {
        if (hi - lo >= 0) System.arraycopy(arr, lo, aux, lo, hi - lo);
        int i = lo;
        int j = mid;
        for (int k = lo; k < hi; k++) {
            if (i >= mid) arr[k] = aux[j++];
            else if (j >= arr.length) arr[k] = aux[i++];
            else if (lt(aux, i, j, comparator)) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }

}
