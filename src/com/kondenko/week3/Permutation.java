package com.kondenko.week3;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = StdIn.readString();
        }
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (String s : input) {
            queue.enqueue(s);
        }
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext() && --k >= 0) {
            StdOut.println(iterator.next());
        }
    }

}
