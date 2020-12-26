package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SAP {

	private Digraph digraph;

	int root;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		if (G == null) throw new IllegalArgumentException();
		digraph = G.reverse();
		for (int v = 0; v < digraph.V(); v++) {
			if (digraph.indegree(v) == 0) {
				root = v;
				break;
			}
		}
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		return length(List.of(v), List.of(w));
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		return ancestor(List.of(v), List.of(w));
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) throw new IllegalArgumentException();
		return findCommonAncestor(v, w).pathLength();
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) throw new IllegalArgumentException();
		return findCommonAncestor(v, w).ancestor;
	}

	private Result findCommonAncestor(Iterable<Integer> v, Iterable<Integer> w) {
		return dfs(digraph, new HashMap<>(), 0, root, v, w);
	}

	private Result dfs(Digraph g, HashMap<Integer, List<Integer>> marked, int depth, int current, Iterable<Integer> v, Iterable<Integer> w) {
		Iterable<Integer> adj = g.adj(current);
		int vDepth = -1;
		int wDepth = -1;
		for (int vertex : adj) {
			if (!marked.getOrDefault(current, Collections.emptyList()).contains(vertex)) {
				List<Integer> newList = marked.getOrDefault(current, new ArrayList<>());
				newList.add(vertex);
				marked.put(current, newList);
				Result result = dfs(g, marked, depth + 1, vertex, v, w);
				if (result.isCommonAncestor()) return result;
				if (contains(v, vertex) || result.vFound) {
					vDepth = Math.max(depth, result.vDepth);
				}
				if (contains(w, vertex) || result.wFound) {
					wDepth = Math.max(depth, result.wDepth);
				}
			}
		}
		return new Result(current, vDepth, wDepth);
	}

	// do unit testing of this class

	public static void main(String[] args) {
		// The graph is reversed
		Digraph G = new Digraph(8);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		G.addEdge(3, 1);
		G.addEdge(4, 1);
		G.addEdge(5, 1);

		G.addEdge(6, 5);
		G.addEdge(7, 6);

		SAP sap = new SAP(G);
		sap.findCommonAncestor(List.of(3), List.of(7));
	}

	private boolean contains(Iterable<Integer> iterable, int i) {
		for (Integer integer : iterable) {
			if (integer == i) return true;
		}
		return false;
	}

	private class Result {

		final int ancestor;

		private final boolean vFound;

		private final boolean wFound;

		private final int vDepth;

		private final int wDepth;

		public Result(int vertex, int vDepth, int wDepth) {
			this.vFound = vDepth >= 0;
			this.wFound = wDepth >= 0;
			this.ancestor = vertex;
			this.vDepth = vDepth;
			this.wDepth = wDepth;
		}

		int pathLength() {
			return isCommonAncestor() ? vDepth + wDepth : -1;
		}

		boolean isCommonAncestor() {
			return vFound && wFound;
		}

	}

}

