package com.kondenko.part2.week2.prim;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;


public interface MST {

    Iterable<Edge> edges();

    double weight();
}
