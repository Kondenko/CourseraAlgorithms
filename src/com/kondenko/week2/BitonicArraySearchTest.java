package com.kondenko.week2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BitonicArraySearchTest {

    @Test
    public void shouldFind2() {
        int[] arr = new int[]{1, 4, 5, 10, 16, 3, 2, 0};
        assertTrue(BitonicArraySearch.find(arr, 2));
    }

    @Test
    public void shouldNotFind3() {
        int[] arr = new int[]{1, 4, 5, 2};
        assertFalse(BitonicArraySearch.find(arr, 3));
    }

}