package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EulerCycleTest {

	@Test
	public void hasEulerCycle() {
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		g.addEdge(0, 1);
		assertTrue(EulerCycle.hasEulerCycle(g));
	}

	@Test
	public void hasNoEulerCycle() {
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		g.addEdge(0, 1);
		g.addEdge(0, 1);
		assertFalse(EulerCycle.hasEulerCycle(g));
	}

}