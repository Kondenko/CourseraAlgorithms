package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SAP {

	private Digraph digraph;

	private String[] synsets;

	int root;

	// constructor takes a digraph (not necessarily a DAG)
	// TODO Remove synsets
	public SAP(Digraph G) {
		this(G, null);
	}

	public SAP(Digraph G, String[] synsets) {
		this.synsets = synsets;
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
		return findCommonAncestor(v, w).getAncestor();
	}

	private Result findCommonAncestor(Iterable<Integer> vIterable, Iterable<Integer> wIterable) {
		Result closestAncestor = null;
		for (int v : vIterable) {
			for (int w : wIterable) {
				Result ancestor = dfs(new HashMap<>(), 0, root, v, w);
				if (closestAncestor == null || ancestor.pathLength() < closestAncestor.pathLength()) {
					// println("New common ancestor found:");
					closestAncestor = ancestor;
				}
				// println("Ancestor of %s and %s: %s", synsets[v], synsets[w], ancestor);
			}
		}
		return closestAncestor;
	}

	private Result dfs(HashMap<Integer, List<Integer>> marked, int depth, int current, int v, int w) {
		Iterable<Integer> adj = digraph.adj(current);
		int vDepth = current == v ? depth : -1;
		int wDepth = current == w ? depth : -1;
		Result newResult = null;
		for (int vertex : adj) {
			if (!marked.getOrDefault(current, Collections.emptyList()).contains(vertex)) {
				// Mark the vertex as visited
				List<Integer> newList = marked.getOrDefault(current, new ArrayList<>());
				newList.add(vertex);
				marked.put(current, newList);
				// Calculate the lowest common ancestor
				int childDepth = depth + 1;
				Result result = dfs(marked, childDepth, vertex, v, w);
				if (v == vertex) {
					vDepth = childDepth;
					// println("%s was found at %d: %s", synsets[vertex], childDepth, result);
				} else if (result.vFound) {
					vDepth = result.vDepth;
					// println("%s", synsets[vertex]);
				}
				if (w == vertex) {
					wDepth = childDepth;
					// println("%s was found at %d: %s", synsets[vertex], childDepth, result);
				} else if (result.wFound) {
					wDepth = result.wDepth;
					// println("%s", synsets[vertex]);
				}
				int newResultDepth = (vDepth - result.ancestorDepth) + (wDepth - result.ancestorDepth);
				if (newResultDepth <= result.pathLength() && result.isCommonAncestor()) {
					newResult = result;
					// println("New best result: " + result);
				}
			}
		}
		return newResult != null ? newResult : new Result(current, depth, vDepth, wDepth);
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

	private boolean contains(Iterable<Integer> iterable, int i) {
		for (Integer integer : iterable) {
			if (integer == i) return true;
		}
		return false;
	}

	private class Result {

		final int ancestorDepth;

		private final int vertex;

		private final boolean vFound;

		private final boolean wFound;

		private final int vDepth;

		private final int wDepth;

		public Result(int vertex, int ancestorDepth, int vDepth, int wDepth) {
			this.vertex = vertex;
			this.ancestorDepth = ancestorDepth;
			this.vFound = vDepth >= 0;
			this.wFound = wDepth >= 0;
			this.vDepth = vDepth;
			this.wDepth = wDepth;
		}

		int getAncestor() {
			return pathLength() >= 0 ? vertex : -1;
		}

		int pathLength() {
			return isCommonAncestor() ? (vDepth - ancestorDepth) + (wDepth - ancestorDepth) : -1;
		}

		boolean isCommonAncestor() {
			return vFound && wFound;
		}

		@Override
		public String toString() {
			return "Result{" +
					"ancestor=" + synsets[vertex] +
					", ancestorDepth=" + ancestorDepth +
					", pathLength=" + pathLength() +
					", vFound=" + vFound +
					", wFound=" + wFound +
					", vDepth=" + vDepth +
					", wDepth=" + wDepth +
					'}';
		}

	}

}

