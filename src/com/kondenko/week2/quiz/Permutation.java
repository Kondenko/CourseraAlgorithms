package com.kondenko.week2.quiz;

public class Permutation {

    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) return false;
        int intersections = 0;
        ShellSort.sort(b);
        for (int i : a) {
            int index = BinarySearch.search(i, b);
            if (index != -1) intersections++;
        }
        return intersections == b.length;
    }

}
