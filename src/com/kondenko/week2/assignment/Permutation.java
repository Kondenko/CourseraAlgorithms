package com.kondenko.week2.assignment;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
