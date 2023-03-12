package com.kondenko.part2.week2;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class PrimMST implements MST {

    private final EdgeWeightedGraph graph;

    private final IndexMinPQ<Double> pq;

    private final Edge[] edgeTo;

    private final double[] distTo;

    private final HashSet<Edge> mst = new HashSet<>();

    public PrimMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        this.pq = new IndexMinPQ<>(graph.V());
        this.edgeTo = new Edge[graph.V()];
        this.distTo = new double[graph.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            Utils.println(pqToString());
            visit(pq.delMin());
        }
    }

    private void visit(int v) {
        Utils.println("Visiting %d", v);
        Utils.println(toString());
        Edge mstEdge = edgeTo[v];
        if (mstEdge != null) {
            Utils.println("Adding %d and %s to the MST", v, mstEdge);
            mst.add(mstEdge);
        }
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            boolean wMarked = distTo[w] != Double.POSITIVE_INFINITY;
            Utils.println("Checking %d-%d", v, w);
            if (pq.contains(w) && edge.weight() < pq.keyOf(w)) {
                Utils.println("Found a better edge to %d, decreasing from %s %.02f to %s",
                        w,
                        edgeFromWeight(pq.keyOf(w)),
                        pq.keyOf(w),
                        edge
                );
                pq.decreaseKey(w, edge.weight());
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
            }
            if (wMarked) {
                Utils.println("%d marked, skipping", w);
                continue;
            }
            if (!pq.contains(w) && edge.weight() < distTo[w]) {
                Utils.println("Adding %d and %s to the PQ", w, edge);
                pq.insert(w, edge.weight());
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
            }
        }
        Utils.println("");
    }

    private String pqToString() {
        StringBuilder sb = new StringBuilder("PQ:\n");
        pq.forEach(i ->
                sb.append(i)
                        .append(": ")
                        .append(pq.keyOf(i))
                        .append(" ")
                        .append(edgeFromWeight(pq.keyOf(i)))
                        .append('\n'));
        return sb.toString();
    }

    private String edgeFromWeight(double weight) {
        return Utils.toList(graph.edges())
                .stream()
                .filter(e -> e.weight() == weight)
                .map(e -> String.format("%d-%d", e.either(), e.other(e.either())))
                .findFirst()
                .orElse("");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  edgeTo  distTo\n");
        for (int i = 0; i < graph.V(); i++) {
            String edgeToStr;
            if (edgeTo[i] != null) {
                int v = edgeTo[i].either();
                int w = edgeTo[i].other(v);
                edgeToStr = String.format("%d-%d", v, w);
            } else {
                edgeToStr = "   ";
            }
            String distToStr = distTo[i] != Double.POSITIVE_INFINITY ? String.valueOf(distTo[i]) : "";
            sb.append(i).append("  ").append(edgeToStr).append("     ").append(distToStr).append('\n');
        }
        return sb.toString();
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0;
        for (Edge edge : edges()) {
            weight += edge.weight();
        }
        return weight;
    }
}