package com.kondenko.part1.week3.quiz;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void merge3() {
        Integer[] actual = {2, 1};
        Integer[] expected = {1, 2};
        MergingWithSmallerArray.merge(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void merge4() {
        Integer[] actual = {1};
        Integer[] expected = {1};
        MergingWithSmallerArray.merge(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void merge5() {
        int size = 100;
        Integer[] actual = new Integer[size];
        Integer[] expected = new Integer[size];
        for (int i = 0; i < size; i++) {
            expected[i] = i;
            actual[i] = (i < size / 2) ? i + size / 2 : i - size / 2;
        }
        MergingWithSmallerArray.merge(actual);
        assertArrayEquals(expected, actual);
    }

}