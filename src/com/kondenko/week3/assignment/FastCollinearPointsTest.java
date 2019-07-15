package com.kondenko.week3.assignment;


import org.junit.Test;

public class FastCollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInput() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new FastCollinearPoints(points);
    }

    @Test
    public void shouldNotCrash() {
        Point[] points = { new Point(0,0), new Point(1,0) };
        new FastCollinearPoints(points);
    }


}