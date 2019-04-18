package com.kondenko.week4.quiz;

import java.util.Arrays;

import static com.kondenko.ArrayUtils.gt;
import static com.kondenko.ArrayUtils.swap;

public class BinaryHeap<T extends Comparable<T>> {

    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Comparable[3];

    private int size = 0;

    public BinaryHeap() {
    }

    public void add(T item) {
        size++;
        grow();
        items[size] = item;
        swim(size);
    }

    private void swim(int i) {
        int k = i;
        while (k > 1 && gt(items, k, k / 2)) {
            swap(items, k / 2, k);
            k /= 2;
        }
    }

    private void sink(int i) {

    }

    private void grow() {
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }
    }

    private void shrink() {
        if (size <= items.length / 4) {
            items = Arrays.copyOf(items, items.length / 2);
        }
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> bh = new BinaryHeap<>();
        bh.add(3);
        bh.add(2);
        bh.add(1);
        bh.add(4);
    }

}
