package com.kondenko.week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class LogUF extends WeightedQuickUnionUF {


    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public LogUF(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        super.union(p, q);
    }

}
