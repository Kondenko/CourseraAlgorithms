package com.kondenko.week2.quiz;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ShellSortTest {

    @Test
    public void shellSort() {
        Integer[] expected = {1, 2, 3, 4, 5, 10, 40, 50, 60, 70, 80, 100, 1000};
        Integer[] original = {2, 1, 5, 4, 80, 1000, 10, 70, 60, 100, 50, 3, 40};
        ShellSort.sort(original);
        assertArrayEquals(expected, original);
    }
}