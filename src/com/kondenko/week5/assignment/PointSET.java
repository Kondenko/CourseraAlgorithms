package com.kondenko.week5.assignment;

import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    private Set<Point2D> points = new TreeSet<>();

    /**
     * construct an empty set of points
     */
    public PointSET() {
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        points.add(p);
    }

    /**
     * does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        return points.contains(p);
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect is null");
        return null;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        return null;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        points.forEach(Point2D::draw);
    }

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * number of points in the set
     */
    public int size() {
        return points.size();
    }


    /**
     * unit testing of the methods (optional)
     */
    public static void main(String[] args) {

    }

}