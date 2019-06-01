package com.kondenko.week3.quiz;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.CompareUtils.gt;

public class Inversions {

    // 5, 20, 10, 35, 30, 60, 70, 80 | 2/2

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
        T[] copy = Arrays.copyOf(a, a.length);
        return countInversions(copy, comparator);
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
        StdOut.printf("lo = %d, hi = %d\n", lo, hi);
        int inversionsCount = 0;
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k < hi; k++) {
            int m;
            if (i > mid) m = j++;
            else if (j > hi) m = i++;
            if (isInversion(arr, i, j)) {
                m = j++;
                inversionsCount++;
            } else {
                m = i++;
            }
        }
        return inversionsCount;
    }

    private static <T extends Comparable<T>> boolean isInversion(T[] a, int i, int j) {
        StdOut.println(Arrays.toString(a));
        try {
            boolean isInversion = i < j && gt(a, i, j);
            StdOut.printf("%d > %d = %b\n", a[i], a[j], isInversion);
            return isInversion;
        } catch (ArrayIndexOutOfBoundsException e) {
            StdOut.printf("%d or %d is out of bounds\n", i, j);
            return false;
        }
    }

}
