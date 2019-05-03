package com.kondenko.week3.quiz;

import java.util.Comparator;

import static com.kondenko.ArrayUtils.isSorted;
import static com.kondenko.CompareUtils.lt;

public class Mergesort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        sort(array, 0, array.length - 1, comparator);
    }

    private static <T> void sort(T[] arr, final int lo, final int hi, Comparator<T> comparator) {
        if (lo < hi) {
            @SuppressWarnings("unchecked")
            T[] aux = (T[]) new Object[arr.length];
            int mid = (hi + lo) / 2;
            sort(arr, lo, mid, comparator);
            sort(arr, mid + 1, hi, comparator);
            merge(arr, aux, lo, mid, hi, comparator);
        }
    }

    protected static <T> void merge(T[] arr, T[] aux, final int lo, int mid, final int hi, Comparator<T> comparator) {
        assert isSorted(arr, lo, mid, comparator);
        assert isSorted(arr, mid + 1, hi, comparator);
        mid = Math.max(1, mid);
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = mid;
        for (int k = lo; k <= hi; k++) {
            if (i >= mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (lt(aux, i, j, comparator)) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
        assert isSorted(arr, lo, hi, comparator);
    }

}
