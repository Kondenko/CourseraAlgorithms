package com.kondenko.part1.week2.quiz;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntersectionOfSetsTest {

    @Test
    public void shouldReturnTheSameNumberOfIntersections() {
        int length = 10;
        int expectedIntersections = 5;
        Point[] a = new Point[length];
        Point[] b = new Point[length];
        for (int i = 0; i < length - expectedIntersections; i++) {
            a[i] = Point.of(0, i);
            b[i] = Point.of(i, 0);
        }
        for (int i = length - expectedIntersections; i < length; i++) {
            a[i] = Point.of(i, i);
            b[i] = Point.of(i, i);
        }
        int actualIntersections = IntersectionOfSets.numberOfIntersections(a, b);
        assertEquals(expectedIntersections + 1 /* for [0,0] */, actualIntersections);
    }

    @Test
    public void shouldReturnTheSameNumberOfIntersectionsInLargeSet() {
        int length = 1_000_000;
        int expectedIntersections = 5;
        Point[] a = new Point[length];
        Point[] b = new Point[length];
        for (int i = 0; i < length - expectedIntersections; i++) {
            a[i] = Point.of(0, i);
            b[i] = Point.of(i, 0);
        }
        for (int i = length - expectedIntersections; i < length; i++) {
            a[i] = Point.of(i, i);
            b[i] = Point.of(i, i);
        }
        long executionTime = Utils.measureTime(() -> {
            int actualIntersections = IntersectionOfSets.numberOfIntersections(a, b);
            assertEquals(expectedIntersections + 1 /* for [0,0] */, actualIntersections);
        });
        StdOut.printf("Algorithm executed in %d ms", executionTime);
    }

    @Test
    public void shouldReturn0() {
        int length = 10;
        Point[] a = new Point[length];
        Point[] b = new Point[length];
        for (int i = 0, j = i + 1; i < length; i++) {
            a[i] = Point.of(0, j);
            b[i] = Point.of(j, 0);
        }
        int actualIntersections = IntersectionOfSets.numberOfIntersections(a, b);
        assertEquals(0, actualIntersections);
    }

}