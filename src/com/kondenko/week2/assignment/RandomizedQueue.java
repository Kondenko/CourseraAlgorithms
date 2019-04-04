package com.kondenko.week2.assignment;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

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
        return new RandomizedIterator<>(queue, size);
    }

    private void shuffle() {
        shouldShuffle = false;
        shufflePosition = last;
        StdRandom.shuffle(queue, first, last);
    }

    private void resize(int capacity) {
        Item[] items = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            items[i] = queue[(first + i) % queue.length];
        }
        queue = items;
        shufflePosition -= first; // TODO test if works correctly
        first = 0;
        last = size;
    }

    private static class RandomizedIterator<Item> implements Iterator<Item> {

        private Item[] randomizedQueue;

        private int pointer = 0;

        private RandomizedIterator(Item[] q, int size) {
            this.randomizedQueue = Arrays.copyOf(q, size);
            StdRandom.shuffle(randomizedQueue);
        }

        @Override
        public boolean hasNext() {
            return pointer < randomizedQueue.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomizedQueue[pointer++];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rqueue.enqueue(i);
        }
        StdOut.println("Using an iterator");
        for (Integer integer : rqueue) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        StdOut.println("Dequeueing");
        while (!rqueue.isEmpty()) {
            StdOut.print(rqueue.dequeue() + " ");
        }
    }

}
