package com.kondenko.week3;

import java.util.Iterator;

public class Deque<T> implements Iterable<T> {

    private Object[] items = new Object[2];

    private int first = -1;

    private int last = 0;

    private int size = 0;

    // return the number of items on the deque

    // add the item to the front
    public void addFirst(T item) {
        size++;
        items[++first] = item;
    }

    // add the item to the end
    public void addLast(T item) {
        size++;
        items[++last] = item;
    }

    // remove and return the item from the front
    public T removeFirst() {
        size--;
        return (T) items[first++];
    }

    // remove and return the item from the end
    public T removeLast() {
        size--;
        return (T) items[last--];
    }

    public int size() {
        return size;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return an iterator over items in order from front to end
    public Iterator<T> iterator() {
        return null;
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }

}