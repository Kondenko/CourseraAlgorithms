package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.*;

import java.util.List;

public class SAP {

	private Digraph digraph;

	private int root;

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
		return findCommonAncestor(v, w).length;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) throw new IllegalArgumentException();
		return findCommonAncestor(v, w).vertex;
	}

	private Ancestor findCommonAncestor(Iterable<Integer> vIterable, Iterable<Integer> wIterable) {
		Digraph reversed = digraph.reverse(); // reverse digraph so that a path can be found from vertices farther from root to vertices closer to root
		BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(reversed, vIterable);
		BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(reversed, wIterable);
		return findSap(vPaths, wPaths);
	}

	private Ancestor findSap(BreadthFirstDirectedPaths vPaths, BreadthFirstDirectedPaths wPaths) {
		Ancestor shortestPath = new Ancestor(-1, 0);
		// Run a non-recursive BFS
		Queue<Integer> q = new Queue<Integer>();
		boolean[] marked = new boolean[digraph.V()];
		marked[root] = true;
		q.enqueue(root);
		while (!q.isEmpty()) {
			int current = q.dequeue();
			// Enqueue adjacent vertices
			for (int adjacent : digraph.adj(current)) {
				if (!marked[adjacent]) {
					marked[adjacent] = true;
					q.enqueue(adjacent);
				}
			}
			// See if [current] is a common ancestor and update [shortestPath] if it's shorter than the previous one.
			if (vPaths.hasPathTo(current) && wPaths.hasPathTo(current)) {
				int length = vPaths.distTo(current) + wPaths.distTo(current);
				if (shortestPath.length == 0 || length < shortestPath.length) {
					shortestPath = new Ancestor(current, length);
				}
			}
		}
		return shortestPath;
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

	private static class Ancestor {

		final int vertex;

		final int length;

		public Ancestor(int vertex, int length) {
			this.vertex = vertex;
			this.length = length;
		}

	}

}

