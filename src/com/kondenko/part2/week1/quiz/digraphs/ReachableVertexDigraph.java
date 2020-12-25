package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class ReachableVertexDigraph {

	static final int CANT_BE_REACHED = -1;

	static int find(Digraph g) {
		int potentiallyReachableVertex = findPotentiallyReachableVertex(g);
		if (potentiallyReachableVertex == CANT_BE_REACHED) {
			return CANT_BE_REACHED;
		}
		for (int v = 0; v < g.V(); v++) {
			if (v != potentiallyReachableVertex && !isVertexReachable(g, v, potentiallyReachableVertex)) {
				return CANT_BE_REACHED;
			}
		}
		return potentiallyReachableVertex;
	}

	private static boolean isVertexReachable(Digraph g, int start, int vToFind) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[g.V()];
		stack.push(start);
		while (!stack.isEmpty()) {
			int current = stack.pop();
			if (!visited[current]) {
				visited[current] = true;
				if (current == vToFind) return true;
				for (int v : g.adj(current)) {
					stack.push(v);
				}
			}
		}
		return false;
	}

	private static int findPotentiallyReachableVertex(Digraph g) {
		for (int v = 0; v < g.V(); v++) {
			if (g.indegree(v) > 0) return v;
		}
		return CANT_BE_REACHED;
	}

}
