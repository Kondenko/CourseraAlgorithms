package com.kondenko.week4.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryHeapTest {

    @Test
    public void min() {
        BinaryHeap<Integer> bh = BinaryHeap.min(7, 8, 10);
        assertEquals(7, (int) bh.root());
    }

    @Test
    public void min1() {
        BinaryHeap<Integer> bh = BinaryHeap.min(7, 8, 10);
        bh.removeRoot();
        assertEquals(8, (int) bh.root());
    }

}