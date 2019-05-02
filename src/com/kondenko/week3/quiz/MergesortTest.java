package com.kondenko.week3.quiz;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class MergesortTest {

    @Test
    public void merge() {
        Integer[] arr = {4, 5, 6, 1, 2, 3};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int mid = 3;
        int hi = arr.length;
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge2() {
        Integer[] arr = {1, 3, 5, 2, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int mid = 3;
        int hi = arr.length;
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test
    public void merge3() {
        Integer[] arr = {1, 3, 2, 5, 4, 6};
        Integer[] aux = new Integer[arr.length];
        int lo = 0;
        int mid = 2;
        int hi = 4;
        Integer[] expected = {1, 2, 3, 5, 4, 6};
        Mergesort.merge(arr, aux, lo, mid, hi, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

}