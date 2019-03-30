package com.kondenko.week3;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.Utils.measureTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class DequeTest {

    @Test
    public void addFirstAndLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(100);
        assertEquals(1, (int) deque.removeFirst());
        assertEquals(100, (int) deque.removeLast());
    }

    @Test
    public void addFirstRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(1, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        assertEquals(3, (int) deque.removeLast());
    }

    @Test
    public void addLastRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(1, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(3, (int) deque.removeFirst());
    }

    @Test
    public void addFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeFirst());
    }

    @Test
    public void addLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        assertEquals(5, (int) deque.removeLast());
    }

    @Test
    public void capacityTest() {
        Deque<Integer> deque = new Deque<>();
        int itemsCount = 100;
        for (int i = 1; i <= itemsCount; i++) {
            deque.addFirst(i);
        }
        assertFalse(deque.isEmpty());
        assertEquals(itemsCount, deque.size());
    }

    @Test
    public void removalTest() {
        Deque<Integer> deque = new Deque<>();
        int itemsCount = 100;
        for (int i = 1; i <= itemsCount; i++) {
            deque.addFirst(i);
        }
        assertEquals(1, (int) deque.removeLast());
        assertEquals(itemsCount, (int) deque.removeFirst());
    }

    @Test
    public void highCapacityTest() {
        Deque<Integer> deque = new Deque<>();
        int itemsCount = 4;
        long additionTime = measureTime(() -> {
            for (int i = 1; i <= itemsCount; i++) {
                deque.addFirst(i);
            }
        });
        long removalTime = measureTime(() -> {
            while(!deque.isEmpty()) {
                deque.removeLast();
            }
        });
        StdOut.printf("Added %d items in %d ms", itemsCount, additionTime);
        StdOut.printf("Removed %d items in %d ms", itemsCount, removalTime);
    }

}