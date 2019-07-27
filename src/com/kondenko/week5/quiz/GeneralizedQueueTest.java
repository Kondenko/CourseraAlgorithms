package com.kondenko.week5.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GeneralizedQueueTest {

    @Test
    public void shouldEnqueueAndDequeue() {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, (int) queue.dequeue());
    }

    @Test
    public void shouldRemove() {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<>();
        int size = 6;
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);
        }
        int indexToRemove = 1;
        int removedItem = queue.remove(indexToRemove);
        assertEquals(size - 1, queue.size());
        assertNotEquals(removedItem, (int) queue.get(indexToRemove));
        assertNotEquals(null, queue.get(indexToRemove));
    }

}