package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuicksortTest {

    @Test
    public void partition() {
        int pivotIndex = 2;
        int pivotItem = 3;
        Integer[] actual = {4, 5, 0, 1, 2};
        actual[pivotIndex] = pivotItem;
        int pivotActual = Quicksort.partition(actual, 0, actual.length - 1, pivotIndex);
        assertEquals(pivotIndex, pivotActual);
        assertEquals(pivotItem, (int) actual[pivotIndex]);
        for (int i = 0; i < actual.length; i++) {
            if (i < pivotIndex) {
                assertTrue(actual[i] <= pivotItem);
            } else if (i > pivotIndex) {
                assertTrue(actual[i] >= pivotItem);
            }
        }
    }

    @Test
    public void sort() {
        Integer[] actual = {4, 3, 2, 1, 0};
        Integer[] expected = {0, 1, 2, 3, 4};
        Quicksort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void partionLargeArray() {
        int size = 1_000_000;
        Integer[] actual = new Integer[size];
        for (int i = 0; i < size; i++) {
            actual[i] = size - 1 - i;
        }
        int pivotIndex = Quicksort.partition(actual, 0, size -1);
        int pivotItem = actual[pivotIndex];
        for (int i = 0; i < actual.length; i++) {
            if (i < pivotIndex) {
                assertTrue(actual[i] <= pivotItem);
            } else if (i > pivotIndex) {
                assertTrue(actual[i] >= pivotItem);
            }
        }
    }

    @Test
    public void sortLargeArray() {
        int size = 10000;
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