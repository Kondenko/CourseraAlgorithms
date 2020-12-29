package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordNet {

	private final String[] synsets;

	private final HashMap<String, List<Integer>> nouns;

	private final SAP shortestAncestralPath;

	// constructor takes the name of the two input files
	public WordNet(String synsetsFile, String hypernymsFile) {
		if (synsetsFile == null) throw new IllegalArgumentException("Synset file name is null");
		if (hypernymsFile == null) throw new IllegalArgumentException("Hypernym file name is null");
		// Read synsets
		List<List<String>> synsetRecords = getRecords(synsetsFile);
		synsets = new String[synsetRecords.size()];
		nouns = new HashMap<>();
		for (List<String> synsetRecord : synsetRecords) {
			int synsetId = Integer.parseInt(synsetRecord.get(0));
			String synset = synsetRecord.get(1);
			synsets[synsetId] = synset;
			for (String noun : synset.split(" ")) {
				List<Integer> synsetsForNoun = nouns.getOrDefault(noun, new ArrayList<>());
				synsetsForNoun.add(synsetId);
				nouns.put(noun, synsetsForNoun);
			}
		}

		// Read hypernyms
		List<List<String>> hypernymRecords = getRecords(hypernymsFile);
		Set<Integer>[] hypernyms = new HashSet[hypernymRecords.size()];
		boolean rootFound = false;
		for (List<String> hypernymRecord : hypernymRecords) {
			int id = Integer.parseInt(hypernymRecord.get(0));
			Set<Integer> hypernymsSet = new HashSet<>();
			if (hypernymRecord.size() == 1) {
				rootFound = true;
			}
			for (int i = 1; i < hypernymRecord.size(); i++) {
				hypernymsSet.add(Integer.parseInt(hypernymRecord.get(i)));
			}
			hypernyms[id] = hypernymsSet;
		}
		if (!rootFound) {
			throw new IllegalArgumentException("The input to the constructor does not correspond to a rooted DAG");
		}

		// Construct WordNet
		Digraph wordNet = new Digraph(synsets.length);
		for (int synset = 0; synset < synsets.length; synset++) {
			for (Integer hypernym : hypernyms[synset]) {
				wordNet.addEdge(synset, hypernym);
			}
		}

		// Construct SAP
		shortestAncestralPath = new SAP(wordNet);
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return nouns.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return nouns.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		Iterable<Integer> synsetsA = synsetsContainingNoun(nounA);
		Iterable<Integer> synsetsB = synsetsContainingNoun(nounB);
		return shortestAncestralPath.length(synsetsA, synsetsB);
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		List<Integer> synsetsA = synsetsContainingNoun(nounA);
		List<Integer> synsetsB = synsetsContainingNoun(nounB);
		int ancestorSynsetId = shortestAncestralPath.ancestor(synsetsA, synsetsB);
		return synsets[ancestorSynsetId];
	}

	private List<Integer> synsetsContainingNoun(String noun) {
		return nouns.get(noun);
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wn = new WordNet("data/synsets.txt", "data/hypernyms.txt");
		StdOut.println(wn.isNoun("horse"));
	}

	private static List<List<String>> getRecords(String fileName) {
		List<List<String>> records = new ArrayList<>();
		In in = new In(fileName);
		while (in.hasNextLine()) {
			records.add(getRecordsFromLine(in.readLine()));
		}
		return records;
	}

	private static List<String> getRecordsFromLine(String line) {
		List<String> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(line)) {
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				records.add(scanner.next());
			}
		}
		return records;
	}

}