package com.kondenko.week3.quiz;

import edu.princeton.cs.algs4.StdOut;

public class Inversions {

    public static int count(int[] a) {
        int complexity = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                complexity++;
                if (isInversion(a, i, j)) count++;
            }
        }
        StdOut.println(String.format(
                "Desired complexity: %d log(%.1f) = %d",
                a.length,
                Math.log(a.length) / Math.log(2),
                desiredComplexity(a.length)
        ));
        StdOut.println("Actual complexity: " + complexity);
        return count;
    }

    private static long desiredComplexity(int n) {
        return Math.round(n * Math.log(n) / Math.log(2));
    }

    private static boolean isInversion(int[] a, int i, int j) {
        return i < j && a[i] > a[j];
    }

}
