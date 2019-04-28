package com.kondenko.week4.assignment;

import org.junit.Test;

public class GoalPosition {

    /* Goal position */

    @Test
    public void goalPosition1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
//        assertArrayEquals(new int[]{0, 0}, board.goalPosition(1));
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
//        assertArrayEquals(new int[]{2, 2}, board.goalPosition(0));
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
//        assertArrayEquals(new int[]{0, 2}, board.goalPosition(3));
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
//        assertArrayEquals(new int[]{1, 0}, board.goalPosition(4));
    }

}
