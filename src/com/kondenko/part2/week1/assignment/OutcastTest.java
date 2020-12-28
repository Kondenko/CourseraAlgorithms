package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OutcastTest {

	WordNet wordNet = new WordNet("data/synsets.txt", "data/hypernyms.txt");

	@Test
	public void outcast5() {
		Outcast outcast = new Outcast(wordNet);
		assertEquals("table", outcast.outcast(getNounsInFile("data/outcast5.txt")));
	}

	@Test
	public void outcast8() {
		Outcast outcast = new Outcast(wordNet);
		assertEquals("bed", outcast.outcast(getNounsInFile("data/outcast8.txt")));
	}

	@Test
	public void outcast11() {
		Outcast outcast = new Outcast(wordNet);
		assertEquals("potato", outcast.outcast(getNounsInFile("data/outcast11.txt")));
	}

	private String[] getNounsInFile(String fileName) {
		In in = new In(fileName);
		return in.readAllStrings();
	}

}