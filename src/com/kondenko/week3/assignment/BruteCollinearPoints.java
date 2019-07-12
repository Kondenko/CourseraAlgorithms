package com.kondenko.week3.assignment;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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

    private Stack<LineSegment> findLineSegments() {
        Stack<LineSegment> segments = new Stack<>();
        for (int i = 0; i < points.length - pointsNumber; i++) {
            int lastPointIndex = i + pointsNumber;
            boolean isLine = isLine(Arrays.copyOfRange(points, i, lastPointIndex));
            if (isLine) segments.push(new LineSegment(points[i], points[lastPointIndex]));
        }
        return segments;
    }

    private boolean isLine(Point[] points) {
        Point initial = points[0];
        double prevSlop = initial.slopeTo(points[1]);
        for (int i = 2; i < points.length; i++) {
            var currentSlope = initial.slopeTo(points[i]);
            if (currentSlope != prevSlop) return false;
            prevSlop = currentSlope;
        }
        return true;
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
