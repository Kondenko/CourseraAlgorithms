package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Test;

import static com.kondenko.part2.week1.quiz.digraphs.ReachableVertexDAG.CANT_BE_REACHED;
import static com.kondenko.part2.week1.quiz.digraphs.ReachableVertexDAG.find;
import static org.junit.Assert.assertEquals;

public class ReachableVertexDAGTest {

	@Test
	public void test1() {
		Digraph g = new Digraph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		assertEquals(2, find(g));
	}

	@Test
	public void test2() {
		Digraph g = new Digraph(3);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		assertEquals(CANT_BE_REACHED, find(g));
	}

	@Test
	public void test3() {
		Digraph g = new Digraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(3, 2);
		g.addEdge(4, 2);
		assertEquals(2, find(g));
	}

}