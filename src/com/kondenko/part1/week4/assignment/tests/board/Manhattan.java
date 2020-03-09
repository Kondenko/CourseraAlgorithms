package com.kondenko.part1.week4.assignment.tests.board;

import com.kondenko.part1.week4.assignment.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/* Manhattan distance */
public class Manhattan {

    @Test
    public void manhattan1() {
        Board board = new Board(
                new int[][]{
                        {0, 1, 3},
                        {4, 2, 5},
                        {7, 8, 6},
                }
        );
        assertEquals(4, board.manhattan());

    }

    @Test
    public void manhattan2() {
        Board board = new Board(
                new int[][]{
                        {1, 0},
                        {2, 3},
                }
        );
        assertEquals(3, board.manhattan());
    }

}
