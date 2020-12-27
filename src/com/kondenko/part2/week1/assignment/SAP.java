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
		return dfs(new HashMap<>(), 0, root, v, w);
	}

	private Result dfs(HashMap<Integer, List<Integer>> marked, int depth, int current, Iterable<Integer> v, Iterable<Integer> w) {
		Iterable<Integer> adj = digraph.adj(current);
		int vDepth = contains(v, current) ? depth : -1;
		int wDepth = contains(w, current) ? depth : -1;
		Result newResult = null;
		for (int vertex : adj) {
			if (!marked.getOrDefault(current, Collections.emptyList()).contains(vertex)) {
				List<Integer> newList = marked.getOrDefault(current, new ArrayList<>());
				newList.add(vertex);
				marked.put(current, newList);
				Result result = dfs(marked, depth + 1, vertex, v, w);
				if (contains(v, vertex)) {
					vDepth = depth + 1;
				} else if (result.vFound) {
					vDepth = result.vDepth;
				}
				if (contains(w, vertex)) {
					wDepth = depth + 1;
				} else if (result.wFound) {
					wDepth = result.wDepth;
				}
				if (vDepth - result.ancestorDepth + wDepth - result.ancestorDepth <= result.pathLength() && result.isCommonAncestor()) {
					newResult = result;
				}
			}
		}
		return newResult != null ? newResult : new Result(current, depth, vDepth, wDepth);
	}

	private boolean contains(Iterable<Integer> iterable, int i) {
		for (Integer integer : iterable) {
			if (integer == i) return true;
		}
		return false;
	}

	private class Result {

		final int ancestor;

		final int ancestorDepth;

		private final boolean vFound;

		private final boolean wFound;

		private final int vDepth;

		private final int wDepth;

		public Result(int vertex, int ancestorDepth, int vDepth, int wDepth) {
			this.ancestor = vertex;
			this.ancestorDepth = ancestorDepth;
			this.vFound = vDepth >= 0;
			this.wFound = wDepth >= 0;
			this.vDepth = vDepth;
			this.wDepth = wDepth;
		}

		int pathLength() {
			return isCommonAncestor() ? vDepth - ancestorDepth + wDepth - ancestorDepth : -1;
		}

		boolean isCommonAncestor() {
			return vFound && wFound;
		}

		@Override
		public String toString() {
			return "Result{" +
					"ancestor=" + ancestor +
					", ancestorDepth=" + ancestorDepth +
					", vFound=" + vFound +
					", wFound=" + wFound +
					", vDepth=" + vDepth +
					", wDepth=" + wDepth +
					'}';
		}

	}

}

