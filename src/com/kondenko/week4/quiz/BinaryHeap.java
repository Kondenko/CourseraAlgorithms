package com.kondenko.week4.quiz;

import com.kondenko.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.ArrayUtils.swap;
import static com.kondenko.Utils.println;

public class BinaryHeap<T extends Comparable<T>> {

    public static <T extends Comparable<T>> BinaryHeap<T> min() {
        return new BinaryHeap<T>(ArrayUtils.reverseComparator());
    }

    public static <T extends Comparable<T>> BinaryHeap<T> max() {
        return new BinaryHeap<>();
    }

    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Comparable[3];

    private int size = 0;

    private Comparator<T> comparator = Comparable::compareTo;

    public BinaryHeap() {
    }

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
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

    public T peekRoot() {
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
            if (!lt(j, k)) break;
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

    public void print() {
        StringBuilder sb = new StringBuilder();
        StdOut.println(Arrays.deepToString(items));
        for (int i = 1, j = 2; i <= size; i++) {
            sb.append(" ").append(items[i]).append(" ");
            if (i == j - 1) {
                sb.append("\n");
                j *= 2;
            }
        }
        StdOut.println(sb.toString());
    }

    private boolean lt(int i, int j) {
        return comparator.compare(items[i], items[j]) < 0;
    }

    private boolean gt(int i, int j) {
        return comparator.compare(items[i], items[j]) > 0;
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> min = BinaryHeap.min();
        BinaryHeap<Integer> max = BinaryHeap.max();
        min.add(1, 2, 3);
        max.add(1, 2, 3);
        println("Min root = " + min.peekRoot());
        println("Max root = " + max.peekRoot());
    }


}
