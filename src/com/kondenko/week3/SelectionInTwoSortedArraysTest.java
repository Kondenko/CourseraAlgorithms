package com.kondenko.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectionInTwoSortedArraysTest {

    @Test
    public void select() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {6, 7, 8, 9, 10};
        int k = 5;
        assertEquals(6, SelectionInTwoSortedArrays.select(a, b, k));
    }

    @Test
    public void select2() {
        int[] a = {1, 2, 4, 5};
        int[] b = {3, 6};
        int k = 4;
        assertEquals(3, SelectionInTwoSortedArrays.select(a, b, k));
    }

}