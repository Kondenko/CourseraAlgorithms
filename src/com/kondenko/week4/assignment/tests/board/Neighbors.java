package com.kondenko.week4.assignment.tests.board;

import com.kondenko.week4.assignment.Board;

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
                })
        };
        Iterable<Board> actual = board.neighbors();
        assertThat(actual, containsInAnyOrder(expected));
    }

}