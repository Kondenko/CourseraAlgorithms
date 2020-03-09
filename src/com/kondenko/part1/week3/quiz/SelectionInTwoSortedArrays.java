package com.kondenko.part1.week3.quiz;

public class SelectionInTwoSortedArrays {

    public static int select(Integer[] a, Integer[] b, int k) {
        int alargest = QuickSelect.select(a, Math.min(a.length, k));
        int blargest = QuickSelect.select(b, Math.min(b.length, k));
        return Math.max(alargest, blargest);
    }

}
