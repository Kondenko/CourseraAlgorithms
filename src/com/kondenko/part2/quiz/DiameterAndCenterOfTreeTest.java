package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiameterAndCenterOfTreeTest {

	// @Test
	public void center1() {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		assertEquals(1, DiameterAndCenterOfTree.center(g));
	}

	@Test
	public void diameter1() {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		assertEquals(5, DiameterAndCenterOfTree.diameter(g));
	}

	@Test
	public void diameter2() {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		assertEquals(2, DiameterAndCenterOfTree.diameter(g));
	}

	@Test
	public void diameter3() {
		Graph g = new Graph(9);
		g.addEdge(0, 1);

		g.addEdge(1, 3);
		g.addEdge(1, 5);

		g.addEdge(3, 4);

		g.addEdge(0, 2);

		g.addEdge(2, 6);
		g.addEdge(2, 8);

		g.addEdge(6, 7);

		assertEquals(7, DiameterAndCenterOfTree.diameter(g));
	}

}