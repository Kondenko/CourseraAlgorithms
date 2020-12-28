package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private final WordNet wordNet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordNet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		String outcast = null;
		int maxSum = 0;
		for (String noun : nouns) {
			int sum = 0;
			for (String otherNoun : nouns) {
				int distance = wordNet.distance(noun, otherNoun);
				// println("Distance between %s and %s = %d", noun, otherNoun, distance);
				sum += distance;
			}
			// println("Sum for %s = %d", noun, sum);
			if (sum > maxSum) {
				// println("%s is the new outcast", noun);
				outcast = noun;
				maxSum = sum;
			}
		}
		return outcast;
	}

	// see test client below
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}