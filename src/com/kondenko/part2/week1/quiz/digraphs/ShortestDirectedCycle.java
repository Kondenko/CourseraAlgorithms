package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;

import java.util.Arrays;

import static com.kondenko.Utils.println;

public class ShortestDirectedCycle {

	private static final int NO_CYCLE = -1;

	public static void find(Digraph g) {
		int shortestCycleLength = dfs(g);
		println("\nThe shortest cycle in G has length of %d", shortestCycleLength);
	}

	private static int dfs(Digraph g) {
		int[] graphDepths = new int[g.V()];
		Arrays.fill(graphDepths, NO_CYCLE);
		for (int i = 0; i < g.V(); i++) {
			int[] depth = new int[g.V()];
			Arrays.fill(depth, NO_CYCLE);
			dfs(g, new boolean[g.V()][g.V()], i, i, depth);
			graphDepths[i] = depth[i];
		}
		int minCycleDepth = NO_CYCLE;
		for (int depth : graphDepths) {
			if (minCycleDepth == NO_CYCLE || (depth != NO_CYCLE && depth < minCycleDepth)) {
				minCycleDepth = depth;
			}
		}
		return minCycleDepth;
	}

	private static void dfs(Digraph g, boolean[][] marked, int root, int current, int[] depth) {
		Iterable<Integer> adj = g.adj(current);
		for (int v : adj) {
			if (!marked[current][v]) {
				marked[current][v] = true;
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
		ShortestDirectedCycle.find(g);
	}

}
