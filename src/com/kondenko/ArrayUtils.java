package com.kondenko;

import com.kondenko.week4.quiz.PriorityQueue;

import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;

public class ArrayUtils {

    public static <T extends Comparable<T>> boolean isSorted(T[] array, boolean ascending) {
        return isSorted(array, 0, array.length, Comparator.naturalOrder(), ascending);
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array, int lo, int hi) {
        return isSorted(array, lo, hi, Comparator.naturalOrder(), true);
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array, int lo, int hi, boolean ascending) {
        return isSorted(array, lo, hi, Comparator.naturalOrder(), ascending);
    }

    public static <T> boolean isSorted(T[] array, int lo, int hi, Comparator<T> comparator) {
        return isSorted(array, lo, hi, comparator, true);
    }

    public static <T> boolean isSorted(T[] array, int lo, int hi, Comparator<T> comparator, boolean ascending) {
        if (array.length <= 1) return true;
        for (int i = lo + 1; i < hi; i++) {
            int comparison = comparator.compare(array[i], array[i - 1]);
            if (ascending && comparison < 0) return false;
            else if (!ascending && comparison > 0) return false;
        }
        return true;
    }

    public static <T> boolean contains(T[] array, T item) {
        for (T t : array) if (t.equals(item)) return true;
        return false;
    }

    public static int[][] copyOf(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, copy[i], 0, array.length);
        }
        return copy;
    }

    public static <T> T[] toArray(Stack<T> stack, T[] array) {
        Iterator<T> iter = stack.iterator();
        for (int i = 0; i < stack.size() && iter.hasNext(); i++) {
            array[i] = iter.next();
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] toArray(PriorityQueue<T> pq, T[] array) {
        Iterator<T> iter = pq.iterator();
        for (int i = 0; i < pq.size() && iter.hasNext(); i++) {
            array[i] = iter.next();
        }
        return array;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[][] array, int x_from, int y_from, int x_to, int y_to) {
        int temp = array[x_from][y_from];
        array[x_from][y_from] = array[x_to][y_to];
        array[x_to][y_to] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

}
