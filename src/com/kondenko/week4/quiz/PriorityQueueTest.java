package com.kondenko.week4.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}