package com.kondenko.part2.week1.quiz.digraphs;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.Digraph;

import java.util.Collections;
import java.util.Stack;

import static com.kondenko.Utils.println;

/**
 * Start DFS from root
 * add vertex to PATH
 * if vertex has more than one adjacent vertex
 * mark it as RETURN_POINT
 * for each adjacent vertex:
 * run DFS
 * if PATH has g.V() vertices:
 * return PATH
 * else:
 * pop PATH until vertex is RETURN_POINT
 */
public class HamiltonianPathInDag {

	private static int returnPoint = -1;

	static Iterable<Integer> find(Digraph g) {
		int root = 0;
		Stack<Integer> path = new Stack<>();
		Stack<Integer> dfsStack = new Stack<>();
		boolean[][] marked = new boolean[g.V()][g.V()];
		dfsStack.push(root);
		while (!dfsStack.isEmpty()) {
			int current = dfsStack.pop();
			println("Visited %d", current);
			path.push(current);
			println("Path = %s", path.toString());
			Iterable<Integer> adj = g.adj(current);
			int adjSize = Utils.size(adj);
			if (path.size() == g.V()) {
				println("Path found");
				break;
			} else if (adjSize == 0 && returnPoint != -1) {
				println("Popping until return point of %d", returnPoint);
				while (!path.isEmpty() && path.peek() != returnPoint) {
					path.pop();
				}
				println("Path after popping: %s", path.toString());
				returnPoint = -1;
			}
			if (adjSize > 1) {
				returnPoint = current;
				println("Setting return point to %d", returnPoint);
			}
			for (int v : adj) {
				if (!marked[current][v]) {
					marked[current][v] = true;
					dfsStack.push(v);
				}
			}
		}
		if (path.size() != g.V()) {
			return Collections.emptyList();
		}
		println("Hamiltonian path in a DAG:\n%s", path.toString());
		return path;
	}

}
