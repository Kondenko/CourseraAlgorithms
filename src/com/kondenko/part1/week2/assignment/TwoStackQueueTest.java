package com.kondenko.part1.week2.assignment;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TwoStackQueueTest {

    @Test
    public void testQueue() {
        // Multiple assertions in the same test is a bad practice. Don't do this, kids.
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        TwoStackQueue<Integer> queue = new TwoStackQueue<>();

        assertTrue(queue.isEmpty());

        for (int item : items) {
            queue.enqueue(item);
        }

        assertFalse(queue.isEmpty());
        assertEquals(items.size(), queue.size());

        List<Integer> dequeuedItems = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            dequeuedItems.add(queue.dequeue());
        }

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        assertEquals(dequeuedItems, items);
    }

}