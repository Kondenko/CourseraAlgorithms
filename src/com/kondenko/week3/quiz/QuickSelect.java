package com.kondenko.week3.quiz;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSelect {

    public static <T extends Comparable<T>> T select(T[] array, int k) {
        k = array.length - k;
        if (k < 0 || k >= array.length) {
            throw new ArrayIndexOutOfBoundsException(String.format("%d is out of bounds (0, %d)", k, array.length));
        }
        T[] a = Arrays.copyOf(array, array.length);
        StdRandom.shuffle(a);
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int pivot = Partition.partition(a, left, right);
            if (pivot < k) left = pivot + 1;
            else if (pivot > k) right = pivot - 1;
            else return a[k];
        }
        return a[k];
    }


}
