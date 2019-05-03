package com.kondenko.week3.quiz;

import com.kondenko.Utils;

import org.junit.Test;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

import static org.junit.Assert.assertArrayEquals;

public class MergesortTest {

    @Test
    public void sort() {
        Integer[] actual = {9, 7, 5, 3, 1, 8, 6, 4, 2};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Mergesort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortLargeArray() {
        int size = 100_000;
        Integer[] actual = new Integer[size];
        Integer[] expected = new Integer[size];
        for (int i = 0; i < size; i++) {
            expected[i] = i;
            actual[i] = size - i - 1;
        }
        long time = Utils.measureTime(() -> Mergesort.sort(actual));
        assertArrayEquals(expected, actual);
        StdOut.println(String.format("Sorted %d items in %d ms", size, time));
    }

    @Test
    public void merge1() {
        Integer[] arr = {4, 5, 6, 1, 2, 3};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int hi = arr.length - 1;
        int mid = mid(lo, hi);
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge2() {
        Integer[] arr = {1, 3, 5, 2, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int hi = arr.length - 1;
        int mid = mid(lo, hi);
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge3() {
        Integer[] arr = {1, 3, 2, 5, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int hi = 3;
        int mid = mid(lo, hi);
        Integer[] expected = {1, 2, 3, 5, 4, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge4() {
        Integer[] arr = {1, 3, 2, 5, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int hi = 1;
        int mid = mid(lo, hi);
        Integer[] expected = {1, 3, 2, 5, 4, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge5() {
        Integer[] arr = {1, 3, 2, 5, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 4;
        int hi = arr.length - 1;
        Integer[] expected = {1, 3, 2, 5, 4, 6};
        int mid = mid(lo, hi);
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge6() {
        Integer[] arr = {1, 3, 2};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int hi = arr.length - 1;
        Integer[] expected = {1, 2, 3};
        int mid = mid(lo, hi);
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    private int mid(int lo, int hi) {
        return (lo + hi) / 2;
    }

}