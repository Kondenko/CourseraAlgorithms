package com.kondenko.part2.week1.quiz.digraphs;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.Digraph;

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
			if (path.size() == g.V()) {
				break;
			} else if (returnPoint != -1) {
				while (!path.isEmpty() && path.peek() != returnPoint) {
					path.pop();
				}
				returnPoint = -1;
			}
			Iterable<Integer> adj = g.adj(current);
			if (Utils.size(adj) > 1) returnPoint = current;
			for (int v : adj) {
				if (!marked[current][v]) {
					marked[current][v] = true;
					dfsStack.push(v);
				}
			}
		}
		if (path.size() != g.V()) {
			while(!path.isEmpty()) path.pop();
		}
		println("Hamiltonian path in a DAG:\n%s", path.toString());
		return path;
	}

}
