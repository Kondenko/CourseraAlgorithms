package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

import static com.kondenko.Utils.println;

public class ShortestDirectedCycle {

	public static void find(Digraph g) {
		Stack<Integer>[] vertices = new Stack[g.V()];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Stack<>();
		}
		boolean[] marked = new boolean[g.V()];
	}

/*
	private static void dfs(Digraph g, boolean[] marked, Stack<Integer>[] vertices, int root, int dfsRoot, int current, int depth) throws ExecutionControl.NotImplementedException {
		int lastVisitedVertex = dfs();
		boolean isCycle = dfsRoot == lastVisitedVertex;
		if (isCycle) {
			vertices
		}
	}
*/

	private static void dfs(Digraph g, boolean[][] marked, int root, int current, int[] depth) {
		println("dfs(s = %d, depth = %d)", current, depth[current]);
		for (int v : g.adj(current)) {
			if (!marked[current][v]) {
				if (current == root) {
					depth[current] = 0;
					marked[current][v] = true;
				}
				int newDepth = depth[current] + 1;
				depth[v] = newDepth;
				dfs(g, marked, root, v, depth);
			}
		}
	}

	public static void main(String[] args) {
		Digraph g = new Digraph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(3, 5);
		g.addEdge(3, 4);
		g.addEdge(4, 1);
		// println(g.toString());
		int root = 1;
		dfs(g, new boolean[g.V()][g.V()], root, root, new int[g.V()]);
	}

	static class Result {
		int depth;
		int lastVisitedVertex;

		public Result(int depth, int lastVisitedVertex) {
			this.depth = depth;
			this.lastVisitedVertex = lastVisitedVertex;
		}

		@Override
		public String toString() {
			return "Result{" +
					"depth=" + depth +
					", lastVisitedVertex=" + lastVisitedVertex +
					'}';
		}
	}

}
