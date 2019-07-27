package com.kondenko.week5.quiz;

import edu.princeton.cs.algs4.RedBlackBST;

public class GeneralizedQueue<T extends Comparable<T>> {

    private RedBlackBST<Integer, T> bst = new RedBlackBST<>();

    private int size = 0;

    public void enqueue(T item) {
        bst.put(size++, item);
    }

    public T dequeue() {
        T value = bst.get(size - 1);
        bst.delete(size-- - 1);
        return value;
    }

    public T get(int index) {
        return bst.get(index);
    }

    public T remove(int index) {
        T value = bst.get(index);
        bst.delete(index);
        size--;
        for (int i = index; i < size; i++) {
            bst.put(i, bst.get(i + 1));
        }
        return value;
    }

    public int size() {
        return size;
    }

}
