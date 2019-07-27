package com.kondenko.week5.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.StdOut;

public class RedBlackBst<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    protected Node root = null;

    private boolean logAuxiliaryOperations = false;

    public void put(K key, V value) {
        root = put(root, root, key, value);
        root.parent = null;
        root.color = BLACK;
        // System.out.printf("Put %s(%s)\n\n%s", key, value, this);
    }

    private Node put(Node parent, Node node, K key, V value) {
        if (node == null) return new Node(parent, key, value, root != null);
        int comparison = key.compareTo(node.key);
        if (comparison < 0) node.left = put(node, node.left, key, value);
        else if (comparison > 0) node.right = put(node, node.right, key, value);
        else node.value = value;
        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flip(node);
        return node;
    }

    public V delete(K key) {
        Node nodeToDelete = getNode(key);
        if (nodeToDelete == null) return null;
        V value = nodeToDelete.value;
        root = delete(nodeToDelete, key);
        flipIfNeeded(root);
        return value;
    }

    private Node delete(Node node, K key) {
        if (isRed(node) || isRed(node.parent)) {
            bstDelete(node, key);
            node.parent.color = BLACK;
            return root;
        } else {
            return fixDoubleBlack(node, key);
        }
    }

    private void bstDelete(Node node, K key) {
        if (node == null) return;
        if (node.equals(root) && key.equals(node.key)) {
            root = null;
        } else if (key.equals(node.key)) {
            Node right = node.right;
            Node left = node.left;
            boolean isLeft = isLeft(node);
            if (right == null && left == null) {
                if (isLeft) node.parent.left = null;
                else node.parent.right = null;
            }
            if (right != null && left == null) {
                if (isLeft) node.parent.left = right;
                else node.parent.right = right;
            }
            if (left != null && right == null) {
                if (isLeft) node.parent.left = left;
                else node.parent.right = left;
            }
            Node successor = successor(node);
            bstDelete(successor, key);
            node.key = successor.key;
            node.value = successor.value;
        } else {
            bstDelete(node.left, key);
            bstDelete(node.right, key);
        }
    }

    private Node fixDoubleBlack(Node node, K key) {
        if (!node.equals(root)) {
            Node sibling = sibling(node);
            boolean isLeft = isLeft(sibling);
            boolean isRed = isRed(sibling);
            boolean oneChildRed = isRed(sibling.left) || isRed(sibling.right);
            boolean bothChildrenBlack = !isRed(sibling.left) && !isRed(sibling.right);
            if (key.equals(node.key)) {
                node.key = null;
                node.value = null;
            }
            if (!isRed && oneChildRed) {
                if (isLeft && isRed(sibling.left)) return leftLeft(sibling);
                else if (isLeft && isRed(sibling.right)) return leftRight(sibling);
                else if (!isLeft && isRed(sibling.right)) return rightRight(sibling);
                else if (!isLeft && isRed(sibling.left)) return rightLeft(sibling);
            } else if (!isRed && bothChildrenBlack) {
                recolor(sibling);
                return fixDoubleBlack(node.parent, key);
            } else if (isRed) {
                Node n;
                if (isLeft) {
                    n = rotateRight(sibling.parent);
                    sibling.left.color = RED;
                } else {
                    n = rotateLeft(sibling);
                    sibling.right.color = RED;
                }
                sibling.color = BLACK;
                return n;
            } else {
                return node;
            }
        } else {
            node.color = BLACK;
            return node;
        }
        throw new RuntimeException("Unreachable code");
    }

    private Node rightRight(Node sibling) {
        return rotateLeft(sibling);
    }

    private Node leftLeft(Node sibling) {
        return rotateRight(sibling);
    }

    private Node rightLeft(Node sibling) {
        Node newSibling = sibling.left;
        sibling.parent.right = newSibling;
        newSibling.parent = sibling.parent;
        sibling.left = null;
        newSibling.right = sibling;
        sibling.color = RED;
        newSibling.color = BLACK;
        Node rotated = rotateLeft(sibling.parent);
        flipIfNeeded(rotated);
        return rotated;
    }

    private Node leftRight(Node sibling) {
        Node newSibling = sibling.right;
        sibling.parent.left = newSibling;
        newSibling.parent = sibling.parent;
        sibling.right = null;
        newSibling.left = sibling;
        sibling.color = RED;
        Node rotated =  rotateRight(sibling);
        flipIfNeeded(rotated);
        return rotated;
    }

    private void flipIfNeeded(Node node) {
        if (isRed(node.left) && isRed(node.right)) flip(node);
    }

    private void recolor(Node sibling) {
        sibling.color = RED;
    }

    protected final Node sibling(Node node) {
        Node left = node.parent.left;
        if (node.key != left.key) return left;
        else return node.parent.right;
    }

    protected final Node successor(Node n) {
        if (n.right != null) {
            return minValue(n.right);
        }
        Node p = n.parent;
        while (p != null && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * Given a non-empty binary search tree, return the minimum data
     * value found in that tree. Note that the entire tree does not need
     * to be searched.
     **/
    private Node minValue(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    protected final Node getNode(K key) {
        Node node = root;
        while (node != null && node.key != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        Node hParent = h.parent;
        Node x = h.right;
        h.right = x.left;
        if (h.right != null) h.right.parent = h;
        x.left = h;
        h.parent = x;
        x.parent = hParent;
        x.color = h.color;
        x.left.color = RED;
        if (logAuxiliaryOperations) System.out.printf("rotateLeft(%s)\n\n%s", h.key, this);
        return x;
    }

    protected final Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        if (h.left != null) h.left.parent = h;
        x.right = h;
        h.parent = x;
        x.color = h.color;
        h.color = RED;
        if (logAuxiliaryOperations) System.out.printf("rotateRight(%s)\n\n%s", h.key, this);
        return x;
    }

    private void flip(Node h) {
        if (h != null) {
            if (!h.equals(root) && h.parent != null) h.color = RED;
            if (h.left != null) h.left.color = BLACK;
            if (h.right != null) h.right.color = BLACK;
            if (logAuxiliaryOperations) System.out.printf("flip(%s)\n\n%s", h.key, this);
        }
    }

    private boolean isLeft(Node node) {
        Node left = node.parent.left;
        return left != null && node.parent.left.key.equals(node.key);
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
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
        if (node == null || node.key == null) return "n";
        else return node.toString();
    }

    public static void main(String[] args) {
        RedBlackBst<Integer, Integer> bst = new RedBlackBst<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int key = random.nextInt(20);
            bst.put(key, key);
        }
        StdOut.println(bst);
    }

    protected final class Node {

        K key;
        V value;

        public Node parent;
        public Node left = null;
        public Node right = null;

        boolean color;

        public Node(Node parent, K key, V value, boolean color) {
            this.parent = parent;
            this.key = key;
            this.value = value;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return color == node.color &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, color);
        }

        @Override
        public String toString() {
            String keyString = key.equals(value) ? key.toString() : String.format("%s(%s)", key, value);
            String colorString = color == RED ? ((char) 27 + "[31m") : "";
            String colorReset = color == RED ? ((char) 27 + "[0m") : "";
            // String colorString = color == RED ? "r" : "b";
            // String colorReset = "";
            return colorString + keyString + colorReset;
        }

    }

}
