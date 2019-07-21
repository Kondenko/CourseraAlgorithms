package com.kondenko.week5.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedBlackBstTest {

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

}