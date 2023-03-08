package com.kondenko.part2.week2;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;

public class MstTest {
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G;
        G = new EdgeWeightedGraph(in);

        MST lazyPrimMST = new LazyPrimMST(G);
        for (Edge e : lazyPrimMST.edges()) {
            StdOut.println(e);
        }
        StdOut.println(lazyPrimMST.weight());
    }
}
