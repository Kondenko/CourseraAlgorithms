package com.kondenko.week4.quiz;

import java.util.Arrays;
import java.util.Comparator;

import static com.kondenko.ArrayUtils.swap;

public class BinaryHeap<T extends Comparable<T>> {

    public static <T extends Comparable<T>> BinaryHeap<T> min(T... items) {
        BinaryHeap<T> bh = new BinaryHeap<T>(Comparator.reverseOrder());
        bh.add(items);
        return bh;
    }

    public static <T extends Comparable<T>> BinaryHeap<T> max(T... items) {
        BinaryHeap<T> bh = new BinaryHeap<>();
        bh.add(items);
        return bh;
    }

    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Comparable[3];

    private int size = 0;

    private Comparator<T> comparator = Comparable::compareTo;

    public BinaryHeap() { }

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public void add(T... items) {
        for (T item : items) {
            add(item);
        }
    }

    public void add(T item) {
        size++;
        grow();
        items[size] = item;
        swim(size);
    }

    public T removeRoot() {
        T root = items[1];
        swap(items, 1, size);
        items[size--] = null;
        sink(1);
        shrink();
        return root;
    }

    public T root() {
        return items[1];
    }

    private void swim(int i) {
        int k = i;
        while (k > 1 && gt(k, k / 2)) {
            swap(items, k / 2, k);
            k /= 2;
        }
    }

    private void sink(int i) {
        int k = i;
        while (k * 2 <= size) {
            int j = k * 2;
            if (j < size && lt(j, j + 1)) j++;
            if (!lt(k, j)) break;
            swap(items, k, j);
            k *= 2;
        }
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

    private boolean lt(int i, int j) {
        return comparator.compare(items[i], items[j]) < 0;
    }

    private boolean gt(int i, int j) {
        return comparator.compare(items[i], items[j]) > 0;
    }

}
