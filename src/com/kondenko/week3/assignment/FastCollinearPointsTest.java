package com.kondenko.week3.assignment;


import org.junit.Test;

public class FastCollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInput() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new FastCollinearPoints(points);
    }


}