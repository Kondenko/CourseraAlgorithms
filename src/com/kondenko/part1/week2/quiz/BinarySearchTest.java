package com.kondenko.part1.week2.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {

    private Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    @Test
    public void shouldNotFind() {
        assertEquals(-1, BinarySearch.search(100, arr));
    }

    @Test
    public void shouldFindFirstItem() {
        assertEquals(0, BinarySearch.search(0, arr));
    }

    @Test
    public void shouldFindLastItem() {
        assertEquals(8, BinarySearch.search(8, arr));
    }

    @Test
    public void shouldFind2() {
        assertEquals(2, BinarySearch.search(2, arr));
    }

    @Test
    public void shouldFind7() {
        assertEquals(7, BinarySearch.search(7, arr));
    }

    @Test
    public void shouldFindInLargeArray() {
        int length = 1_000_000;
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        int itemToFind = 888888;
        assertEquals(itemToFind, BinarySearch.search(itemToFind, arr));
    }

    @Test
    public void shouldFindInArrayOfPrimitives() {
        int length = 1_000_000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        int itemToFind = 888888;
        assertEquals(itemToFind, BinarySearch.search(itemToFind, arr));
    }

}