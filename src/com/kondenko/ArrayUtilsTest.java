package com.kondenko;

import com.kondenko.week4.quiz.PriorityQueue;

import org.junit.Test;

import edu.princeton.cs.algs4.Stack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ArrayUtilsTest {

    @Test
    public void copyOf() {
        int[][] expected = new int[][]{
            {1, 2},
            {3, 4},
        };
        int[][] actual = ArrayUtils.copyOf(expected);
        assertArrayEquals(expected, actual);
        assertNotEquals(expected, actual);
    }

    @Test
    public void stackToArray() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer[] expected = {3, 2, 1};
        assertArrayEquals(expected, ArrayUtils.toArray(stack, new Integer[stack.size()]));
        assertFalse(stack.isEmpty());
    }

    @Test
    public void stackToArray2() {
        Stack<Integer> stack = new Stack<>();
        Integer[] expected = {};
        assertArrayEquals(expected, ArrayUtils.toArray(stack, new Integer[stack.size()]));
        assertTrue(stack.isEmpty());
    }

    @Test
    public void priorityQueueToArray() {
        Integer[] items = {10, 9, 8, 7, 6, 5};
        PriorityQueue<Integer> bh = PriorityQueue.max(items);
        Integer[] actual = ArrayUtils.toArray(bh, new Integer[bh.size()]);
        assertArrayEquals(items, actual);
    }

    @Test
    public void isSorted() {
        Integer[] arr = { 1, 2, 3, 4, 5};
        assertTrue(ArrayUtils.isSorted(arr, true));
    }

    @Test
    public void isSorted2() {
        Integer[] arr = { 1, 1, 1 };
        assertTrue(ArrayUtils.isSorted(arr, true));
    }

    @Test
    public void isSortedPartial() {
        Integer[] arr = { 5, 4, 1, 2, 3, 0 };
        assertTrue(ArrayUtils.isSorted(arr, 2, 5, true));
    }

    @Test
    public void isNotSorted() {
        Integer[] arr = { 2, 1, 1 };
        assertFalse(ArrayUtils.isSorted(arr, true));
    }

}