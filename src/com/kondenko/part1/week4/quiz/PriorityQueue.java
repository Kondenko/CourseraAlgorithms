package com.kondenko.part1.week4.quiz;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import static com.kondenko.ArrayUtils.swap;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    public static <T extends Comparable<T>> PriorityQueue<T> min(T... items) {
        PriorityQueue<T> bh = new PriorityQueue<T>(Comparator.reverseOrder());
        bh.add(items);
        return bh;
    }

    public static <T extends Comparable<T>> PriorityQueue<T> max(T... items) {
        PriorityQueue<T> bh = new PriorityQueue<>();
        bh.add(items);
        return bh;
    }

    @SuppressWarnings("unchecked")
    protected T[] items = (T[]) new Comparable[3];

    protected int size = 0;

    protected Comparator<T> comparator = Comparable::compareTo;

    public PriorityQueue() { }

    public PriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
        if (size == 0) throw new IndexOutOfBoundsException("Heap is empty");
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

    public boolean contains(T item) {
        for (int i = 1; i < items.length && items[i] != null; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    protected final void swim(int i) {
        int k = i;
        while (k > 1 && gt(k, k / 2)) {
            swap(items, k / 2, k);
            k /= 2;
        }
    }

    protected final void sink(int i) {
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

    @Override
    public Iterator<T> iterator() {
        return new PQIterator();
    }

    private class PQIterator implements Iterator<T> {

        private int i = 1;

        @Override
        public boolean hasNext() {
            return i <= size;
        }

        @Override
        public T next() {
            return items[i++];
        }

    }

}
