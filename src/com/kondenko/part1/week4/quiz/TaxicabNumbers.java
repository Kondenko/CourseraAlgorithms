package com.kondenko.part1.week4.quiz;

import com.kondenko.ArrayUtils;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class TaxicabNumbers {

    private static class SumOfCubes implements Comparable<SumOfCubes> {

        int a, b, sumOfCubes;

        public SumOfCubes(int a, int b, int sumOfCubes) {
            this.a = a;
            this.b = b;
            this.sumOfCubes = sumOfCubes;
        }

        @Override
        public int compareTo(SumOfCubes o) {
            return this.sumOfCubes - o.sumOfCubes;
        }

    }

    public static Integer[] find(int n) {
        PriorityQueue<Integer> taxicabNums = PriorityQueue.max();
        PriorityQueue<SumOfCubes> soc = PriorityQueue.max();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                soc.add(new SumOfCubes(i, j, sumOfCubes(i, j)));
            }
        }
        while (!soc.isEmpty()) {
            SumOfCubes sumOfCubes = soc.removeRoot();
            for (int a = 1; a < n; a++) {
                for (int b = 1; b < n; b++) {
                    if (isTaxicab(a, b, sumOfCubes)) {
                        int taxicabNum = sumOfCubes(a, b);
                        if (!taxicabNums.contains(taxicabNum)) taxicabNums.add(taxicabNum);
                    }
                }
            }
        }
        printResults(taxicabNums);
        return ArrayUtils.toArray(taxicabNums, new Integer[taxicabNums.size()]);
    }

    private static boolean isTaxicab(int a, int b, SumOfCubes sumOfCubes) {
        return a != sumOfCubes.a && a != sumOfCubes.b && b != sumOfCubes.a && b != sumOfCubes.b && sumOfCubes(a, b) == sumOfCubes.sumOfCubes;
    }

    private static int sumOfCubes(int a, int b) {
        return cube(a) + cube(b);
    }

    private static int cube(int i) {
        return i * i * i;
    }

    private static void printResults(PriorityQueue<Integer> taxicabs) {
        System.out.println("Taxicab numbers found:");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Iterator<Integer> i = taxicabs.iterator(); i.hasNext(); ) {
            sb.append(i.next());
            if (i.hasNext()) sb.append(", ");
        }
        sb.append("]");
        StdOut.println(sb.toString());
    }

}
