package com.kondenko.part1.week3.quiz;

import com.kondenko.CompareUtils;
import edu.princeton.cs.algs4.StdRandom;

import static com.kondenko.ArrayUtils.swap;

public class NutsAndBolts {

    public static abstract class Item<T> implements Comparable<T> {
    }

    public static class Bolt extends Item<Nut> implements Comparable<Nut> {

        int value;

        public Bolt(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Nut nut) {
            return value - nut.value;
        }

    }

    public static class Nut extends Item<Bolt> implements Comparable<Bolt> {

        int value = 0;

        public Nut(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Bolt bolt) {
            return value - bolt.value;
        }

    }

    public static void findPairs(Bolt[] bolts, Nut[] nuts) {
        StdRandom.shuffle(bolts);
        StdRandom.shuffle(nuts);
        findPairs(bolts, nuts, 0, bolts.length - 1);
    }

    private static void findPairs(Bolt[] nuts, Nut[] bolts, int left, int right) {
        if (left < right) {
            int pivot = partition(nuts, left, right, bolts[right]);
            partition(bolts, left, right, nuts[pivot]);
            findPairs(nuts, bolts, left, pivot - 1);
            findPairs(nuts, bolts, pivot + 1, right);
        }
    }

    private static <T extends Comparable> int partition(T[] a, int left, int right, T pivot) {
        int i = left;
        for (int j = left; j < right; j++) {
            if (CompareUtils.lt(a[j], pivot)) {
                swap(a, i++, j);
            } else if (a[j].compareTo(pivot) == 0) {
                swap(a, j--, right);
            }
        }
        swap(a, i, right);
        return i;
    }

}
