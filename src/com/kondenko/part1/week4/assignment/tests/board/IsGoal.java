package com.kondenko.part1.week4.assignment.tests.board;

import com.kondenko.part1.week4.assignment.Board;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/* Is a goal board */
public class IsGoal {

    @Test
    public void isGoal1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertTrue(board.isGoal());
    }

    @Test
    public void isGoal2() {
        Board board = new Board(
                new int[][]{
                        {5, 2, 3},
                        {4, 1, 6},
                        {7, 8, 0},
                }
        );
        assertFalse(board.isGoal());
    }

}
