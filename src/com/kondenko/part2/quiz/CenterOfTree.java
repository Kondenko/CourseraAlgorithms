package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import static com.kondenko.Utils.println;

public class CenterOfTree {

	public static int center(Graph g) {
		boolean[] removed = bfsWithRemoval(g, 0);
		for (int i = removed.length - 1; i >= 0; i--) {
			if (!removed[i]) return i;
		}
		return -1;
	}

	/**
	 * Remove a leaf node until there are only 2 or 1 nodes left. They will be the center of the tree.
	 */
	private static boolean[] bfsWithRemoval(Graph g, int s) {
		Queue<Integer> adj = new Queue<>();
		boolean[] visited = new boolean[g.V()];
		boolean[] removed = new boolean[g.V()];
		adj.enqueue(s);
		while (!adj.isEmpty()) {
			for (int v : g.adj(adj.dequeue())) {
				if (!visited[v]) {
					println("Visited %d", v);
					adj.enqueue(v);
					visited[v] = true;
					if (g.degree(v) == 1) {
						println("Removed %d", v);
						removed[v] = true;
					}
				}
			}
		}
		return removed;
	}

}
