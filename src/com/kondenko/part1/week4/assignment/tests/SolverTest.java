package com.kondenko.part1.week4.assignment.tests;

import com.kondenko.part1.week4.assignment.Board;
import com.kondenko.part1.week4.assignment.Solver;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void solvable1() {
        Solver solver = new Solver(new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6},
        }));
        assertTrue(solver.isSolvable());
    }

    @Test
    public void solvable2() {
        Solver solver = new Solver(new Board(new int[][]{
                {1, 2, 3},
                {0, 7, 6},
                {5, 4, 8},
        }));
        assertEquals(7, solver.moves());
    }

    @Test
    public void solvable3() {
        Solver solver = new Solver(new Board(new int[][]{
                {1, 2, 3, 4, 5},
                {12, 6, 8, 9, 10},
                {0, 7, 13, 19, 14},
                {11, 16, 17, 18, 15},
                {21, 22, 23, 24, 20},
        }));
        assertEquals(12, solver.moves());
    }

    @Test
    public void solvable4() {
        Solver solver = new Solver(new Board(new int[][]{
                {1, 2, 3},
                {0, 7, 6},
                {5, 4, 8},
        }));
        assertEquals(7, solver.moves());
    }

    @Test
    public void unsolvable() {
        Solver solver = new Solver(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0},

        }));
        assertFalse(solver.isSolvable());
    }

}