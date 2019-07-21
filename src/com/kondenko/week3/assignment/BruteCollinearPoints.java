package com.kondenko.week3.assignment;

import java.awt.Color;
import java.util.Stack;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private final Point[] points;

    private Stack<LineSegment> segments;

    /**
     * finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }
        if (!areUnique(true, points)) {
            throw new IllegalArgumentException("The array contains a repeating point");
        }
        this.points = points;
        this.segments = findLineSegments();
    }

    /**
     * the number of line segments
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /**
     * the line segments
     */
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private Stack<LineSegment> findLineSegments() {
        Stack<LineSegment> segments = new Stack<>();
        for (int ip = 0; ip < points.length; ip++) {
            for (int iq = 0; iq < points.length; iq++) {
                for (int ir = 0; ir < points.length; ir++) {
                    for (int is = 0; is < points.length; is++) {
                        Point p = points[ip];
                        Point q = points[iq];
                        Point r = points[ir];
                        Point s = points[is];
                        boolean areAscending =
                                areAscending(p, q) &&
                                        areAscending(q, r) &&
                                        areAscending(r, s);
                        if (areAscending && areUnique(false, p, q, r, s) && isLine(p, q, r, s)) {
                            segments.push(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
        return segments;
    }

    private boolean areAscending(Point a, Point b) {
        return a.compareTo(b) >= 0;
    }

    private boolean isLine(Point p, Point q, Point r, Point s) {
        double pq = p.slopeTo(q);
        double pr = p.slopeTo(r);
        double ps = p.slopeTo(s);
        return areEqual(pq, pr, ps);
    }

    private <T> boolean areEqual(T... values) {
        T initial = values[0];
        for (int i = 1; i < values.length; i++) {
            if (!initial.equals(values[i])) return false;
        }
        return true;
    }

    private boolean areUnique(boolean checkForNulls, Point... array) {
        for (int i = 0; i < array.length; i++) {
            if (checkForNulls) throwIfNull(array[i]);
            for (int j = i + 1; j < array.length; j++) {
                if (checkForNulls) throwIfNull(array[j]);
                if (equal(array[i], array[j])) return false;
            }
        }
        return true;
    }

    private void throwIfNull(Point point) {
        if (point == null) throw new IllegalArgumentException("A point in the array is null");
    }

    private boolean equal(Point a, Point b) {
        return a.compareTo(b) == 0;
    }

    public static void main(String[] args) {
        String file = "/Users/vladimirkondenko/IdeaProjects/AlgoritmsPrincetonCoursera/collinear/input20.txt";
        Point[] points = PointsFactory.fromFile(file);
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

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
        StdOut.printf("\n%d segments found", collinear.numberOfSegments());
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(Color.BLACK);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }

}
