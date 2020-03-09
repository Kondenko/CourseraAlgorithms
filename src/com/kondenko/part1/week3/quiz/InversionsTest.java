package com.kondenko.part1.week3.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InversionsTest {

    @Test
    public void count0() {
        int[] array = {8, 9, 1, 2};
        assertEquals(4, Inversions.count(array));
    }

    @Test
    public void count1() {
        int[] array = {4, 1, 2, 3};
        assertEquals(3, Inversions.count(array));
    }

    @Test
    public void count2() {
        int[] array = {8, 1, 2, 3, 4, 5, 6, 7};
        assertEquals(7, Inversions.count(array));
    }

    @Test
    public void count3() {
        int[] array = {8, 9, 1, 2, 10, 12, 3, 4};
        assertEquals(12, Inversions.count(array));
    }

    @Test
    public void count4() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(0, Inversions.count(array));
    }

    @Test
    public void count5() {
        int[] array = {1, 2, 3, 5, 4};
        assertEquals(1, Inversions.count(array));
    }

    @Test
    public void count6() {
        int[] array = {5, 1, 2, 3, 4};
        assertEquals(4, Inversions.count(array));
    }

    @Test
    public void countLargeArray() {
        int size = 100000;
        Integer[] array = new Integer[size];
        array[0] = size;
        for (int i = 1; i < array.length; i++) {
            array[i] = i;
        }
        assertEquals(size - 1, Inversions.count(array));
    }

}