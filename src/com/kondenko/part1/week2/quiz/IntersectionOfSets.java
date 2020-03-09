package com.kondenko.part1.week2.quiz;

public class IntersectionOfSets {

    public static int numberOfIntersections(Point[] a, Point[] b) {
        if (a.length == 0 || b.length == 0) return 0;
        int intersections = 0;
        ShellSort.sort(b);
        for (Point p : a) {
            int index = BinarySearch.search(p, b);
            if (index != -1) intersections++;
        }
        return intersections;
    }

}
