package com.kondenko.part1.week2.quiz;

public class Point implements Comparable<Point> {

    public static Point ZERO = Point.of(0, 0);

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public int compareTo(Point o) {
        if (x == o.x && y == o.y) return 0;
        double distanceFromZero = distance(ZERO, this);
        double distanceFromZeroToOther = distance(ZERO, o);
        if (distanceFromZero < distanceFromZeroToOther) return -1;
        return 1;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
