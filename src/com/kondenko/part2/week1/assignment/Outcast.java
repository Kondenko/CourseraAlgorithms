package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.Utils.println;

public class Outcast {

	private WordNet wordNet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordNet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		String outcast = null;
		int maxDistance = -1;
		for (int i = 0; i < nouns.length; i++) {
			for (int j = i + 1; j < nouns.length; j++) {
				int distance = wordNet.distance(nouns[i], nouns[j]);
				String ancestor = wordNet.sap(nouns[i], nouns[j]);
				println("%s - %s = %d (ancestor is %s)", nouns[i], nouns[j], distance, ancestor);
				if (distance > maxDistance) {
					maxDistance = distance;
					outcast = nouns[j];
					println("New outcast: %s (%d)", outcast, maxDistance);
				}
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