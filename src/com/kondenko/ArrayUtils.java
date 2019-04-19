package com.kondenko;

import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;

public class ArrayUtils {

    public static <T> T[] toArray(Stack<T> stack) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[stack.size()];
        Iterator<T> iter = stack.iterator();
        for (int i = 0; i < stack.size() && iter.hasNext(); i++) {
            array[i] = iter.next();
        }
        return array;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

    public static <T extends Comparable<T>> Comparator<T> reverseComparator() {
        return (a, b) -> {
            int comp = a.compareTo(b);
            if (comp < 0) return 1;
            else if (comp > 0) return -1;
            return 0;
        };
    }

    public static <T extends Comparable<T>> boolean lt(T[] array, int a, int b) {
        return lt(array[a], array[b]);
    }

    public static <T extends Comparable<T>> boolean gt(T[] array, int a, int b) {
        return gt(array[a], array[b]);
    }

    public static <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<T>> boolean ltOrEq(T a, T b) {
        return a.compareTo(b) <= 0;
    }

}
