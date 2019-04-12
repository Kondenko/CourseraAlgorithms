package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectionInTwoSortedArraysTest {

    @Test
    public void select() {
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] b = {6, 7, 8, 9, 10};
        Integer k = 5;
        assertEquals(6, SelectionInTwoSortedArrays.select(a, b, k));
    }

    @Test
    public void select2() {
        Integer[] a = {1, 2, 4, 5};
        Integer[] b = {3, 6};
        Integer k = 4;
        assertEquals(3, SelectionInTwoSortedArrays.select(a, b, k));
    }

    @Test
    public void select3() {
        int n1 = 1_000_000;
        int n2 = 2_000_000;
        Integer[] a = new Integer[n1];
        Integer[] b = new Integer[n2];
        int k = n2 / 2;
        for (int i = 0; i < n2; i++) {
            if (i < n1) a[i] = i + 1;
            b[i] = i + 1;
        }
        assertEquals(n1 + 1, SelectionInTwoSortedArrays.select(a, b, k));
    }

}