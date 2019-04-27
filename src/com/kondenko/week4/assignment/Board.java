package com.kondenko.week4.assignment;

import com.kondenko.ArrayUtils;

import java.util.Arrays;

/*
1  2  3
4  5  6
8  7  0
 */
public class Board {

    private int[][] blocks;

    /**
     * construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * board dimension n
     */
    public int dimension() {
        return blocks.length;
    }

    /**
     * number of blocks out of place
     */
    public int hamming() {
        int num = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                int block = blocks[i][j];
                boolean isLastNumber = i == blocks.length - 1 && j == blocks.length - 1;
                int correctBlock = isLastNumber ? 0 : i * (blocks.length) + j + 1;
                if (block != correctBlock) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int distancesSum = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                int block = blocks[i][j];
                int[] goalPosition = goalPosition(block);
                int distanceX = goalPosition[0];
                int distanceY = goalPosition[1];
                int manhattanDistance = (distanceX - i) + (distanceY - j);
                distancesSum += Math.abs(manhattanDistance);
            }
        }
        return distancesSum;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        if (blocks.length < 1) {
            throw new IllegalArgumentException("Can't create a twin of a 1-element array");
        }
        int[][] twin = ArrayUtils.copyOf(blocks);
        ArrayUtils.swap(twin, 0, 1);
        return new Board(twin);
    }

    /**
     * does this board equal y?
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(blocks, board.blocks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(blocks);
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        return null;
    }

    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        return null;
    }

    protected final int[] goalPosition(int n) {
        if (n == 0) {
            int lastIndex = blocks.length - 1;
            return new int[]{lastIndex, lastIndex};
        }
        n--;
        return new int[]{(int) Math.floor(n / blocks.length), n % blocks.length};
    }

    /**
     * unit tests (not graded)
     */
    public static void main(String[] args) {

    }

}