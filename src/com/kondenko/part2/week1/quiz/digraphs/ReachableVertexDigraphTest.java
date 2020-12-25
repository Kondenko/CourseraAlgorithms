package com.kondenko.part2.week1.quiz.digraphs;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Test;

import static com.kondenko.part2.week1.quiz.digraphs.ReachableVertexDigraph.CANT_BE_REACHED;
import static com.kondenko.part2.week1.quiz.digraphs.ReachableVertexDigraph.find;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ReachableVertexDigraphTest {

	@Test
	public void test1() {
		Digraph g = new Digraph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(1, 4);
		g.addEdge(4, 1);
		g.addEdge(5, 4);
		assertThat(find(g), anyOf(equalTo(1), equalTo(4)));
	}

	@Test
	public void test2() {
		Digraph g = new Digraph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(1, 4);
		g.addEdge(5, 4);
		g.addEdge(5, 1);
		assertThat(find(g), equalTo(CANT_BE_REACHED));
	}

}