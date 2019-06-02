package com.kondenko.week3.quiz;


import java.util.LinkedList;
import java.util.Random;


public class LinkedListShuffle {

    public static <T> void shuffle(LinkedList<T> list) {
        shuffle(list, new Random(), 0, list.size() - 1);
    }

    private static <T> void shuffle(LinkedList<T> list, Random random, int lo, int hi) {
        if (lo < hi) {
            int mid = (hi + lo) / 2;
            shuffle(list, random, lo, mid);
            shuffle(list, random, mid + 1, hi);
            if (random.nextBoolean()) {
                list.push(list.pollLast());
            }
        }
    }

}
