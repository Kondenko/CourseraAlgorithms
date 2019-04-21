package com.kondenko;

import com.kondenko.week4.quiz.PriorityQueue;

import org.junit.Test;

import edu.princeton.cs.algs4.Stack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayUtilsTest {

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


}