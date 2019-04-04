package com.kondenko.week2.assignment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DequeIteratorTest {

    @Test
    public void contentsShouldBeSame() {
        Deque<Integer> deque = new Deque<>();
        List<Integer> items = Arrays.asList(0, 1, 2);
        List<Integer> itemsFromIterator = new ArrayList<>();
        for (int i : items) {
            deque.addLast(i);
        }
        deque.iterator().forEachRemaining(itemsFromIterator::add);
        assertEquals(itemsFromIterator, items);
    }

    @Test
    public void contentsShouldBeMirrored() {
        Deque<Integer> deque = new Deque<>();
        List<Integer> items = Arrays.asList(0, 1, 2);
        List<Integer> itemsFromIterator = new ArrayList<>();
        for (int i : items) {
            deque.addFirst(i);
        }
        deque.iterator().forEachRemaining(itemsFromIterator::add);
        Collections.reverse(items);
        assertEquals(items, itemsFromIterator);
    }

    @Test
    public void shouldHaveNoNextIfEmpty() {
        Deque<Integer> deque = new Deque<>();
        Iterator<Integer> iterator = deque.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldHandleNestedIterators() {
        Deque<Integer> deque = new Deque<>();
        ArrayList<Integer> actual = new ArrayList<>();
        List<Integer> expected = Arrays.asList(
                1,
                1, 2, 3,
                2,
                1, 2, 3,
                3,
                1, 2, 3
        );
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        for (Integer i : deque) {
            actual.add(i);
            for (Integer j : deque) {
                actual.add(j);
            }
        }
        assertEquals(expected, actual);
    }

}