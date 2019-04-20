package com.kondenko.week4.quiz;

import org.junit.Test;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class RandomizedPriorityQueueTest {

    @Test
    public void deleteRandom() {
        Integer[] items = {1, 2, 3, 4, 5};
        Integer[] actual = new Integer[items.length];
        RandomizedPriorityQueue<Integer> pq = new RandomizedPriorityQueue<>();
        pq.add(items);
        for (int i = 0; !pq.isEmpty(); i++) {
            actual[i] = pq.deleteRandom();
        }
        StdOut.println(Arrays.deepToString(actual));
        assertThat(actual, arrayContainingInAnyOrder(items));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpe() {
        RandomizedPriorityQueue<Integer> pq = new RandomizedPriorityQueue<>();
        pq.sample();
    }

    @Test
    public void sample() {
        Integer[] items = {1, 2, 3, 4, 5};
        RandomizedPriorityQueue<Integer> pq = new RandomizedPriorityQueue<>();
        pq.add(items);
        assertThat(pq.sample(), in(items));
    }

}