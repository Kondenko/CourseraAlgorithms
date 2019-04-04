package com.kondenko.week2.assignment;


import com.kondenko.Utils;

import org.junit.Test;

import static com.kondenko.Utils.println;
import static org.junit.Assert.assertEquals;


public class StackWithMaxTest {


    @Test
    public void max() {
        StackWithMax stack = new StackWithMax();
        stack.push(10);
        stack.push(5);
        stack.push(15);
        assertEquals(15, stack.max());
    }

    @Test
    public void maxAfterPoppingMax() {
        StackWithMax stack = new StackWithMax();
        stack.push(10);
        stack.push(1);
        stack.push(5);
        stack.push(15);
        stack.pop();
        assertEquals(10, stack.max());
    }

    @Test
    public void maxInLargeArray() {
        StackWithMax stack = new StackWithMax();

        int items = 100_000;
        for (int i = 0; i <= items; i++) {
            stack.push(i);
        }
        assertEquals(items, stack.max());

        long popTime = Utils.measureTime(() -> {
            while (!stack.isEmpty()) {
                stack.pop();
            }
        });
        println("Popping %d items took %d ms with", items, popTime);
    }

}