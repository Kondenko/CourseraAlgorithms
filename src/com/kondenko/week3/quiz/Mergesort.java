package com.kondenko.week3.quiz;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.ArrayUtils.isSorted;
import static com.kondenko.CompareUtils.lt;

public class Mergesort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        // sortRecursive(array, 0, array.length - 1, comparator);
        sortBottomUp(array, comparator);
    }

    private static <T> void sortBottomUp(T[] arr, Comparator<T> comparator) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Object[arr.length];
        for (int size = 1; size < arr.length; size *= 2) {
            for (int lo = 0; lo < arr.length - size; lo += size * 2) {
                int mid = lo + size - 1;
                int hi = Math.min(lo + size * 2 - 1, arr.length - 1);
                merge(arr, aux, lo, mid, hi, comparator);
            }
        }
    }

    private static <T> void sortRecursive(T[] arr, final int lo, final int hi, Comparator<T> comparator) {
        if (lo < hi) {
            @SuppressWarnings("unchecked")
            T[] aux = (T[]) new Object[arr.length];
            int mid = (hi + lo) / 2;
            sortRecursive(arr, lo, mid, comparator);
            sortRecursive(arr, mid + 1, hi, comparator);
            if (lo == hi - 1) StdOut.println("Merging into final array");
            merge(arr, aux, lo, mid, hi, comparator);
        }
    }

    protected static <T> void merge(T[] arr, T[] aux, final int lo, int mid, final int hi, Comparator<T> comparator) {
        assert isSorted(arr, lo, mid, comparator);
        assert isSorted(arr, mid + 1, hi, comparator);
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (lt(aux, j, i, comparator)) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
        assert isSorted(arr, lo, hi, comparator);
    }

}
