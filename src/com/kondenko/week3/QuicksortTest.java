package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuicksortTest {

    @Test
    public void sort() {
        Integer[] actual = {4, 3, 2, 1, 0};
        Integer[] expected = {0, 1, 2, 3, 4};
        Quicksort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortLargeArray() {
        int size = 1_000_000;
        Integer[] actual = new Integer[size];
        Integer[] expected = new Integer[size];
        for (int i = 0; i < size; i++) {
            actual[i] = size - 1 - i;
            expected[i] = i;
        }
        Quicksort.sort(actual);
        assertArrayEquals(expected, actual);
    }

}