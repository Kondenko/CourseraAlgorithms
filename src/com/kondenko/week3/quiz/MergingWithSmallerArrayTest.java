package com.kondenko.week3.quiz;

import org.junit.Test;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

import static org.junit.Assert.assertArrayEquals;

public class MergingWithSmallerArrayTest {

    @Test
    public void shrink() {
        Integer[] actual = {null, 1, null, 2, null};
        Integer[] expected = {null, null, null, 1, 2};
        StdOut.println("Initial: " + Arrays.deepToString(actual));
        MergingWithSmallerArray.shrink(actual, 0, 0);
        StdOut.println("Actual: " + Arrays.deepToString(actual));
        assertArrayEquals(expected, actual);
    }

    @Test
    public void merge1() {
        Integer[] actual = {4, 5, 6, 1, 2, 3};
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        MergingWithSmallerArray.merge(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void merge2() {
        Integer[] actual = {1, 3, 5, 2, 4, 6};
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        MergingWithSmallerArray.merge(actual);
        assertArrayEquals(expected, actual);
    }

}