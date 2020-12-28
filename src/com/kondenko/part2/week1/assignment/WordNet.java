package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordNet {

	private Set<Integer>[] hypernyms;

	private String[] synsets;

	private Set<String>[] nouns;

	private Digraph wordNet;

	private SAP shortestAncestralPath;

	// constructor takes the name of the two input files
	public WordNet(String synsetsFile, String hypernymsFile) {
		if (synsetsFile == null) throw new IllegalArgumentException("Synset file name is null");
		if (hypernymsFile == null) throw new IllegalArgumentException("Hypernym file name is null");
		// Read synsets
		List<List<String>> synsetRecords = getRecords(synsetsFile);
		synsets = new String[synsetRecords.size()];
		nouns = new HashSet[synsetRecords.size()];
		for (List<String> synsetRecord : synsetRecords) {
			int id = Integer.parseInt(synsetRecord.get(0));
			String synset = synsetRecord.get(1);
			synsets[id] = synset;
			nouns[id] = new HashSet<>();
			for (String noun : synset.split(" ")) {
				nouns[id].add(noun);
			}
		}

		// Read hypernyms
		List<List<String>> hypernymRecords = getRecords(hypernymsFile);
		hypernyms = new HashSet[hypernymRecords.size()];
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
		wordNet = new Digraph(synsets.length);
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
		List<String> nounsList = new ArrayList<>();
		for (Set<String> nounsSet : nouns) {
			nounsList.addAll(nounsSet);
		}
		return nounsList;
	}

	// is the word a WordNet noun?Us
	public boolean isNoun(String word) {
		for (Set<String> nounsSet : nouns) {
			if (nounsSet.contains(word)) return true;
		}
		return false;
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
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nouns.length; i++) {
			if (nouns[i].contains(noun)) {
				list.add(i);
			}
		}
		return list;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wn = new WordNet("data/synsets.txt", "data/hypernyms.txt");
	}

	private static List<List<String>> getRecords(String file) {
		List<List<String>> recods = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(file))) {
			while (scanner.hasNextLine()) {
				recods.add(getRecordsFromLine(scanner.nextLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recods;
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