package com.kondenko.week3;


import com.kondenko.Utils;

import org.junit.Test;

import static com.kondenko.Utils.println;
import static org.junit.Assert.assertEquals;


public class StackWithMaxTest {

    private static StackWithMax stack = new StackWithMax();

    static {
        stack.push(10);
        stack.push(5);
        stack.push(15);
    }

    @Test
    public void max() {
        assertEquals(15, stack.max());
    }

    @Test
    public void maxAfterPoppingMax() {
        stack.pop();
        assertEquals(10, stack.max());
    }

    @Test
    public void maxInLargeArray() {
        stack = new StackWithMax();
        int from = 100000;
        for (int i = 0; i <= from; i++) {
            stack.push(i);
        }
        assertEquals(from, stack.max());
        long popTime = Utils.measureTime(() -> {
            for (int i = 0; i <= from; i++) {
                stack.pop();
            }
        });
        println("Popping %d items took %d ms", from - stack.size(), popTime);
    }

}