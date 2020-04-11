package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;

import java.util.*;

import static com.kondenko.Utils.println;

public class EulerCycle {

	public static List<Integer> findEulerCycle(Graph g) {
		if (!hasEulerCycle(g)) {
			throw new IllegalArgumentException("This graph doesn't have a Euler cycle (a vertex with an odd degree was found)");
		}
		HashSet<Integer>[] visited = (HashSet<Integer>[]) new HashSet[g.V()];
		Stack<Integer> vertices = new Stack<>();
		for (int i = 0; i < visited.length; i++) {
			visited[i] = new HashSet<>();
		}
		euler(g, visited, vertices, 0, 0, 0);
		println("Euler's cycle goes through %s", vertices.toString());
		return vertices;
	}

	private static void euler(Graph g, Set<Integer>[] visited, Stack<Integer> vertices, int root, int prev, int current) {
		Deque<Integer> next = new ArrayDeque<>();
		visited[prev].add(current);
		vertices.add(current);
		for (int v : g.adj(current)) {
			if (!visited[v].contains(current)) {
				int numberOfUnvisitedEdges = g.degree(v) - visited[v].size();
				if (numberOfUnvisitedEdges >= 2) next.addFirst(v);
				else next.addLast(v);
			}
		}
		for (Integer v : next) {
			if (vertices.size() <= 1 || !vertices.firstElement().equals(vertices.lastElement())) {
				if (v == root && !vertices.isEmpty()) {
					vertices.add(v);
					return;
				} else {
					visited[v].add(current);
					euler(g, visited, vertices, root, current, v);
				}
			}
		}
	}

	public static boolean hasEulerCycle(Graph g) {
		for (int i = 0; i < g.V(); i++) {
			if (g.degree(i) % 2 != 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 4);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 5);
		g.addEdge(3, 5); // parallel edge
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		List<Integer> eulerCycle = findEulerCycle(g);
		assert eulerCycle.get(0).equals(eulerCycle.get(eulerCycle.size() - 1));
	}

}
