package com.kondenko.week3.assignment;

import edu.princeton.cs.algs4.In;

public class PointsFactory {

    public static Point[] fromFile(String file) {
        In input = new In(file);
        int size = input.readInt();
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

}
