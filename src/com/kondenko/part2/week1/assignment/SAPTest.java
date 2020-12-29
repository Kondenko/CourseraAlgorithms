package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SAPTest {

	@Test
	public void shouldHandleEmptyIterables() {
		Digraph G = new Digraph(3);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		SAP sap = new SAP(G);
		assertEquals(-1, sap.ancestor(Collections.emptyList(), Collections.emptyList()));
		assertEquals(0, sap.length(Collections.emptyList(), Collections.emptyList()));
	}

	@Test
	public void shouldFindACommonAncestor0() {
		Digraph G = new Digraph(3);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		SAP sap = new SAP(G);
		assertEquals(0, sap.ancestor(1, 2));
	}

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
		Digraph G = new Digraph(7);

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
		Digraph G = new Digraph(5);

		G.addEdge(1, 0);
		G.addEdge(2, 1);
		G.addEdge(3, 1);

		G.addEdge(4, 2);
		G.addEdge(4, 3);

		SAP sap = new SAP(G);
		assertEquals(3, sap.ancestor(3, 4));
	}

	@Test
	public void shouldFindACommonAncestor4() {
		Digraph G = new Digraph(4);

		G.addEdge(1, 0);

		G.addEdge(2, 1);
		G.addEdge(3, 1);

		SAP sap = new SAP(G);
		assertEquals(1, sap.ancestor(2, 3));
	}


	// TODO Fix length implementation

	@Test
	public void distanceBetweenTheSameVerticesShouldBeZero() {
		Digraph G = new Digraph(2);

		G.addEdge(1, 0);

		SAP sap = new SAP(G);
		assertEquals(0, sap.length(0, 0));
	}

	@Test
	public void distanceBetweenVertices1() {
		Digraph G = new Digraph(5);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		G.addEdge(3, 1);
		G.addEdge(4, 1);

		SAP sap = new SAP(G);
		assertEquals(2, sap.length(1, 2));
	}

	@Test
	public void distanceBetweenVertices2() {
		Digraph G = new Digraph(8);

		G.addEdge(2, 0);
		G.addEdge(1, 0);

		G.addEdge(3, 1);
		G.addEdge(4, 1);
		G.addEdge(5, 1);

		G.addEdge(6, 5);
		G.addEdge(7, 6);

		SAP sap = new SAP(G);
		assertEquals(4, sap.length(3, 7));
	}

	@Test
	public void fileTest1() {
		SAP sap = new SAP(create("digraph1.txt"));
		assertEquals(0, sap.length(6, 6));
	}

	@Test
	public void fileTest11() {
		SAP sap = new SAP(create("digraph1.txt"));
		assertEquals(6, sap.ancestor(6, 6));
	}

	@Test
	public void fileTest2() {
		SAP sap = new SAP(create("digraph1.txt"));
		assertEquals(-1, sap.length(2, 6));
	}

	@Test
	public void fileTest3() {
		SAP sap = new SAP(create("digraph2.txt"));
		assertEquals(2, sap.length(5, 1));
	}

	@Test
	public void fileTest4() {
		SAP sap = new SAP(create("digraph2.txt"));
		assertEquals(0, sap.length(1, 1));
	}


	@Test
	public void fileTest5() {
		SAP sap = new SAP(create("digraph2.txt"));
		assertEquals(0, sap.length(1, 1));
	}


	@Test
	public void fileTest6() {
		SAP sap = new SAP(create("digraph2.txt"));
		assertEquals(0, sap.length(1, 1));
	}


	private Digraph create(String fileName) {
		return new Digraph(new In("data/wordnet/" + fileName));
	}


}