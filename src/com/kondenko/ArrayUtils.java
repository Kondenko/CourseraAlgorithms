package com.kondenko;

import com.kondenko.week4.quiz.PriorityQueue;

import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;

public class ArrayUtils {

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

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

    public static <T extends Comparable<T>> T min(T a, T b) {
        if (lt(a, b)) return a;
        else return b;
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        if (gt(a, b)) return a;
        else return b;
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
