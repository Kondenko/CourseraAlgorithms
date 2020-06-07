package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.kondenko.Utils.println;

public class ShortestDirectedCycle {

	public static void find(Digraph g) {
		List<Integer> shortestCycle = findCycle(g);
		println("\nThe shortest cycle in G is %s", shortestCycle);
	}

	private static List<Integer> findCycle(Digraph g) {
		List<Stack<Integer>> graphPaths = new ArrayList<>(g.V());
		for (int i = 0; i < g.V(); i++) {
			graphPaths.add(new Stack<>());
		}
		for (int i = 0; i < g.V(); i++) {
			List<Stack<Integer>> paths = new ArrayList<>(g.V());
			for (int j = 0; j < g.V(); j++) {
				paths.add(new Stack<>());
			}
			dfs(g, new boolean[g.V()][g.V()], i, i, paths);
			graphPaths.set(i, paths.get(i));
		}
		List<Integer> shortestCycle = null;
		for (Stack<Integer> path : graphPaths) {
			List<Integer> cycle = findCycle(path);
			if (shortestCycle == null || (cycle != null && cycle.size() > 1 && cycle.size() < shortestCycle.size())) {
				shortestCycle = cycle;
			}
		}
		return shortestCycle;
	}

	// Maybe this can be avoided by returning from dfs and discarding vertices adjacent to root
	private static List<Integer> findCycle(List<Integer> path) {
		for (int i = 0; i < path.size(); i++) {
			for (int j = i + 1; j < path.size(); j++) {
				if (path.get(i).equals(path.get(j))) {
					return path.subList(i, j + 1);
				}
			}
		}
		return null;
	}

	private static void dfs(Digraph g, boolean[][] marked, int root, int current, List<Stack<Integer>> path) {
		Iterable<Integer> adj = g.adj(current);
		path.get(root).push(current);
		for (int v : adj) {
			if (!marked[current][v]) {
				marked[current][v] = true;
				dfs(g, marked, root, v, path);
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
