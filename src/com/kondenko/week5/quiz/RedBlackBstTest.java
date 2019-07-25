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
        System.out.println(bst);
        assertEquals(e, bst.successor(bst.getNode(d)).key);
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
        System.out.println(bst);
        assertEquals(e, bst.sibling(bst.getNode(b)).key);
    }

    @Test
    public void shouldContainAllAddedElements() {
        RedBlackBst<String, String> bst = new RedBlackBst<>();
        String A = "A";
        String E = "E";
        String S = "S";
        String R = "R";
        String C = "C";
        put(bst, A);
        put(bst, E);
        put(bst, S);
        put(bst, R);
        assertEquals(E, bst.root.key);
        assertEquals(A, bst.root.left.key);
        assertEquals(S, bst.root.right.key);
        assertEquals(R, bst.root.right.left.key);
        put(bst, C);
        assertEquals(E, bst.root.key);
        assertEquals(C, bst.root.left.key);
        assertEquals(S, bst.root.right.key);
        assertEquals(R, bst.root.right.left.key);
        assertEquals(A, bst.root.left.left.key);
        assertTrue(bst.root.left.left.color);
        assertTrue(bst.root.right.left.color);
        System.out.println(bst);
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
        System.out.println(bst);
        bst.delete(a);
        assertFalse(bst.contains(a));
    }

    @Test
    public void shouldDeleteElements1() {
        RedBlackBst<Integer, Integer> bst = new RedBlackBst<>();
        put(bst, 30);
        put(bst, 20);
        put(bst, 40);
        put(bst, 10);
        System.out.println(bst.toString());
        bst.delete(10);
        assertFalse(bst.contains(10));
    }

    @Test
    public void shouldDeleteElements2() {
        RedBlackBst<Integer, Integer> bst = new RedBlackBst<>();
        put(bst, 30);
        put(bst, 20);
        put(bst, 40);
        put(bst, 50);
        System.out.println(bst.toString());
        bst.delete(20);
        assertFalse(bst.contains(20));
        assertTrue(bst.contains(30));
        assertTrue(bst.contains(40));
        assertTrue(bst.contains(50));
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
        assertTrue(str.contains("n"));
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
        System.out.println(bst);
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