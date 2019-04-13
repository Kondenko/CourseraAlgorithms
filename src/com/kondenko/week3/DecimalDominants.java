package com.kondenko.week3;

import edu.princeton.cs.algs4.Stack;

public class DecimalDominants {

    public static  <T extends Comparable<T>> Iterable<T> find(T[] array, int n) {
        Quicksort.sortFewUniques(array);
        Stack<T> dominants = new Stack<>();
        int m = n / 10;
        int latestItemOccurrences = 0;
        T prev = null;
        for (T item : array) {
            if (item.equals(prev) || prev == null) {
                latestItemOccurrences++;
                if (latestItemOccurrences > m && (dominants.isEmpty() || dominants.peek() != item)) {
                    dominants.push(item);
                }
            } else {
                latestItemOccurrences = 0;
            }
            prev = item;
        }
        return dominants;
    }

}
