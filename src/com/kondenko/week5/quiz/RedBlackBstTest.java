package com.kondenko.week5.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedBlackBstTest {

    @Test
    public void shouldFindSuccessor() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        bst.put(a, 1);
        bst.put(b, 2);
        bst.put(c, 3);
        bst.put(d, 4);
        bst.put(e, 5);
        assertEquals(e, bst.successor(d).key);
    }

    @Test
    public void shouldFindSibling() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        bst.put(a, 1);
        bst.put(b, 2);
        bst.put(c, 3);
        bst.put(d, 4);
        bst.put(e, 5);
        assertEquals(e, bst.sibling(bst.getNode(b)).key);
    }

    @Test
    public void shouldContainAllAddedElements() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        bst.put(a, 1);
        bst.put(b, 2);
        bst.put(c, 3);
        bst.put(d, 4);
        bst.put(e, 5);
        assertTrue(bst.contains(a));
        assertTrue(bst.contains(b));
        assertTrue(bst.contains(c));
        assertTrue(bst.contains(d));
        assertTrue(bst.contains(e));
    }

    @Test
    public void shouldDeleteElements0() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        String c = "C";
        bst.put(a, 1);
        bst.put(b, 2);
        bst.put(c, 3);
        int deleted = bst.delete(a);
        assertEquals(1, deleted);
    }

    @Test
    public void shouldDeleteElements1() {
        RedBlackBst<Integer, Integer> bst = new RedBlackBst<>();
        put(bst, 20);
        put(bst, 30);
        put(bst, 35);
        put(bst, 40);
        put(bst, 50);
        System.out.println(bst.toString());
        int deleted = bst.delete(20);
        assertEquals(20, deleted);
        assertFalse(bst.contains(20));
    }

    @Test
    public void shouldShowNullNodesInToString() {
        RedBlackBst<String, String> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        put(bst, a);
        put(bst, b);
        String str = bst.toString();
        System.out.println(str);
        assertTrue(str.contains("nil"));
        assertTrue(str.contains(a));
        assertTrue(str.contains(b));
    }

    @Test
    public void shouldMaintainBalance() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        String a = "A";
        String b = "B";
        String c = "C";
        bst.put(a, 1);
        bst.put(b, 2);
        bst.put(c, 3);
        assertEquals(b, bst.root.key);
        assertEquals(a, bst.root.left.key);
        assertEquals(c, bst.root.right.key);
    }

    @Test
    public void shouldNotContainExtraElements() {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        bst.put("A", 1);
        assertFalse(bst.contains("B"));
    }

    private <T extends Comparable<T>> void put(RedBlackBst<T, T> bst, T value) {
        bst.put(value, value);
    }

}