package com.kondenko.part2.week1.assignment;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class WordNetTest {

	@Test
	public void shouldReturnTheCorectNumberOfNouns() {
		WordNet wordNet = new WordNet("data/synsets.txt", "data/hypernyms.txt");
		assertEquals(119188, ((Collection<String>) wordNet.nouns()).size());
	}

}