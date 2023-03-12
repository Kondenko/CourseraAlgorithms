package com.kondenko.part2.week2.prim;

import edu.princeton.cs.algs4.*;

public class LazyPrimMST implements MST {

    private final EdgeWeightedGraph graph;

    private final Queue<Edge> mst = new Queue<>();

    private final MinPQ<Edge> pq = new MinPQ<>();

    private final boolean[] visited;

    private double weight = 0.0;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];

        visit(0);
        while (!pq.isEmpty()) {
            Edge current = pq.delMin();
            int v = current.either();
            int w = current.other(v);
            if (!(visited[v] && visited[w])) {
                mst.enqueue(current);
                weight += current.weight();
            }
            if (!visited[v]) visit(v);
            if (!visited[w]) visit(w);
        }
    }

    private void visit(int v) {
        visited[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!visited[e.other(v)]) {
                pq.insert(e);
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