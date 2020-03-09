package com.kondenko.part1.week3.quiz;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void test1() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        assertTrue(list.contains(1));
        assertEquals(1, (int) list.get(0));
        list.delete(0);
        assertFalse(list.contains(1));
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
    }


}