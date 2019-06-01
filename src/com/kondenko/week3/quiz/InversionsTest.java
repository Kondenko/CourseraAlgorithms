package com.kondenko.week3.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InversionsTest {

    @Test
    public void count1() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(0, Inversions.count(array));
    }

    @Test
    public void count2() {
        int[] array = {1, 2, 3, 5, 4};
        assertEquals(1, Inversions.count(array));
    }

    @Test
    public void count3() {
        int[] array = {5, 1, 2, 3, 4};
        assertEquals(4, Inversions.count(array));
    }

    @Test
    public void countLargeArray() {
        int size = 100000;
        int[] array = new int[size];
        array[0] = size;
        for (int i = 1; i < array.length; i++) {
            array[i] = i;
        }
        assertEquals(size - 1, Inversions.count(array));
    }

}