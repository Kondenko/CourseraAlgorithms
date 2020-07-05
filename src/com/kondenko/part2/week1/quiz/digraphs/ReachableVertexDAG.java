package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;

/*
  A vertex is only reachable from all other vertices if it only has incoming connections
  AND all other vertices have 1 outgoing edge (ignoring the case with parallel edges)
*/
public class ReachableVertexDAG {

	static final int CANT_BE_REACHED = -1;

	public static int find(Digraph g) {
		int reachableVertex = CANT_BE_REACHED;
		for (int v = 0; v < g.V(); v++) {
			if (g.outdegree(v) == 0 && g.indegree(v) > 0) reachableVertex = v;
			else if (g.outdegree(v) != 1) return CANT_BE_REACHED;
		}
		return reachableVertex;
	}

}
