package com.kondenko.week3.quiz;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;

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
            int pivot = Partition.partition(array, left, right, comparator);
            sort(array, left, pivot - 1, comparator);
            sort(array, pivot + 1, right, comparator);
        }
    }

    static <T extends Comparable<T>> void sortFewUniques(T[] array) {
        sortFewUniques(array, Comparable::compareTo);
    }

    static <T extends Comparable<T>> void sortFewUniques(T[] array, Comparator<T> comparator) {
        sortFewUniques(array, 0, array.length - 1, comparator);
    }

    private static <T extends Comparable<T>> void sortFewUniques(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int pivot = Partition.threeWayPartition(array, left, right, comparator);
            sort(array, left, pivot - 1, comparator);
            sort(array, pivot + 1, right, comparator);
        }
    }

}
