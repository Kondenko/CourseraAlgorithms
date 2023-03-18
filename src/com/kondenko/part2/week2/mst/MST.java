package com.kondenko.part2.week2.mst;

import edu.princeton.cs.algs4.Edge;


public interface MST {

    Iterable<Edge> edges();

    double weight();
}
