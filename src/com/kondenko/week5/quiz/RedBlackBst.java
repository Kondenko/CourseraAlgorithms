package com.kondenko.week5.quiz;

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
            Node sibling = sibling(node);
            node.key = null;
            node.value = null;
            if (!isRed(sibling) && (isRed(sibling.left) || isRed(sibling.right))) {
                rightRight(sibling);
            } else if (!isRed(sibling) && isRed(sibling.left)) {
                rightLeft(sibling);
            }
        }
        throw new IllegalArgumentException("Can't delete a black node yet");
    }

    private Node rightRight(Node sibling) {
        return null;
    }

    private Node leftLeft(Node sibling) {
        return null;
    }

    private Node rightLeft(Node sibling) {
        return null;
    }

    private Node leftRight(Node sibling) {
        return null;
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

    protected Node sibling(Node node) {
        return sibling(root, node);
    }

    private Node sibling(Node root, Node node) {
        if (node.key == root.left.key) return root.right;
        if (node.key == root.right.key) return root.left;
        if (lt(node.key, root.key)) return sibling(root.left, node);
        if (gt(node.key, root.key)) return sibling(root.right, node);
        throw new IllegalStateException("Should not be reached");
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
        public String toString() {
            return key + ": " + value;
        }

    }

}
