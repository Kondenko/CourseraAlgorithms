package com.kondenko.week4.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PriorityQueueTest {

    @Test
    public void min() {
        PriorityQueue<Integer> bh = PriorityQueue.min(7, 8, 10);
        assertEquals(7, (int) bh.root());
    }

    @Test
    public void min1() {
        PriorityQueue<Integer> bh = PriorityQueue.min(7, 8, 10);
        bh.removeRoot();
        assertEquals(8, (int) bh.root());
    }

    @Test
    public void contains() {
        PriorityQueue<Integer> bh = PriorityQueue.min(7, 8, 10, 150);
        assertTrue(bh.contains(10));
    }

    @Test
    public void doesNotContain() {
        PriorityQueue<Integer> bh = PriorityQueue.min(7, 8, 10, 150);
        assertFalse(bh.contains(1));
    }

}