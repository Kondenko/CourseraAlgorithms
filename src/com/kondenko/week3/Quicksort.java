package com.kondenko.week3;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;

import static com.kondenko.ArrayUtils.swap;

public class Quicksort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    public static <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1, comparator);
    }

    private static <T extends Comparable<T>> void sort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int pivot = partition(array, left, right, comparator);
            sort(array, left, pivot - 1, comparator);
            sort(array, pivot + 1, right, comparator);
        }
    }

    protected static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        return partition(array, left, right, Comparable::compareTo);
    }

    protected static <T extends Comparable<T>> int partition(T[] array, int left, int right, Comparator<T> comparator) {
        int i = left;
        int j = right + 1;
        while (true) {
            while (comparator.compare(array[++i], array[left]) < 0) if (i == right) break;
            while (comparator.compare(array[left], array[--j]) < 0) if (j == left) break;
            if (i >= j) break;
            swap(array, i, j);
        }
        swap(array, left, j);
        return j;
    }

}
