package com.kondenko.part1.week3.assignment;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInputFast() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new FastCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnDuplicatePointsInInputBrute() {
        Point point = new Point(0,0);
        Point[] points = { point, point };
        new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnNullPointsInInputFast() {
        Point[] points = { new Point(0,0), null };
        new FastCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashOnNullPointsInInputBrute() {
        Point[] points = { null };
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

    @Test
    public void shouldFind2segmentsInInput8() {
        Point[] points =
                PointsFactory.fromFile("/Users/vladimirkondenko/IdeaProjects/AlgoritmsPrincetonCoursera/collinear/input9.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(1, collinear.numberOfSegments());
    }


}