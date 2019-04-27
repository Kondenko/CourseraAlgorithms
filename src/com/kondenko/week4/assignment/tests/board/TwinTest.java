package com.kondenko.week4.assignment.tests.board;

import com.kondenko.week4.assignment.Board;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TwinTest {

    @Test
    public void twin() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        Board twin = board.twin();
        assertNotEquals(board, twin);

    }

}