package com.kondenko.part2.week2.mst;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.HashSet;

public class PrimMST implements MST {

    private final EdgeWeightedGraph graph;

    private final IndexMinPQ<Double> pq;

    private final Edge[] edgeTo;

    private final double[] distTo;

    private final HashSet<Edge> mst = new HashSet<>();

    private double weight = 0;

    public PrimMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        this.pq = new IndexMinPQ<>(graph.V());
        this.edgeTo = new Edge[graph.V()];
        this.distTo = new double[graph.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(pq.delMin());
        }
    }

    private void visit(int v) {
        Edge mstEdge = edgeTo[v];
        if (mstEdge != null) {
            mst.add(mstEdge);
            weight += mstEdge.weight();
        }
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            boolean wMarked = distTo[w] != Double.POSITIVE_INFINITY;
            if (pq.contains(w) && edge.weight() < pq.keyOf(w)) {
                pq.decreaseKey(w, edge.weight());
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
            }
            if (wMarked) continue;
            if (!pq.contains(w) && edge.weight() < distTo[w]) {
                pq.insert(w, edge.weight());
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
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