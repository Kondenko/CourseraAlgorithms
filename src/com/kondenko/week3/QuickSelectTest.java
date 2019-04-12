package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSelectTest {

    @Test
    public void select() {
        Integer[] array = {1, 2, 3, 4, 5};
        int k = 2;
        assertEquals(4, (int) QuickSelect.select(array, k));
    }

    @Test
    public void select2() {
        Integer[] array = {1, 2, 3, 4, 5};
        int k = 5;
        assertEquals(1, (int) QuickSelect.select(array, k));
    }

    @Test
    public void select3() {
        Integer[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int k = 5;
        assertEquals(6, (int) QuickSelect.select(array, k));
    }

    @Test
    public void selectFromLargeArray() {
        int size = 1_000_000;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        int k = 1;
        assertEquals(size, (int) QuickSelect.select(array, k));
    }

}