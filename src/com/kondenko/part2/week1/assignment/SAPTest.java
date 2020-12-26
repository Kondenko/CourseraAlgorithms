package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SAPTest {

	@Test
	public void shouldFindACommonAncestor1() {
		Digraph G = new Digraph(8);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		G.addEdge(3, 1);
		G.addEdge(4, 1);
		G.addEdge(5, 1);

		G.addEdge(6, 5);
		G.addEdge(7, 6);

		SAP sap = new SAP(G);
		assertEquals(1, sap.ancestor(3, 7));
	}

	@Test
	public void shouldFindACommonAncestor2() {
		Digraph G = new Digraph(8);

		G.addEdge(1, 0);
		G.addEdge(2, 0);
		G.addEdge(3, 0);

		G.addEdge(4, 2);
		G.addEdge(4, 3);

		G.addEdge(5, 3);

		G.addEdge(6, 5);
		G.addEdge(6, 2);


		SAP sap = new SAP(G);
		assertEquals(2, sap.ancestor(4, 6));
	}

	@Test
	public void shouldFindACommonAncestor3() {
		Digraph G = new Digraph(8);

		G.addEdge(1, 0);
		G.addEdge(2, 1);
		G.addEdge(3, 1);

		G.addEdge(4, 2);
		G.addEdge(4, 3);

		SAP sap = new SAP(G);
		assertEquals(1, sap.ancestor(3, 4));
	}

}