package com.kondenko.part2.week1.quiz.graphs;

import edu.princeton.cs.algs4.Graph;
import org.junit.Test;

import static org.hamcrest.collection.IsIn.isOneOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CenterOfTreeTest {

	@Test(timeout = 100)
	public void centerOdd() {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		assertEquals(1, CenterOfTree.center(g));
	}

	@Test(timeout = 100)
	public void centerEven() {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		assertThat(CenterOfTree.center(g), isOneOf(1, 2));
	}

	@Test(timeout = 100)
	public void center1() {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(4, 1);
		g.addEdge(4, 2);
		g.addEdge(4, 3);
		assertEquals(4, CenterOfTree.center(g));
	}

}