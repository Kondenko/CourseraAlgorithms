package com.kondenko.part2.week2.mst;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.TreeSet;

public class MstTest {

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In("data/1000EWG.txt"));

        MST lazyPrim = new LazyPrimMST(graph);
        printMst("Lazy Prim", lazyPrim, false);

        MST eagerPrim = new PrimMST(graph);
        printMst("Eager Prim", eagerPrim, false);

        MST kruskal = new KruskalMST(graph);
        printMst("Kruskal", kruskal, false);

        boolean areAllTreesTheSame = kruskal.weight() == lazyPrim.weight() && kruskal.weight() == eagerPrim.weight();

        StdOut.println();
        StdOut.printf("MSTs are%s the same", areAllTreesTheSame ? "" : " NOT");
    }

    private static void printMst(String title, MST mst, @SuppressWarnings("SameParameterValue") boolean printEdges) {
        StringBuilder sb = new StringBuilder(title).append(": ").append(mst.weight()).append('\n');
        if (printEdges) {
            TreeSet<Edge> edges = new TreeSet<>(Utils.toList(mst.edges()));
            sb.append(edges);
        }
        StdOut.println(sb.toString());
    }
}
