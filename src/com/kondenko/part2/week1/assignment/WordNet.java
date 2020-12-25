package com.kondenko.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;
import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.kondenko.Utils.println;

public class WordNet {

	private String[][] synsets;

	private Set<Integer>[] hypernyms;

	private Digraph wordnet;

	// constructor takes the name of the two input files
	public WordNet(String synsetsFile, String hypernymsFile) {
		if (synsetsFile == null) throw new IllegalArgumentException("Synset file name is null");
		if (hypernymsFile == null) throw new IllegalArgumentException("Hypernym file name is null");
		try {
			// Read synsets
			List<List<String>> synsetRecords = getRecords(synsetsFile);
			println("Found %d synsets", synsetRecords.size());
			synsets = new String[synsetRecords.size()][];
			for (List<String> synsetRecord : synsetRecords) {
				int id = Integer.parseInt(synsetRecord.get(0));
				String synsetsList = synsetRecord.get(1);
				synsets[id] = synsetsList.split(" ");
			}

			// Read hypernyms
			List<List<String>> hypernymRecords = getRecords(hypernymsFile);
			println("Found %d hypernyms", hypernymRecords.size());
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
			if (!rootFound) throw new IllegalArgumentException("The input to the constructor does not correspond to a rooted DAG");

			// Construct WordNet
			wordnet = new Digraph(synsets.length);
			for (int synset = 0; synset < hypernyms.length; synset++) {
				for (Integer hypernym : hypernyms[synset]) {
					wordnet.addEdge(synset, hypernym);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		List<String> nouns = new ArrayList<>();
		for (String[] synsets : synsets) {
			Collections.addAll(nouns, synsets);
		}
		return nouns;
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		throw new NotImplementedError();
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		throw new NotImplementedError();
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		throw new NotImplementedError();
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wn = new WordNet("data/synsets.txt", "data/hypernyms.txt");
		println(wn.nouns().toString());
	}

	private static List<List<String>> getRecords(String file) throws FileNotFoundException {
		List<List<String>> recods = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(file))) {
			while (scanner.hasNextLine()) {
				recods.add(getRecordsFromLine(scanner.nextLine()));
			}
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