package com.kondenko.part2.week2.prim;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.TreeSet;

public class MstTest {

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G;
        G = new EdgeWeightedGraph(in);

        MST lazyPrimMST = new LazyPrimMST(G);
        MST primMST = new PrimMST(G);

        printEdges("Lazy Prim", lazyPrimMST);
        printEdges("Eager Prim", primMST);

        StdOut.println();
        StdOut.printf("MSTs are%s the same", lazyPrimMST.weight() != primMST.weight() ? " NOT" : "");
    }

    private static void printEdges(String title, MST mst) {
        StringBuilder sb = new StringBuilder(title).append(":\n");
        TreeSet<Edge> edges = new TreeSet<>(Utils.toList(mst.edges()));
        sb.append(edges);
        StdOut.println(sb.toString());
    }
}
