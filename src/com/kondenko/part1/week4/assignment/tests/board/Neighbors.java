package com.kondenko.part1.week4.assignment.tests.board;

import com.kondenko.part1.week4.assignment.Board;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class Neighbors {

    @Test
    public void neighbors1() {
        Board board = new Board(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                }
        );
        Board[] expected = {
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 0},
                        {7, 8, 6},
                }),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 0, 8},
                }),
        };
        Iterable<Board> actual = board.neighbors();
        assertThat(actual, containsInAnyOrder(expected));
    }

    @Test
    public void neighbors2() {
        Board board = new Board(
                new int[][]{
                        {8, 1, 3},
                        {4, 2, 0},
                        {7, 6, 5},
                }
        );
        Board[] expected = {
                new Board(new int[][]{
                        {8, 1, 0},
                        {4, 2, 3},
                        {7, 6, 5},
                }),
                new Board(new int[][]{
                        {8, 1, 3},
                        {4, 0, 2},
                        {7, 6, 5},
                }),
                new Board(new int[][]{
                        {8, 1, 3},
                        {4, 2, 5},
                        {7, 6, 0},
                }),
        };
        Iterable<Board> actual = board.neighbors();
        assertThat(actual, containsInAnyOrder(expected));
    }

}