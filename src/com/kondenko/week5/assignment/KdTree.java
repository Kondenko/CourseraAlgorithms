package com.kondenko.week5.assignment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private Tree2d points = new Tree2d();

    /**
     * construct an empty set of points
     */
    public KdTree() {
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        points.put(p);
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect is null");
        return points.searchIn(rect);
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        if (isEmpty()) return null;
        return points.nearest(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        points.nodesToList().forEach(n -> {
            Point2D p = n.point;
            p.draw();
        });
    }

    /**
     * does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        return points.contains(p);
    }

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * number of points in the set
     */
    public int size() {
        return points.size();
    }

    @Override
    public String toString() {
        return points.toString();
    }

    private static class Tree2d {

        private Node root = null;

        private int size = 0;

        public List<Node> nodesToList() {
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.addAll(toList(root, nodes));
            return nodes;
        }

        public List<Point2D> toList() {
            List<Node> nodes = nodesToList();
            ArrayList<Point2D> points = new ArrayList<>();
            for (Node node : nodes) {
                points.add(new Point2D(node.x, node.y));
            }
            return points;
        }

        private List<Node> toList(Node root, List<Node> nodes) {
            if (root.left != null) nodes.addAll(toList(root.left, nodes));
            if (root.right != null) nodes.addAll(toList(root.right, nodes));
            nodes.add(root);
            return nodes;
        }

        public void put(Point2D key) {
            root = put(root, root, key, root != null);
            size++;
        }

        private Node put(Node parent, Node node, Point2D key, boolean isLeftOrUpper) {
            boolean isChildVertical = parent == null || !parent.isVertical;
            RectHV rect;
            if (parent != null) {
                if (isChildVertical) rect = isLeftOrUpper ? parent.left() : parent.right();
                else rect = isLeftOrUpper ? parent.upper() : parent.lower();
            } else {
                rect = new RectHV(0, 0, 1, 1);
            }
            if (node == null) return new Node(parent, key.x(), key.y(), rect, isChildVertical);
            int comparison;
            if (node.isVertical) {
                comparison = Double.compare(key.x(), node.x);
            } else {
                comparison = Double.compare(key.y(), node.y);
            }
            if (comparison < 0) node.left = put(node, node.left, key, true);
            else if (comparison > 0) node.right = put(node, node.right, key, false);
            else node = new Node(node.parent, key.x(), key.y(), node.rect, isChildVertical);
            return node;
        }

        public List<Point2D> searchIn(RectHV rect) {
            ArrayList<Point2D> points = new ArrayList<>();
            searchIn(root, rect, points);
            return points;
        }

        private void searchIn(Node root, RectHV rect, ArrayList<Point2D> points) {
            if (root == null) return;
            if (intersect(root.left(), root.upper())) {
                searchIn(root.left, rect, points);
            }
            if (intersect(root.right(), root.lower())) {
                searchIn(root.right, rect, points);
            }
            if (rect.contains(root.point)) points.add(root.point);
        }

        public Point2D nearest(Point2D query) {
            return nearest(root, query, null);
        }

        private Point2D nearest(Node node, Point2D query, Point2D nearest) {
            if (node == null) return nearest;
            double distanceToQuery = node.point.distanceSquaredTo(query);
            if (nearest == null || distanceToQuery < nearest.distanceSquaredTo(query))
                nearest = node.point;
            if (node.isVertical) {
                if (query.x() < node.x) nearest = nearest(node.left, query, nearest);
                else nearest = nearest(node.right, query, nearest);
            } else {
                nearest = nearest(node.left, query, nearest);
                nearest = nearest(node.right, query, nearest);
            }
            return nearest;
        }

        public boolean contains(Point2D key) {
            return getNode(key).y == key.y();
        }

        private final Node getNode(Point2D key) {
            Node node = root;
            while (node != null) {
                int cmp = node.isVertical ? Double.compare(key.x(), (node.x)) : Double.compare(key.y(), (node.y));
                if (cmp < 0) node = node.left;
                else if (cmp > 0) node = node.right;
                else return node;
            }
            return null;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private boolean intersect(RectHV a, RectHV b) {
            if (a == null || b == null) return false;
            return a.intersects(b);
        }

        private static final class Node implements Comparable<Node> {

            final Double x;
            final Double y;

            final Point2D point;
            final RectHV rect;

            final public Node parent;
            public Node left = null;
            public Node right = null;

            final boolean isVertical;

            public Node(Node parent, Double x, Double y, RectHV rect, boolean isVertical) {
                this.parent = parent;
                this.x = x;
                this.y = y;
                this.rect = rect;
                this.isVertical = isVertical;
                this.point = new Point2D(x, y);
            }

            public void drawLine() {
                if (isVertical) {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(x, rect.ymax(), x, rect.ymin());
                }
            }

            public RectHV left() {
                double xmin = rect.xmin();
                double xmax = x;
                if (xmax < xmin) return null;
                return new RectHV(xmin, rect.ymin(), xmax, rect.ymax());
            }

            public RectHV right() {
                double xmin = x;
                double xmax = rect.xmax();
                if (xmax < xmin) return null;
                return new RectHV(xmin, rect.ymin(), xmax, rect.ymax());
            }

            public RectHV upper() {
                double ymin = y;
                double ymax = rect.ymax();
                if (ymax < ymin) return null;
                return new RectHV(rect.xmin(), ymin, rect.xmax(), ymax);
            }

            public RectHV lower() {
                double ymin = rect.ymin();
                double ymax = y;
                if (ymax < ymin) return null;
                return new RectHV(rect.xmin(), ymin, rect.xmax(), ymax);
            }

            @Override
            public int compareTo(Node o) {
                return isVertical ? Double.compare(x, o.x) : Double.compare(y, o.y);
            }

            @Override
            public String toString() {
                return String.format("(%.1f, %.1f)", x, y);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return isVertical == node.isVertical &&
                        Objects.equals(x, node.x) &&
                        Objects.equals(y, node.y);
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y, isVertical);
            }

        }

    }

}