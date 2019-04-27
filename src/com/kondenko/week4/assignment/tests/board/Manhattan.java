package com.kondenko.week4.assignment.tests.board;

import com.kondenko.week4.assignment.Board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/* Manhattan distance */
public class Manhattan {

    @Test
    public void manhattan1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertEquals(0, board.manhattan());

    }

    @Test
    public void manhattan2() {
        Board board = new Board(
                new int[][]{
                        {5, 2, 3},
                        {4, 1, 6},
                        {7, 8, 0},
                }
        );
        assertEquals(2 * 2, board.manhattan());
    }

}
