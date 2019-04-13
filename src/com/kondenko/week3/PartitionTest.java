package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PartitionTest {

    @Test
    public void partition() {
        Integer[] actual = {4, 5, 3, 1, 2};
        int pivotIndex = Partition.partition(actual, 0, actual.length - 1);
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
    public void partitionLargeArray() {
        int size = 1_000_000;
        Integer[] actual = new Integer[size];
        for (int i = 0; i < size; i++) {
            actual[i] = size - 1 - i;
        }
        int pivotIndex = Partition.partition(actual, 0, size - 1);
        int pivotItem = actual[pivotIndex];
        for (int i = 0; i < actual.length; i++) {
            if (i < pivotIndex) {
                assertTrue(actual[i] <= pivotItem);
            } else if (i > pivotIndex) {
                assertTrue(actual[i] >= pivotItem);
            }
        }
    }

}