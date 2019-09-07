package com.kondenko.week5.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
        if (!contains(p)) points.put(p);
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
        for (Tree2d.Node n : points.nodesToList()) {
            Point2D p = n.point;
            p.draw();
        }
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

        public Node[] nodesToList() {
            ArrayList<Node> nodes = new ArrayList<>();
            return toList(root, nodes).toArray(new Node[0]);
        }

        private ArrayList<Node> toList(Node root, ArrayList<Node> nodes) {
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
            boolean isChildVertical = root == null || !parent.isVertical;
            RectHV rect;
            if (parent != null) {
                if (isChildVertical) {
                    rect = isLeftOrUpper ? parent.left() : parent.right();
                } else {
                    rect = isLeftOrUpper ? parent.upper() : parent.lower();
                }
            } else {
                rect = new RectHV(0, 0, 1, 1);
            }

            if (node == null) return new Node(parent, key.x(), key.y(), rect, isChildVertical);

            int comparison = node.compareTo(key);
            if (comparison < 0) node.left = put(node, node.left, key, true);
            else node.right = put(node, node.right, key, false);

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
            return getNode(key) != null;
        }

        private Node getNode(Point2D key) {
            Node node = root;
            while (node != null) {
                int comparison = node.compareTo(key);
                if (comparison < 0) node = node.left;
                else if (comparison > 0) node = node.right;
                else if (node.point.equals(key)) return node;
                else return null;
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

        static final class Node implements Comparable<Point2D> {

            public final Double x;
            public final Double y;

            public final Point2D point;
            public final RectHV rect;

            final public Node parent;
            public Node left = null;
            public Node right = null;

            public final boolean isVertical;

            public Node(Node parent, Double x, Double y, RectHV rect, boolean isVertical) {
                this.parent = parent;
                this.x = x;
                this.y = y;
                this.rect = rect;
                this.isVertical = isVertical;
                this.point = new Point2D(x, y);
            }

            public RectHV left() {
                double xmin = rect.xmin();
                double xmax = Math.max(x, rect.xmax());
                return new RectHV(xmin, rect.ymin(), xmax, rect.ymax());
            }

            public RectHV right() {
                double xmin = Math.min(x, rect.xmin());
                double xmax = rect.xmax();
                return new RectHV(xmin, rect.ymin(), xmax, rect.ymax());
            }

            public RectHV lower() {
                double ymin = rect.ymin();
                double ymax = Math.max(y, rect.ymax());
                return new RectHV(rect.xmin(), ymin, rect.xmax(), ymax);
            }

            public RectHV upper() {
                double ymin = Math.min(y, rect.ymin());
                double ymax = rect.ymax();
                return new RectHV(rect.xmin(), ymin, rect.xmax(), ymax);
            }

            @Override
            public int compareTo(Point2D o) {
                return isVertical ? Double.compare(o.x(), x) : Double.compare(o.y(), y);
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