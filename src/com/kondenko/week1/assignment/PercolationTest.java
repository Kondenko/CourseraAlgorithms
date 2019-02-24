package com.kondenko.week1.assignment;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {

    private Percolation p;

    @Before
    public void setUp() {
        p = new Percolation(2);
    }

    @Test
    public void shouldPercolate() {
        p.open(1, 1);
        p.open(2, 1);
        assertTrue(p.percolates());
    }

    @Test
    public void shouldNotPercolate() {
        p.open(1, 1);
        assertFalse(p.percolates());
    }

    @Test
    public void shouldNotPercolate1() {
        p = new Percolation(1);
        assertFalse(p.percolates());
    }

    @Test
    public void shouldNotPercolate2() {
        p = new Percolation(2);
        assertFalse(p.percolates());
    }

    @Test
    public void shouldBeFullAndOpen() {
        p = new Percolation(1);
        p.open(1, 1);
        assertTrue(p.isFull(1, 1) && p.isOpen(1, 1));
    }

    @Test
    public void shouldBeClosedAndNotFull() {
        p = new Percolation(1);
        assertFalse(p.isFull(1, 1) && p.isOpen(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        p = new Percolation(10);
        p.isOpen(-1, 5);
    }

}