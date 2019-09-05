package com.kondenko.week5.assignment;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import kotlin.NotImplementedError;

public class KdTree {

    /**
     * construct an empty set of points
     */
    public KdTree() {
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        throw new NotImplementedError();
    }

    /**
     * does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        throw new NotImplementedError();
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
        if (isEmpty()) return null;
        throw new NotImplementedError();
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        throw new NotImplementedError();
    }

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        throw new NotImplementedError();
    }

    /**
     * number of points in the set
     */
    public int size() {
        throw new NotImplementedError();
    }


    /**
     * unit testing of the methods (optional)
     */
    public static void main(String[] args) {

    }

}