package com.kondenko.part1.week3.quiz;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    Node<E> first = null;

    public void add(E element) {
        if (first == null) first = new Node<>(element);
        else first.next = new Node<>(element);
    }

    public E get(int index) {
        int i = 0;
        for (E e : this) {
            if (index == i) return e;
            i++;
        }
        return null;
    }

    public void delete(int index) {
        int i = 0;
        if (first.next == null) {
            first = null;
        } else {
            Iterator<Node<E>> iter = nodesIterator();
            for (Node<E> node = iter.next(); iter.hasNext(); i++) {
                if (i == index - 1) node.next = node.next.next;
            }
        }
    }

    public boolean contains(E element) {
        for (E e : this) {
            if (e.equals(element)) return true;
        }
        return false;
    }

    private Iterator<Node<E>> nodesIterator() {
        return new NodesIterator(first);
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementsIterator(first);
    }

    public static class Node<E> {

        private final E element;

        Node<E> next = null;

        public Node(E element) {
            this.element = element;
        }

        public E element() {
            return element;
        }

    }

    private class ElementsIterator implements Iterator<E> {

        private Node<E> node;

        public ElementsIterator(Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E element = node.element;
            node = node.next;
            return element;
        }

    }

    private class NodesIterator implements Iterator<Node<E>> {

        private Node<E> node;

        public NodesIterator(Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Node<E> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<E> n = node;
            node = node.next;
            return n;
        }

    }

}
