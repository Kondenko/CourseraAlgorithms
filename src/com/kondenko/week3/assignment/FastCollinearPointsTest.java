package com.kondenko.week3.assignment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastCollinearPointsTest {

    @Test
    public void numberOfSegments() {
        String file = "/Users/vladimirkondenko/IdeaProjects/AlgoritmsPrincetonCoursera/collinear/input10.txt";
        Point[] points = PointsFactory.fromFile(file);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(2, collinear.numberOfSegments());
    }

}