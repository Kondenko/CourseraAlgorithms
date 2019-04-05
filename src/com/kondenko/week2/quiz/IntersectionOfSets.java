package com.kondenko.week2.quiz;

import java.util.Objects;

public class IntersectionOfSets {

    public static int numberOfIntersections(Point[] a, Point[] b) {
        int intersections = 0;

        return intersections;
    }

    public static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Point of(int x, int y) {
            return new Point(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

}
