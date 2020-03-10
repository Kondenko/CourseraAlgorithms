package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

import static com.kondenko.Utils.println;

/**
 * The task: Implement depth-first search in an undirected graph without using recursion.
 */
public class NonRecursiveDFS {

	public static void dfs(Graph g, int s) {
		println("Starting non-recursive DFS");
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[g.V()];
		stack.push(s);
		while (!stack.isEmpty()) {
			int current = stack.pop();
			if (!visited[current]) {
				visited[current] = true;
				println("Visited %d", current);
				for (int v : g.adj(current)) {
					stack.push(v);
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		int s = 0;
		dfs(g, s);
	}

}
