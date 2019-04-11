package com.kondenko.week3;

import edu.princeton.cs.algs4.StdRandom;

import static com.kondenko.ArrayUtils.gt;
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
        return partition(array, left, right, median(array, left, right));
    }

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right, int pivot) {
        while (left < right) {
            if (gt(array, right, pivot)) {
                right--;
            } else {
                swap(array, left, right);
            }
            if (lt(array, left, pivot)) left++;
        }
        swap(array, right, pivot);
        return pivot;
    }

    private static <T extends Comparable<T>> int median(T[] array, int left, int right) {
        int middle = (left + right) / 2;
        if (lt(array, middle, left)) swap(array, left, middle);
        if (lt(array, right, left)) swap(array, left, right);
        if (lt(array, middle, right)) swap(array, middle, right);
        return right;
    }

}
