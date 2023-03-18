package com.kondenko.part2.week2.mst;

import edu.princeton.cs.algs4.*;

public class KruskalMST implements MST {

    private final Queue<Edge> mst = new Queue<>();

    private double weight = 0;

    public KruskalMST(EdgeWeightedGraph graph) {
        UF uf = new UF(graph.V());
        MinPQ<Edge> edges = new MinPQ<>();
        graph.edges().forEach(edges::insert);
        while (!edges.isEmpty()) {
            Edge e = edges.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }
}
