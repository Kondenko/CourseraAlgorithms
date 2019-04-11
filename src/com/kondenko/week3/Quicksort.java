package com.kondenko.week3;

import edu.princeton.cs.algs4.StdRandom;

import static com.kondenko.ArrayUtils.lt;
import static com.kondenko.ArrayUtils.swap;

public class Quicksort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            sort(array, left, pivot - 1);
            sort(array, pivot + 1, right);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int i = left;
        int j = right + 1;
        while(true) {
            while(lt(array, ++i, left)) if (i == right) break;
            while (lt(array, left, --j)) if (j == left) break;
            if (i >= j) break;
            swap(array, i, j);
        }
        swap(array, left, j);
        return j;
    }

}
