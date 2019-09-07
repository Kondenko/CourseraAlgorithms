package com.kondenko.week5.assignment;

import com.kondenko.Utils;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return null;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        points.toList().forEach(p -> {
            StdDraw.setFont(new Font("Arial", Font.PLAIN, 12));
            StdDraw.text(p.x(), p.y() + 0.05, String.format("(%f, %f)", p.x(), p.y()), 45);
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

        protected Node root = null;

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
            root = put(root, root, key);
            size++;
        }

        private Node put(Node parent, Node node, Point2D key) {
            boolean isChildVertical = parent == null || !parent.isVertical;
            RectHV rect;
            if (parent != null) {
                if (isChildVertical) rect = parent.rightOrLower();
                else rect = parent.leftOrUpper();
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
            if (comparison < 0) node.left = put(node, node.left, key);
            else if (comparison > 0) node.right = put(node, node.right, key);
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
            if (rect.intersects(root.leftOrUpper())) searchIn(root.left, rect, points);
            if (rect.intersects(root.rightOrLower())) searchIn(root.right, rect, points);
            if (rect.contains(root.point)) points.add(root.point);
        }

//        public Point2D nearest(Node root, Point2D point) {
//
//        }

        public boolean contains(Point2D key) {
            return getNode(key).y == key.y();
        }

        protected final Node getNode(Point2D key) {
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

        public String toString() {
            if (root == null) return "Empty";
            StringBuilder sb = new StringBuilder();
            Map<Integer, ArrayList<Node>> nodesByRows = getNodesByRows();
            HashMap<Integer, String> indentedStrings = new HashMap<>();
            int size = Collections.max(nodesByRows.keySet());
            nodesByRows.forEach((level, nodes) -> {
                int indent = size - level;
                String line = nodesByRows.getOrDefault(level, new ArrayList<>())
                        .stream()
                        .map(this::safeToString)
                        .collect(Collectors.joining("   "));
                indentedStrings.put(indent, line);
            });
            indentedStrings.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                    .forEach(entry -> {
                        int indent = entry.getKey();
                        String row = entry.getValue();
                        sb.append("   ".repeat(indent));
                        sb.append(row);
                        sb.append("\n\n");
                    });
            return sb.toString();
        }

        protected final Map<Integer, ArrayList<Node>> getNodesByRows() {
            int level = 0;
            HashMap<Integer, ArrayList<Node>> data = new HashMap<>();
            nodesByLevels(data, root, level);
            return data;
        }

        private HashMap<Integer, ArrayList<Node>> nodesByLevels(HashMap<Integer, ArrayList<Node>> map, Node node, int level) {
            var list = map.getOrDefault(level, new ArrayList<>());
            list.add(node);
            map.put(level, list);
            if (node != null) {
                int newLevel = level + 1;
                map.putAll(nodesByLevels(map, node.left, newLevel));
                map.putAll(nodesByLevels(map, node.right, newLevel));
            }
            return map;
        }

        private String safeToString(Node node) {
            if (node == null || node.x == null) return "n";
            else return node.toString();
        }

        protected static final class Node implements Comparable<Node> {

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

            public RectHV leftOrUpper() {
                return isVertical ?
                        new RectHV(rect.xmin(), rect.ymin(), x, rect.ymax()) // left
                        : new RectHV(rect.xmin(), y, rect.xmax(), rect.ymax()); // upper
            }

            public RectHV rightOrLower() {
                return isVertical ?
                        new RectHV(x, rect.ymin(), rect.xmax(), rect.ymax()) // left
                        : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), y); // upper
            }

            @Override
            public int compareTo(Node o) {
                return isVertical ? Double.compare(x, o.x) : Double.compare(y, o.y);
            }

            @Override
            public String toString() {
                String keyString = String.format("(%.1f, %.1f)", x, y);
                return keyString + " " + (isVertical ? "|" : "-");
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