package com.kondenko.part2.week1.assignment;

import org.junit.Test;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

public class WordNetTest {

	@Test
	public void shouldReturnTheCorrectNumberOfNouns() {
		WordNet wordNet = new WordNet("data/wordnet/synsets.txt", "data/wordnet/hypernyms.txt");
		assertEquals(119188, ((Collection<String>) wordNet.nouns()).size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForInvalidRootedGraph1() {
		WordNet wordNet = new WordNet("data/wordnet/synsets3.txt", "data/wordnet/hypernyms3InvalidTwoRoots.txt");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForInvalidRootedGraph2() {
		WordNet wordNet = new WordNet("data/wordnet/synsets6.txt", "data/wordnet/hypernyms6InvalidCycle.txt");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForInvalidRootedGraph3() {
		WordNet wordNet = new WordNet("data/wordnet/synsets6.txt", "data/wordnet/hypernyms6InvalidCycle+Path.txt");
	}

}