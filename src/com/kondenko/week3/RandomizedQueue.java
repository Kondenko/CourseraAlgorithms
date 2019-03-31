package com.kondenko.week3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static class RandomItem<Item> {
        int index;
        Item item;

        public RandomItem(int index, Item item) {
            this.index = index;
            this.item = item;
        }
    }

    @SuppressWarnings("unchecked")
    private Item[] queue = (Item[]) new Object[2];

    private int first = 0;

    private int last = 0;

    private int size = 0;

    private boolean shouldShuffle = true;

    private int shufflePosition = 0;

    /**
     * add the item.
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add a null element");
        shouldShuffle = true;
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        size++;
        queue[last++] = item;
    }

    /**
     * remove and return a random item.
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        size--;
        Item item = queue[first++];
        if (size / 4 == queue.length / 2) {
            resize(queue.length / 2);
        }
        if (shouldShuffle) {
            shuffle();
        }
        return item;
    }

    /**
     * return a random item (but do not remove it).
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return queue[StdRandom.uniform(first, last)];
    }

    /**
     * return the number of items on the randomized queue.
     */
    public int size() {
        return size;
    }

    /**
     * is the randomized queue empty?.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return null;
    }

    private void shuffle() {
        shouldShuffle = false;
        shufflePosition = last;
        StdRandom.shuffle(queue, first, last);
    }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
             items[i] = queue[(first + i) % queue.length];
        }
        queue = items;
        shufflePosition -= first; // TODO test if works correctly
        first = 0;
        last = size;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rqueue.enqueue(i);
        }
        while (!rqueue.isEmpty()) {
            StdOut.println(rqueue.dequeue());
        }
    }

}
