package com.kondenko.week3;

import java.util.Comparator;

import static com.kondenko.ArrayUtils.swap;

public class Partition {

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        return partition(array, left, right, Comparable::compareTo);
    }

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right, Comparator<T> comparator) {
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
