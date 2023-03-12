package com.kondenko.part2.week2.quiz;

import com.kondenko.Utils;
import com.kondenko.part2.week2.prim.LazyPrimMST;
import com.kondenko.part2.week2.prim.MST;
import com.kondenko.part2.week2.prim.PrimMST;
import edu.princeton.cs.algs4.*;

import static com.kondenko.Utils.println;

/**
 * Problem:
 * Is an edge in a MST. Given an edge-weighted graph G and an edge e, design a linear-time algorithm to determine whether e appears in some MST of G.
 * Note: Since your algorithm must take linear time in the worst case, you cannot afford to compute the MST itself.
 *
 * Possible solution:
 * We can apply the cut property. Let v be one vertex of e and w be the other.
 * Then we'll:
 * 1. Partition the graph into two sets: v and the rest.
 * 2. Check the edges adjacent to v and remember the edge with the minimum weight, which we will call vMin.
 * 3. Repeat the same for w and get wMin.
 * 4. if vMin == e || wMin == e, then e appears in some MST of G.
 */
public class IsEdgeInMST {

    static boolean check(EdgeWeightedGraph g, Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        Edge vMin = findMinAdjacent(g, v);
        Edge wMin = findMinAdjacent(g, w);
        return vMin.weight() == edge.weight() || wMin.weight() == edge.weight();
    }

    private static Edge findMinAdjacent(EdgeWeightedGraph g, int v) {
        Edge min = null;
        for (Edge edge : g.adj(v)) {
            if (min == null || edge.weight() < min.weight()) {
                min = edge;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        In in = new In("data/tinyEWG.txt");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        MST primMST = new PrimMST(graph);
        Edge inMst = new Edge(0, 7, 0.16);
        Edge notInMst = new Edge(0, 4, 0.38);

        println("Edge %s is in MST: expected = %b, actual = %b",
                inMst,
                contains(primMST, inMst),
                check(graph, inMst));
        println("Edge %s is NOT in MST: expected = %b, actual = %b",
                notInMst,
                contains(primMST, notInMst),
                check(graph, notInMst));
    }

    private static boolean contains(MST mst, Edge e) {
        for (Edge edge : mst.edges()) {
            if (edge.weight() == e.weight()) return true;
        }
        return false;
    }
}
