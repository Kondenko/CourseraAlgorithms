package com.kondenko.week3.quiz;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.StdOut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.not;


public class LinkedListShuffleTest {

    @Test
    public void shuffle() {
        java.util.LinkedList<Integer> list = new java.util.LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Integer[] original = new Integer[list.size()];
        list.toArray(original);
        Integer[] actual = shuffle(list);
        assertThat(original, arrayContainingInAnyOrder(actual));
        assertThat(original, not(arrayContaining(actual)));
    }

    @Test
    public void shuffleRepeatedly() {
        java.util.LinkedList<Integer> list = new java.util.LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int shufflesCount = 10;
        for (int i = 0; i < shufflesCount; i++) {
            StdOut.println(Arrays.deepToString(shuffle(list)));
        }
    }

    private Integer[] shuffle(LinkedList<Integer> list) {
        LinkedListShuffle.shuffle(list);
        Integer[] actual = new Integer[list.size()];
        list.toArray(actual);
        return actual;
    }

}