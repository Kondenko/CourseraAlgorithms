package com.kondenko.part1.week2.quiz;

import static com.kondenko.Utils.println;

public class BitonicArraySearch {

    public static boolean find(int[] bitonicArray, int number) {
        int comparions = 0;
        int middle = bitonicArray.length / 2;
        while (middle >= 1) {
            int tail = bitonicArray[middle];
            int head = bitonicArray[bitonicArray.length - middle];
            comparions += 2;
            if (tail == number || head == number) {
                printComparisons(comparions, bitonicArray.length);
                return true;
            }
            middle--;
        }
        printComparisons(comparions, bitonicArray.length);
        return false;
    }

    private static void printComparisons(int comparisons, int n) {
        println("%d comparisons made for %d items", comparisons, n);
        println("Max allowed comparisons number is 2*lg(n) = %f", 2 * (Math.log(n) / Math.log(2)));
    }

}
