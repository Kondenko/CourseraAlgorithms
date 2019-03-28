package com.kondenko.week3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(T item) {
            this.item = item;
        }
    }

    private Node first = null;

    private Node last = null;

    private int size = 0;

    /**
     * add the item to the front
     **/
    public void addFirst(T item) {
        if (item == null) throw new IllegalArgumentException("Can't add a null item to deque");
        size++;
        Node oldFirst = first != null ? first : new Node(item);
        Node node = new Node(item);
        node.next = oldFirst;
        oldFirst.prev = node;
        if (last == null) {
            last = oldFirst;
        }
        last.prev = oldFirst;
        first = node;
    }

    /**
     * add the item to the end
     **/
    public void addLast(T item) {
        if (item == null) throw new IllegalArgumentException("Can't add a null item to deque");
        size++;
//        initFirstIfNull(item, () -> {
//            Node node = new Node(item);
//            last.next = node;
//            node.prev = last;
//            last = node;
//        });
    }

    /**
     * remove and return the item from the front
     **/
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        size--;
        T item = first.item;
        first = first.next;
        return item;
    }

    /**
     * remove and return the item from the end
     **/
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        size--;
        T item = last.item;
        last = last.prev;
        return item;
    }

    /**
     * return the number of items on the deque
     **/
    public int size() {
        return size;
    }

    /**
     * is the deque empty?
     **/
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return an iterator over items in order from front to end
     **/
    public Iterator<T> iterator() {
        return null;
    }

}