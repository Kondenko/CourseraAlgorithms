package com.kondenko.part1.week4.assignment.tests.board;

import com.kondenko.part1.week4.assignment.Board;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.junit.Assert.assertNotEquals;

public class TwinTest {

    @Test
    public void twin1() {
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

    @Test
    public void twin2() {
        Board board = new Board(
                new int[][]{
                        {1, 2},
                        {3, 0},
                }
        );
        List<Board> twins = List.of(
                new Board(
                        new int[][]{
                                {3, 2},
                                {1, 0},
                        }
                ),
                new Board(
                        new int[][]{
                                {1, 3},
                                {2, 0},
                        }
                ),
                new Board(
                        new int[][]{
                                {2, 1},
                                {3, 0},
                        }
                )
        );
        Board twin = board.twin();
        assertThat(twin, is(in(twins)));
    }
    @Test
    public void twin3() {
        Board board = new Board(
                new int[][]{
                        {6, 1, 2, 3},
                        {5, 0, 7, 4},
                        {9, 10, 11, 8},
                        {13, 14, 15, 12},
                }
        );
        Board twin = board.twin();
        assertNotEquals(board, twin);
    }

    @Test
    public void twin4() {
        Board board = new Board(
                new int[][]{
                        {2, 3},
                        {1, 0},
                }
        );
        List<Board> twins = List.of(
                new Board(
                        new int[][]{
                                {3, 2},
                                {1, 0},
                        }
                ),
                new Board(
                        new int[][]{
                                {1, 3},
                                {2, 0},
                        }
                ),
                new Board(
                        new int[][]{
                                {2, 1},
                                {3, 0},
                        }
                )
        );
        Board twin = board.twin();
        assertThat(twin, is(in(twins)));
        assertNotEquals(board, twin);
    }

}