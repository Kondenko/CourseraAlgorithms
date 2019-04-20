package com.kondenko.week4.quiz;

import edu.princeton.cs.algs4.StdRandom;

import static com.kondenko.ArrayUtils.swap;

public class RandomizedPriorityQueue<T extends Comparable<T>> extends PriorityQueue<T> {

    public T deleteRandom() {
        int i = randomIndex();
        T item = items[i];
        swap(items, i, size);
        items[size--] = null;
        swim(i);
        return item;
    }

    public T sample() {
        return items[randomIndex()];
    }

    private int randomIndex() {
        if (size == 0) throw new NullPointerException("No items are present");
        if (size == 1) return 1;
        return StdRandom.uniform(1, size);
    }

}
