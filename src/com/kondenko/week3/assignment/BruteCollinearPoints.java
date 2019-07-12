package com.kondenko.week3.assignment;

import java.awt.Color;
import java.util.HashSet;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import static com.kondenko.ArrayUtils.areEqual;
import static com.kondenko.ArrayUtils.areUnique;

public class BruteCollinearPoints {

    private final int pointsNumber = 4;

    private final Point[] points;

    private Stack<LineSegment> segments = null;

    /**
     * finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
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
        this.points = points;
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
        if (segments == null) segments = findLineSegments();
        return segments.toArray(new LineSegment[0]);
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    private Stack<LineSegment> findLineSegments() {
        Stack<LineSegment> segments = new Stack<>();
        for (int p = 0; p < points.length; p++) {
            for (int q = 0; q < points.length; q++) {
                for (int r = 0; r < points.length; r++) {
                    for (int s = 0; s < points.length; s++) {
                        Point pp = points[p];
                        Point qq = points[q];
                        Point rr = points[r];
                        Point ss = points[s];
                        if (areUnique(pp, qq, rr, ss) && isLine(pp, qq, rr, ss)) {
                            segments.push(new LineSegment(pp, ss));
                        }
                    }
                }
            }
        }
        return segments;
    }

    private boolean isLine(Point p, Point q, Point r, Point s) {
        double pq = p.slopeTo(q);
        double pr = p.slopeTo(r);
        double ps = p.slopeTo(s);
        return areEqual(pq, pr, ps);
    }

    public static void main(String[] args) {
        In input = new In(args[0]);
        int size = input.readInt();
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }
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
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(Color.BLACK);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }

}
