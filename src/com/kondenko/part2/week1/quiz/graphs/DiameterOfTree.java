package com.kondenko.part2.week1.quiz.graphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.MaxPQ;

import static com.kondenko.Utils.println;

public class DiameterOfTree {

	/**
	 * Task: design a linear-time algorithm to find the longest simple path in the graph
	 * <p>
	 * Solution: find 2 deepest subgraphs of the root node with DFS, the sum their depths + 1 will be the diameter.
	 */
	public static int diameter(Graph g) {
		int v = g.V();
		if (v <= 1) return 0;
		if (v == 2) return 1;
		MaxPQ<Integer> rootSubgraphsDepths = new MaxPQ<>();
		dfsDepth(g, rootSubgraphsDepths);
		if (rootSubgraphsDepths.size() == 1) return rootSubgraphsDepths.max();
		return rootSubgraphsDepths.delMax() + rootSubgraphsDepths.delMax() + 1; // + 1 for the root node
	}

	private static void dfsDepth(Graph g, MaxPQ<Integer> rootSubgraphsDepths) {
		boolean[] marked = new boolean[g.V()];
		marked[0] = true;
		dfsDepth(g, 0, marked, 1, rootSubgraphsDepths);
	}

	private static int dfsDepth(Graph g, int s, boolean[] marked, final int depth, MaxPQ<Integer> subgraphDepths) {
		MaxPQ<Integer> childSubgraphDepths = new MaxPQ<>();
		for (int v : g.adj(s)) {
			if (!marked[v]) {
				println("Depth of subgraph at node (%d) is %d", v, depth);
				marked[v] = true;
				// Store each's subgraph's depth
				childSubgraphDepths.insert(dfsDepth(g, v, marked, depth + 1, subgraphDepths));
			}
		}
		if (s == 0) {
			// Store all subgraphs of root into a priority queue to extract 2 largest ones later.
			for (Integer rootChildDepth : childSubgraphDepths) {
				subgraphDepths.insert(rootChildDepth);
			}
		}
		// If s is a leaf, its depth is 1. Otherwise, it's depth is the depth of it's deepest child + 1 for the node itself.
		int maxDepth = childSubgraphDepths.size() > 0 ? childSubgraphDepths.max() + 1 : 1;
		println("Max depth of subgraph (%d) is %d", s, maxDepth);
		return maxDepth;
	}

}
