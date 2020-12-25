package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import kotlin.NotImplementedError;

import java.util.List;

import static com.kondenko.Utils.println;

public class SAP {

	private Digraph wordNet;

	int root;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		if (G == null) throw new IllegalArgumentException();
		wordNet = G.reverse();
		for (int v = 0; v < wordNet.V(); v++) {
			if (wordNet.indegree(v) == 0) {
				root = v;
				break;
			}
		}
		println("Root of Digraph is %d", root);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		throw new NotImplementedError();
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		throw new NotImplementedError();
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) throw new IllegalArgumentException();
		throw new NotImplementedError();
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) throw new IllegalArgumentException();
		throw new NotImplementedError();
	}

	private void findCommonAncestor(Iterable<Integer> v, Iterable<Integer> w) {
		Result ancestorResult = dfs(wordNet, new boolean[wordNet.V()][wordNet.V()], 0, root, v, w);
		String resultString = ancestorResult != null ? ancestorResult.toString() : "null";
		println("findCommonAncestor(%s, %s) = %s", v.toString(), w.toString(), resultString);
	}

	private Result dfs(Digraph g, boolean[][] marked, int depth, int current, Iterable<Integer> v, Iterable<Integer> w) {
		Iterable<Integer> adj = g.adj(current);
		boolean vFound = false;
		boolean wFound = false;
		for (int vertex : adj) {
			if (!marked[current][vertex]) {
				marked[current][vertex] = true;
				Result result = dfs(g, marked, depth + 1, vertex, v, w);
				if (result.isCommonAncestor()) return result;
				if (contains(v, vertex) || result.vFound) vFound = true;
				if (contains(w, vertex) || result.wFound) wFound = true;
			}
		}
		return new Result(vFound, wFound, current);
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

		final boolean vFound;

		final boolean wFound;

		final int vertex;

		public Result(boolean vFound, boolean wFound, int vertex) {
			this.vFound = vFound;
			this.wFound = wFound;
			this.vertex = vertex;
		}

		boolean isCommonAncestor() {
			return vFound && wFound;
		}

		@Override
		public String toString() {
			return "Result{" +
					"vFound=" + vFound +
					", wFound=" + wFound +
					", vertex=" + vertex +
					'}';
		}

	}

}

