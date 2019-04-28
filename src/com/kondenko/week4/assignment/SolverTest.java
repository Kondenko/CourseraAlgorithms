package com.kondenko.week4.assignment;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolverTest {

    @Test
    public void solvable() {
        Solver solver = new Solver(new Board(new int[][]{
                {0,  1,  3},
                {4,  2,  5},
                {7,  8,  6},
        }));
        assertTrue(solver.isSolvable());
    }

    @Test
    public void unsolvable() {
        Solver solver = new Solver(new Board(new int[][]{
                {1,  2,  3},
                {4,  5,  6},
                {8,  7,  0},

        }));
        assertFalse(solver.isSolvable());
    }

}