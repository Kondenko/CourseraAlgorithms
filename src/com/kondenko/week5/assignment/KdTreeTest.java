package com.kondenko.week5.assignment;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KdTreeTest {

    @Test
    public void size() {
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0, 1);
        assertEquals(0, kdTree.size());
        kdTree.insert(point);
        assertTrue(kdTree.contains(point));
        assertEquals(1, kdTree.size());
        kdTree.insert(point);
        assertEquals(1, kdTree.size());
    }

    @Test
    public void size1() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 1));
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(0, 0));
        assertEquals(2, kdTree.size());
    }

    @Test
    public void size2() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.8125, 0.5625));
        kdTree.insert(new Point2D(0.875, 0.0625));
        kdTree.insert(new Point2D(0.375, 0.625));
        kdTree.insert(new Point2D(0.9375, 0.0625));
        assertEquals(4, kdTree.size());
    }

    @Test
    public void size3() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.0, 0.125));
        kdTree.insert(new Point2D(0.0, 0.625));
        kdTree.insert(new Point2D(0.25, 0.375));
        kdTree.insert(new Point2D(0.25, 0.5));
        kdTree.insert(new Point2D(0.25, 0.625));
        kdTree.insert(new Point2D(0.25, 1.0));
        kdTree.insert(new Point2D(0.125, 0.5));
        kdTree.insert(new Point2D(0.125, 0.875));
        kdTree.insert(new Point2D(0.125, 0.875)); // duplicate
        kdTree.insert(new Point2D(0.5, 0.875));
        kdTree.insert(new Point2D(0.5, 1.0));
        kdTree.insert(new Point2D(0.75, 0.125));
        kdTree.insert(new Point2D(0.75, 0.25));
        kdTree.insert(new Point2D(0.75, 1.0));
        kdTree.insert(new Point2D(0.625, 0.25));
        kdTree.insert(new Point2D(0.625, 1.0));
        kdTree.insert(new Point2D(0.875, 0.25));
        kdTree.insert(new Point2D(1.0, 0.625));
        kdTree.insert(new Point2D(1.0, 0.75));
        assertEquals(18, kdTree.size());
    }

    @Test
    public void size4() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.0, 0.625));
        kdTree.insert(new Point2D(0.125, 0.875));
        kdTree.insert(new Point2D(0.125, 0.875)); // duplicate
        assertEquals(2, kdTree.size());
    }

    @Test
    public void size5() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(1, 1));
        kdTree.insert(new Point2D(0, 1));
        kdTree.insert(new Point2D(0, 0));
        assertEquals(3, kdTree.size());
    }

    @Test
    public void range() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.9375, 0.0625)); // A
        kdTree.insert(new Point2D(0.34375, 0.3125)); // B
        kdTree.insert(new Point2D(0.84375, 0.6875)); // C
        kdTree.insert(new Point2D(0.0625, 0.03125)); // D
        kdTree.insert(new Point2D(0.625, 0.125)); // E
        kdTree.insert(new Point2D(0.21875, 0.875)); // F
        kdTree.insert(new Point2D(0.8125, 0.15625)); // G
        kdTree.insert(new Point2D(1.0, 0.625)); // H
        kdTree.insert(new Point2D(0.40625, 0.96875)); // I
        kdTree.insert(new Point2D(0.15625, 0.71875)); // J
        kdTree.insert(new Point2D(0.71875, 0.53125)); // K
        kdTree.insert(new Point2D(0.46875, 0.65625)); // L
        kdTree.insert(new Point2D(0.75, 0.375)); // M
        kdTree.insert(new Point2D(0.09375, 0.5)); // N
        kdTree.insert(new Point2D(0.0, 0.75)); // O
        kdTree.insert(new Point2D(0.59375, 0.5625)); // P
        kdTree.insert(new Point2D(0.6875, 0.1875)); // Q
        kdTree.insert(new Point2D(0.28125, 0.25)); // R
        kdTree.insert(new Point2D(0.5625, 0.90625)); // S
        kdTree.insert(new Point2D(0.53125, 0.40625)); // T
        RectHV query = new RectHV(0.125, 0.21875, 0.78125, 0.59375);
        Iterable<Point2D> result = kdTree.range(query);
        StdOut.println(result);
        assertTrue(size(result) > 1);
    }

    private int size(Iterable<?> iterable) {
        int size = 0;
        for (Object ignored : iterable) size++;
        return size;
    }

}