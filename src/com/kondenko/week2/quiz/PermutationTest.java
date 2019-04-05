package com.kondenko.week2.quiz;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PermutationTest {

    @Test
    public void isPermutation() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 1, 3, 2};
        assertTrue(Permutation.isPermutation(a, b));
    }

    @Test
    public void isNotPermutation() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {6, 7, 8, 9, 1};
        assertFalse(Permutation.isPermutation(a, b));
    }

}