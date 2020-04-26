package com.kondenko.part2.week1.quiz.graphs;

import edu.princeton.cs.algs4.Graph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiameterOfTreeTest {

	@Test
	public void diameter1() {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		assertEquals(5, DiameterOfTree.diameter(g));
	}

	@Test
	public void diameter2() {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		assertEquals(2, DiameterOfTree.diameter(g));
	}

	@Test
	public void diameter3() {
		Graph g = new Graph(10);
		g.addEdge(0, 1);

		g.addEdge(1, 3);
		g.addEdge(1, 5);

		g.addEdge(3, 4);

		g.addEdge(0, 2);

		g.addEdge(2, 6);
		g.addEdge(2, 8);

		g.addEdge(6, 7);
		g.addEdge(7, 9);

		assertEquals(8, DiameterOfTree.diameter(g));
	}

}