package com.kondenko.week3.assignment;


import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.ArrayUtils.isSorted;
import static com.kondenko.CompareUtils.lt;
import static edu.princeton.cs.algs4.StdOut.printf;

public class FastCollinearPoints {

    private final int minPointsInSegment = 3;

    private Stack<LineSegment> segments;

    /**
     * finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }
        var pointSet = new HashSet<Point>();
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("A point in the array is null");
            pointSet.add(point);
        }
        if (pointSet.size() != points.length) {
            throw new IllegalArgumentException("The array contains a repeating point");
        }
        this.segments = findSegments(points);
    }

    /**
     * the number of line segments
     */
    public int numberOfSegments() {
        return segments.size();
    }

    private Stack<LineSegment> findSegments(Point[] points) {
        Stack<LineSegment> segments = new Stack<>();
        for (Point point : points) {
            Point[] sorted = Mergesort.sort(points, point.slopeOrder());
            int firstPointInSegment = 0;
            int lastPointInSegment = -1;
            double prevSlope = Double.NaN;
            for (int i = 0; i < sorted.length; i++) {
                double slope = point.slopeTo(sorted[i]);
                if (Double.isNaN(prevSlope) || slope == prevSlope) {
                    lastPointInSegment = i;
                }
                prevSlope = slope;
            }
            if (!Double.isNaN(prevSlope) && lastPointInSegment >= minPointsInSegment) {
                segments.push(new LineSegment(sorted[firstPointInSegment], sorted[lastPointInSegment]));
            }
        }
        return segments;
    }

    /**
     * the line segments
     */
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        String file = "/Users/vladimirkondenko/IdeaProjects/AlgoritmsPrincetonCoursera/collinear/input10.txt";
        Point[] points = PointsFactory.fromFile(file);
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        // print and draw the line segments
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.01);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        printf("\n%d segments found", collinear.numberOfSegments());
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(Color.BLACK);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }

    private static class Mergesort {

        public static <T> T[] sort(T[] array, Comparator<T> comparator) {
            return sortBottomUp(array, comparator);
        }

        private static <T> T[] sortBottomUp(T[] array, Comparator<T> comparator) {
            @SuppressWarnings("unchecked")
            T[] arr = Arrays.copyOf(array, array.length);
            T[] aux = (T[]) new Object[arr.length];
            for (int size = 1; size < arr.length; size *= 2) {
                for (int lo = 0; lo < arr.length - size; lo += size * 2) {
                    int mid = lo + size - 1;
                    int hi = Math.min(lo + size * 2 - 1, arr.length - 1);
                    merge(arr, aux, lo, mid, hi, comparator);
                }
            }
            return arr;
        }

        protected static <T> void merge(T[] arr, T[] aux, final int lo, int mid, final int hi, Comparator<T> comparator) {
            assert isSorted(arr, lo, mid, comparator);
            assert isSorted(arr, mid + 1, hi, comparator);
            System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                int indexAux;
                if (i > mid) indexAux = j++;
                else if (j > hi) indexAux = i++;
                else if (lt(aux, j, i, comparator)) indexAux = j++;
                else indexAux = i++;
                arr[k] = aux[indexAux];
            }
            assert isSorted(arr, lo, hi, comparator);
        }

    }

}
