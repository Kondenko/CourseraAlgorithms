package com.kondenko.week3.assignment;


import org.junit.Test;

public class FastCollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInputBrute() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new FastCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInputFast() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new BruteCollinearPoints(points);
    }

    @Test
    public void shouldNotCrash() {
        Point[] points = { new Point(0,0), new Point(1,0) };
        new FastCollinearPoints(points);
        new BruteCollinearPoints(points);
    }

    @Test
    public void shouldNotCrash2() {
        Point[] points =
                PointsFactory.fromFile("/Users/vladimirkondenko/IdeaProjects/AlgoritmsPrincetonCoursera/collinear/input48.txt");
        new FastCollinearPoints(points);
        new BruteCollinearPoints(points);
    }


}