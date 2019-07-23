package com.kondenko.week5.quiz;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.kondenko.CompareUtils.gt;
import static com.kondenko.CompareUtils.lt;

public class RedBlackBst<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    protected Node root = null;

    public void put(K key, V value) {
        root = put(root, root, key, value);
        root.parent = null;
    }

    private Node put(Node parent, Node node, K key, V value) {
        if (node == null) return new Node(parent, key, value, RED);
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
        Node deletedNode = delete(root, key);
        if (deletedNode != null) return deletedNode.value;
        return null;
    }

    private Node delete(Node node, K key) {
        if (node.key == null) return null;
        if (isRed(node)) {
            Node n = bstDelete(node, key);
            n.color = BLACK;
            return n;
        } else {
            return fixDoubleBlack(node);
        }
    }

    private Node fixDoubleBlack(Node node) {
        Node sibling = sibling(node);
        boolean isLeft = isLeft(sibling);
        boolean isRed = isRed(sibling);
        boolean oneChildRed = isRed(sibling.left) || isRed(sibling.right);
        boolean bothChildrenBlack = !isRed(sibling.left) && !isRed(sibling.right);
        node.key = null;
        node.value = null;
        if (!isRed && oneChildRed) {
            if (isLeft && isRed(sibling.left)) return leftLeft(sibling);
            else if (isLeft && isRed(sibling.right)) return leftRight(sibling);
            else if (!isLeft && isRed(sibling.right)) return rightRight(sibling);
            else if (!isLeft && isRed(sibling.left)) return rightLeft(sibling);
        } else if (!isRed && bothChildrenBlack) {
            recolor(sibling);
            return fixDoubleBlack(node.parent);
        } else if (isRed) {
            if (isLeft) {
                rotateRight(sibling);
                sibling.left.color = RED;
            } else {
                rotateLeft(sibling);
                sibling.right.color = RED;
            }
            sibling.color = BLACK;
            return sibling;
        }
        return null;
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
        return rotateLeft(sibling);
    }

    private Node leftRight(Node sibling) {
        Node newSibling = sibling.right;
        sibling.parent.left = newSibling;
        newSibling.parent = sibling.parent;
        sibling.right = null;
        newSibling.left = sibling;
        sibling.color = RED;
        return rotateRight(sibling);

    }

    private void recolor(Node sibling) {
        sibling.color = RED;
    }

    private Node bstDelete(Node node, K key) {
        if (key == node.key) {
            Node right = node.right;
            Node left = node.left;
            if (right == null && left == null) return node;
            if (right != null && left == null) return right;
            if (left != null && right == null) return left;
            Node successor = successor(node, key);
            bstDelete(successor, key);
            node.key = successor.key;
            node.value = successor.value;
            return node;
        }
        if (key == node.left.key) {
            node.left = bstDelete(node.left, key);
            return node.left;
        }
        if (key == node.right.key) {
            node.right = bstDelete(node.right, key);
            return node.right;
        }
        throw new IllegalStateException("Should not be reached");
    }

    private boolean isLeft(Node node) {
        return node.parent.left.key == node.key;
    }

    protected Node sibling(Node node) {
        Node left = node.parent.left;
        if (node.key != left.key) return left;
        else return node.parent.right;
    }

    protected Node successor(K key) {
        return successor(root, key);
    }

    private Node successor(Node node, K key) {
        if (node == null) return null;
        if (key == node.key && node.right != null) {
            Node tmp = node.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp;
        }
        if (lt(key, node.key)) return successor(node.left, key);
        if (gt(key, node.key)) return successor(node.right, key);
        return null;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    protected Node getNode(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        if (h.right != null) h.right.parent = h;
        x.left = h;
        h.parent = x;
        x.color = h.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        if (h.left != null) h.left.parent = h;
        x.right = h;
        h.parent = x;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flip(Node n) {
        if (n != null) {
            n.color = RED;
            if (n.left != null) n.left.color = BLACK;
            if (n.right != null) n.right.color = BLACK;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Map<Node, Integer> nodesByRows = getNodesByRows();
        HashMap<Integer, String> indentedStrings = new HashMap<>();
        int size = Collections.max(nodesByRows.values());
        nodesByRows.forEach((node, level) -> {
            int indent = size - level;
            String line = String.format("%s%s ", indentedStrings.getOrDefault(indent, ""), node);
            indentedStrings.put(indent, line);
        });
        indentedStrings.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((v1, v2) -> -(v1.compareTo(v2))))
                .forEach(entry -> {
                    int indent = entry.getKey();
                    String row = entry.getValue();
                    sb.append("   ".repeat(indent));
                    sb.append(row);
                    sb.append("\n\n");
                });
        return sb.toString();
    }

    protected Map<Node, Integer> getNodesByRows() {
        int level = 0;
        HashMap<Node, Integer> data = new HashMap<>();
        nodesByLevels(data, root, level);
        return data;
    }

    private HashMap<Node, Integer> nodesByLevels(HashMap<Node, Integer> map, Node node, int level) {
        map.put(node, level);
        if (node != null) {
            int newLevel = level + 1;
            if (node.left != null) map.putAll(nodesByLevels(map, node.left, newLevel));
            if (node.right != null) map.putAll(nodesByLevels(map, node.right, newLevel));
        }
        return map;
    }

    protected class Node {

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
            return String.format("%s(%s)", key, value);
        }


    }

}
