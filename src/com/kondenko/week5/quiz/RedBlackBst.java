package com.kondenko.week5.quiz;

public class RedBlackBst<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    protected Node root = null;

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, RED);
        int comparison = key.compareTo(node.key);
        if (comparison < 0) node.left = put(node.left, key, value);
        else if (comparison > 0) node.right = put(node.right, key, value);
        else node.value = value;
        boolean leftRed = isRed(node.left);
        boolean rightRed = isRed(node.right);
        if (leftRed && isRed(node.left.left)) node = rotateRight(node);
        if (!leftRed && rightRed) node = rotateLeft(node);
        if (leftRed && rightRed) flip(node);
        return node;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    private Node getNode(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node;
        }
        return null;
    }

    private Node rotateLeft(Node n) {
        assert isRed(n.right);
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node n) {
        assert isRed(n.left);
        Node l = n.left;
        n.left = l.right;
        l.right = n;
        l.color = n.color;
        n.color = RED;
        return l;
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

    protected class Node {

        K key;
        V value;

        public Node left = null;
        public Node right = null;

        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

    }

}
