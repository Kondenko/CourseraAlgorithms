package com.kondenko.week6.quiz;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FourSumTest {

    @Test
    public void hasFourSum() {
        assertTrue(FourSum.hasFourSum(new int[]{2, 3, 1, 4}));
    }

    @Test
    public void doesntHaveFourSum() {
        assertFalse(FourSum.hasFourSum(new int[]{0, 10, 100, 1}));
    }

}