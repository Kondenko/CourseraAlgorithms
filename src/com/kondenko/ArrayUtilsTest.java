package com.kondenko;

import org.junit.Test;

import edu.princeton.cs.algs4.Stack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayUtilsTest {

    @Test
    public void toArray() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer[] expected = {3, 2, 1};
        assertArrayEquals(expected, ArrayUtils.toArray(stack));
        assertFalse(stack.isEmpty());
    }

    @Test
    public void toArray2() {
        Stack<Integer> stack = new Stack<>();
        Integer[] expected = {};
        assertArrayEquals(expected, ArrayUtils.toArray(stack));
        assertTrue(stack.isEmpty());
    }

}