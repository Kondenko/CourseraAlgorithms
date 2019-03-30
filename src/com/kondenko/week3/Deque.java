package com.kondenko.week3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Deque<Item> implements Iterable<Item> {

    private class Node {

        Item item;

        Node next;

        Node prev;

        public Node(Item item) {
            this.item = item;
        }

        @Override
        public String toString() {
            String left;
            if (prev == null) {
                left = "null";
            } else {
                left = prev.item.toString();
            }
            String right;
            if (next == null) {
                right = "null";
            } else {
                right = next.item.toString();
            }
            return String.format("%s <- %s -> %s", left, item, right);
        }

    }

    private Node first = null;

    private Node last = null;

    private int size = 0;

    /**
     * add the item to the front
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add a null item to deque");
        size++;
        Node oldFirst = first != null ? first : new Node(item);
        Node node = new Node(item);
        node.next = first != null ? oldFirst : null;
        oldFirst.prev = node;
        if (last == null) {
            last = oldFirst;
        }
        last.prev = oldFirst;
        first = node;
    }

    /**
     * add the item to the end
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add a null item to deque");
        size++;
        Node oldLast = last != null ? last : new Node(item);
        Node node = new Node(item);
        node.prev = last != null ? oldLast : null;
        oldLast.next = node;
        if (first == null) {
            first = oldLast;
        }
        first.next = oldLast;
        last = node;
    }

    /**
     * remove and return the item from the front
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        size--;
        Item item = first.item;
        first = first.next;
        resetIfEmpty();
        return item;
    }

    /**
     * remove and return the item from the end
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        size--;
        Item item = last.item;
        last = last.prev;
        resetIfEmpty();
        return item;
    }

    /**
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void resetIfEmpty() {
        if (isEmpty()) {
            first = null;
            last = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currentNode;

        public DequeIterator() {
            this.currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (isEmpty() || !hasNext()) throw new NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Can't remove items from the deque");
        }

        @Override
        public void forEachRemaining(Consumer<? super Item> action) {
            while (hasNext()) {
                action.accept(next());
            }
        }

    }

}