package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Stack;

import static com.kondenko.Utils.println;
import static com.kondenko.Utils.size;

public class DiameterAndCenterOfTree {

	/*
	 * 1. Perform DFS, store the longest subtree of each vertex.
	 * 2. Take 2 largest paths of the root, it will be the longest path
	 */
	public static int diameter(Graph g) {
		int v = g.V();
		if (v <= 1) return 0;
		if (v == 2) return 1;
		MaxPQ<Integer> depths = new MaxPQ<>();
		dfsDepth(g, 0, depths);
		return depths.size() == 1 ? depths.max() : depths.delMax() + depths.delMax() + 1; // 1 for root
	}

	private static int dfsDepth(Graph g, int s, MaxPQ<Integer> depths) {
		boolean[] marked = new boolean[g.V()];
		marked[s] = true;
		return dfsDepth(g, s, marked, 1, depths);
	}

	private static int dfsDepth(Graph g, int s, boolean[] marked, int depth, MaxPQ<Integer> depths) {
		depth++;
		for (int v : g.adj(s)) {
			if (!marked[v]) {
				println("(%d) is at depth %d", v, depth);
				marked[v] = true;
				dfsDepth(g, v, marked, depth, depths);
				if (depth == 2) {
					println("Subtree processed, adding depth %d", depth);
					depths.insert(depth);
				}
			}
		}
		return depth;
	}

	public static int center(Graph g) {
		int center = -1;
		return center;
	}

}
