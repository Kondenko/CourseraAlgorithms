package com.kondenko.week3;

public class SelectionInTwoSortedArrays {

    public static int select(Integer[] a, Integer[] b, int k) {
        int alargest = QuickSelect.select(a, Math.min(a.length, k));
        int blargest = QuickSelect.select(b, Math.min(b.length, k));
        return Math.max(alargest, blargest);
    }

}
