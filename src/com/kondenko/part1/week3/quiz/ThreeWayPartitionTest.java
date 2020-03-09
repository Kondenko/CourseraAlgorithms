package com.kondenko.part1.week3.quiz;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ThreeWayPartitionTest {

    @Test
    public void threeWayPartition() {
        Integer[] actual = {4, 2, 3, 4, 3, 3};
        Integer[] expected = {2, 3, 3, 3, 4, 4};
        Partition.threeWayPartition(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void threeWayPartition2() {
        Integer[] actual = {1, 2, 1, 1};
        Integer[] expected = {1, 1, 1, 2};
        Partition.threeWayPartition(actual);
        assertArrayEquals(expected, actual);
    }

}
