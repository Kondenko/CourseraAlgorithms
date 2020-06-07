package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HamiltonianPathInDagTest {

	@Test
	public void shouldNotBeFound1() {
		Digraph g = new Digraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 4);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		test(g, Collections.emptyList());
	}

	@Test
	public void shouldBeFound1() {
		Digraph g = new Digraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 4);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		test(g, List.of(0, 1, 2, 3, 4));
	}

	@Test
	public void shouldBeFound2() {
		Digraph g = new Digraph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		test(g, List.of(0, 1, 2, 3));
	}

	private void test(Digraph g, Iterable<Integer> expected) {
		Iterable<Integer> path = HamiltonianPathInDag.find(g);
		assertEquals(expected, path);
	}

}