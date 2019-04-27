package com.kondenko.week4.assignment.tests.board;

import com.kondenko.week4.assignment.Board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/* The number of out-of-place blocks */
public class Hamming {

    @Test
    public void hamming1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertEquals(0, board.hamming());
    }

    @Test
    public void hamming2() {
        Board board = new Board(
                new int[][]{
                        {7, 8, 0},
                        {1, 2, 3},
                        {4, 5, 6},
                }
        );
        assertEquals(9, board.hamming());
    }

    @Test
    public void hamming3() {
        Board board = new Board(
                new int[][]{
                        {0, 2, 3},
                        {4, 5, 6},
                        {7, 8, 1},
                }
        );
        assertEquals(2, board.hamming());
    }

}
