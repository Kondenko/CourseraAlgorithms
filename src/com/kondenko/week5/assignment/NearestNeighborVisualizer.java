package com.kondenko.week5.assignment;

/******************************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import com.kondenko.Utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class NearestNeighborVisualizer {

    public static void main(String[] args) {
        // initialize the two data structures with point from file
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();

        Double prevX = null;
        Double prevY = null;

        while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();

            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();
            kdtree.draw();

            Utils.drawCoords(x, y);

            if (prevX != null && prevY != null && StdDraw.isMousePressed()) {
                double llX = min(x, prevX);
                double urX = max(x, prevX);
                double llY = min(y, prevY);
                double urY = max(y, prevY);
                RectHV rect = new RectHV(llX, llY, urX, urY);
                rect.draw();
                // highlight points inside the rect
                StdDraw.setPenRadius(0.03);
                StdDraw.setPenColor(StdDraw.GREEN);
                // brute.range(rect).forEach(Point2D::draw);
                kdtree.range(rect).forEach(Point2D::draw);
            } else if (!StdDraw.isMousePressed()) {
                prevX = x;
                prevY = y;
            }

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            Point2D nearestBrute = brute.nearest(query);
            if (nearestBrute != null) nearestBrute.draw();

            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            Point2D nearestKd = kdtree.nearest(query);
            if (nearestKd != null) nearestKd.draw();

            StdDraw.show();
            StdDraw.pause(40);
        }

    }
}