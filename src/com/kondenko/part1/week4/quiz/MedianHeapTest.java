package com.kondenko.part1.week4.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianHeapTest {

    @Test
    public void findMedian() {
        MedianHeap<Integer> mh = new MedianHeap<>();
        mh.insert(1);
        mh.insert(5);
        mh.insert(10);
        assertEquals(5, (int) mh.findMedian());
    }

    @Test
    public void findMedian1() {
        MedianHeap<Integer> mh = new MedianHeap<>();
        mh.insert(1);
        mh.insert(5);
        mh.insert(10);
        mh.removeMedian();
        assertEquals(1, (int) mh.findMedian());
    }

    @Test
    public void findMedian2() {
        MedianHeap<Integer> mh = new MedianHeap<>();
        mh.insert(1);
        mh.insert(5);
        mh.insert(7);
        mh.insert(8);
        mh.insert(10);
        assertEquals(7, (int) mh.findMedian());
    }

    @Test
    public void findMedian3() {
        MedianHeap<Integer> mh = new MedianHeap<>();
        mh.insert(1);
        mh.insert(5);
        mh.insert(6);
        mh.insert(7);
        mh.insert(8);
        mh.insert(10);
        assertEquals(6, (int) mh.findMedian());
    }


}