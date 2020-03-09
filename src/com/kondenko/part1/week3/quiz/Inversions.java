package com.kondenko.part1.week3.quiz;

import java.util.Comparator;

import static com.kondenko.CompareUtils.gt;

public class Inversions {

    public static <T extends Comparable<T>> int count(T[] a) {
        return count(a, Comparable::compareTo);
    }

    public static int count(int[] a) {
        Integer[] copy = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            copy[i] = a[i];
        }
        return count(copy, Comparable::compareTo);
    }

    public static <T extends Comparable<T>> int count(T[] a, Comparator<T> comparator) {
        return countInversions(a, comparator);
    }

    private static <T extends Comparable<T>> int countInversions(T[] arr, Comparator<T> comparator) {
        @SuppressWarnings("unchecked")
        int inversionsCount = 0;
        for (int size = 1; size < arr.length; size *= 2) {
            for (int lo = 0; lo < arr.length - size; lo += size * 2) {
                int mid = lo + size - 1;
                int hi = Math.min(lo + size * 2 - 1, arr.length - 1);
                inversionsCount += merge(arr, lo, mid, hi, comparator);
            }
        }
        return inversionsCount;
    }

    protected static <T extends Comparable<T>> int merge(T[] arr, final int lo, int mid, final int hi, Comparator<T> comparator) {
        int inversionsCount = 0;
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k < hi; k++) {
            if (i > mid) {
                j++;
            } else if (j > hi) {
                if (j == hi + 1) {
                    j = mid + 1;
                    k = lo;
                }
                i++;
            } else {
                if (isInversion(arr, i, j)) inversionsCount++;
                j++;
            }
        }
        return inversionsCount;
    }

    private static <T extends Comparable<T>> boolean isInversion(T[] a, int i, int j) {
        return i < j && gt(a, i, j);
    }

}
