package com.kondenko.week4.assignment;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardTest {

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

    @Test
    public void goalPosition1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertArrayEquals(new int[]{0, 0}, board.goalPosition(1));
    }

    @Test
    public void goalPosition2() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertArrayEquals(new int[]{2, 2}, board.goalPosition(0));
    }

    @Test
    public void goalPosition3() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertArrayEquals(new int[]{0, 2}, board.goalPosition(3));
    }

    @Test
    public void goalPosition4() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        assertArrayEquals(new int[]{1, 0}, board.goalPosition(4));
    }

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