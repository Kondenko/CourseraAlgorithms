package com.kondenko.week3.assignment;


import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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
            Point[] sorted = Arrays.copyOf(points, points.length);
            Arrays.sort(sorted, point.slopeOrder());
            int firstPointInSegment = -1;
            int lastPointInSegment = -1;
            double prevSlope = point.slopeTo(point);
            for (int i = 0; i < sorted.length; i++) {
                double slope = point.slopeTo(sorted[i]);
                if (prevSlope == slope) {
                    if (firstPointInSegment == -1) firstPointInSegment = i - 1;
                    lastPointInSegment = i;
                }
                if (prevSlope != slope || i == sorted.length - 1) {
                    boolean hasMinPoints = lastPointInSegment - firstPointInSegment >= minPointsInSegment - 1;
                    if (hasMinPoints) {
                        segments.push(new LineSegment(sorted[firstPointInSegment], sorted[lastPointInSegment]));
                    }
                    firstPointInSegment = -1;
                    lastPointInSegment = -1;
                }
                prevSlope = slope;
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

}
