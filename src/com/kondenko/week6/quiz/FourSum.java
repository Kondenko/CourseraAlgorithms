package com.kondenko.week6.quiz;

import kotlin.Pair;

public class FourSum {

    public static boolean hasFourSum(int[] arr) {
        SeparateChainingHashTable<Integer, Pair<Integer, Integer>> sums = new SeparateChainingHashTable<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    sums.put(arr[i] + arr[j], new Pair<>(arr[i], arr[j]));
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                Pair<Integer, Integer> pair = sums.get(sum);
                if (i != j && pair != null && !in(arr[i], arr[j], pair)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean in(int a, int b, Pair<Integer, Integer> pair) {
        return pair.getFirst() == a && pair.getSecond() == b || pair.getFirst() == b && pair.getSecond() == a;
    }

}
