package com.kondenko.week5.assignment;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;

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
    public void size2() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.8125, 0.5625));
        kdTree.insert(new Point2D(0.875, 0.0625));
        kdTree.insert(new Point2D(0.375, 0.625));
        kdTree.insert(new Point2D(0.9375, 0.0625));
        assertEquals(4, kdTree.size());
    }
}