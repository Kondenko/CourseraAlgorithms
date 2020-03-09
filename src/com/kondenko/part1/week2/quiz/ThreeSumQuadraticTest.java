package com.kondenko.part1.week2.quiz;


import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class ThreeSumQuadraticTest {


    @Test
    public void threeSum1() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4}; // [-4, -1, -1, 0, 1, 2]
        List<List<Integer>> expected = List.of(List.of(-1, 0, 1), List.of(-1, -1, 2));
        List<List<Integer>> actual = ThreeSumQuadratic.threeSum(nums);
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void threeSum2() {
        int[] nums = new int[]{0, 0, 0, 0};
        List<List<Integer>> expected = List.of(List.of(0, 0, 0));
        List<List<Integer>> actual = ThreeSumQuadratic.threeSum(nums);
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void threeSum3() {
        int[] nums = new int[]{-2, 0, 1, 1, 2};
        List<List<Integer>> expected = List.of(List.of(-2, 0, 2), List.of(-2, 1, 1));
        List<List<Integer>> actual = ThreeSumQuadratic.threeSum(nums);
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }


}