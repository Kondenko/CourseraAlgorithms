package com.kondenko.part1.week2.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext() && --k >= 0) {
            StdOut.println(iterator.next());
        }
    }

}
