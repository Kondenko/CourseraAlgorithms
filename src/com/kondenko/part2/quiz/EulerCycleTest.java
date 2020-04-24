package com.kondenko.part2.quiz;

import edu.princeton.cs.algs4.Graph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EulerCycleTest {

	@Test
	public void eulerPath1() {
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
		assertCycle(g, EulerCycle.findEulerCycle(g));
	}

	@Test
	public void eulerPath2() {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		assertCycle(g, EulerCycle.findEulerCycle(g));
	}

	@Test
	public void eulerPath3() {
		// TODO Draw the graph and debug step-by-step
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(4, 2);
		g.addEdge(0, 5);
		g.addEdge(5, 2);
		assertCycle(g, EulerCycle.findEulerCycle(g));
	}

	private void assertCycle(Graph g, List<Integer> eulerVertices) {
		assertEquals(eulerVertices.size(), g.E() + 1);
		assertEquals(eulerVertices.get(0), eulerVertices.get(eulerVertices.size() - 1));
	}

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