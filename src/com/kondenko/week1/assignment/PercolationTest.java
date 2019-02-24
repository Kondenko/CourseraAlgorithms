package com.kondenko.week1.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PercolationTest {

    private Percolation p;

    @BeforeEach
    void setUp() {
        p = new Percolation(2);
    }

    @Test
    void shouldPercolate() {
        p.open(1, 1);
        p.open(2, 1);
        assertTrue(p.percolates());
    }

    @Test
    void shouldNotPercolate() {
        p.open(1,1);
        assertFalse(p.percolates());
    }

    @Test
    void shouldNotPercolate1() {
        p = new Percolation(1);
        assertFalse(p.percolates());
    }

    @Test
    void shouldNotPercolate2() {
        p = new Percolation(2);
        assertFalse(p.percolates());
    }

}