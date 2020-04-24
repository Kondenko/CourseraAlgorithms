package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;

import java.util.*;
import java.util.stream.StreamSupport;

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
		printEdges(vertices);
		return vertices;
	}

	private static void euler(Graph g, Set<Integer>[] visited, Stack<Integer> vertices, int root, int prev, int current) {
		Deque<Integer> next = new ArrayDeque<>();
		visited[prev].add(current);
		vertices.add(current);
		for (int v : g.adj(current)) {
			if (!visited[v].contains(current)) {
				int numberOfUnvisitedEdges = g.degree(v) - visited[v].size();
				if (next.contains(v)) {
					next.addFirst(current);
					next.addFirst(v);
				} else if (numberOfUnvisitedEdges >= 2) {
					next.addFirst(v);
				} else {
					next.addLast(v);
				}
			}
		}
		for (Integer v : next) {
			if (vertices.size() == g.E() + 1) {
				if (v == root) vertices.add(v);
				return;
			} else {
				visited[v].add(current);
				euler(g, visited, vertices, root, current, v);
			}
		}
	}

	public static boolean hasEulerCycle(Graph g) {
		for (int i = 0; i < g.V(); i++) {
			if (g.degree(i) % 2 != 0) return false;
		}
		return true;
	}

	private static void printEdges(Iterable<Integer> edges) {
		Optional<String> chain = StreamSupport.stream(edges.spliterator(), false)
				.map(String::valueOf)
				.reduce((a, b) -> a + " - " + b);
		println("Euler's cycle goes through " + chain.orElse("nothing"));
	}

}
