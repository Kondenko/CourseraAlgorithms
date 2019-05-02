package com.kondenko.week3.quiz;

import java.util.Comparator;

import static com.kondenko.CompareUtils.lt;

public class Mergesort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Object[array.length];
        sort(array, aux, 0, array.length - 1, comparator);
    }

    private static <T> void sort(T[] arr, T[] aux, final int lo, final int hi, Comparator<T> comparator) {
        if (lo < hi) {
            int mid = (hi + lo) / 2;
            sort(arr, aux, lo, mid, comparator);
            sort(arr, aux, mid + 1, hi, comparator);
            merge(arr, aux, lo, mid, hi, comparator);
        }
    }

    protected static <T> void merge(T[] arr, T[] aux, final int lo, final int mid, final int hi, Comparator<T> comparator) {
        System.arraycopy(arr, lo, aux, lo, Math.min(hi - lo + 1, arr.length));
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
