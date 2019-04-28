package com.kondenko.week4.assignment;

import java.util.Arrays;
import java.util.function.Consumer;

import edu.princeton.cs.algs4.Stack;

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
        int[][] twin = copyOf(blocks);
        int a = 0;
        int b = 1;
        while (blocks[a][a] == 0) a++;
        while (blocks[b][b] == 0) b++;
        swap(twin, a, a, b, b);
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
        return Arrays.deepEquals(blocks, board.blocks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(blocks);
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        final Stack<Board> neighbors = new Stack<>();
        int[] emptyBlockCoordinates = findEmptyBlock();
        int x = emptyBlockCoordinates[0];
        int y = emptyBlockCoordinates[1];
        produceIfInBounds(x, y, x - 1, y, neighbors::push);
        produceIfInBounds(x, y, x + 1, y, neighbors::push);
        produceIfInBounds(x, y, x, y - 1, neighbors::push);
        produceIfInBounds(x, y, x, y + 1, neighbors::push);
        return neighbors;
    }

    private void produceIfInBounds(int emptyX, int emptyY, int i, int j, Consumer<Board> accept) {
        if (isInBounds(i, j)) {
            accept.accept(new Board(copyBlocksAndSwap(emptyX, emptyY, i, j)));
        }
    }

    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(blocks.length).append("\n");
        for (int[] row  : blocks) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", row[j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int[] findEmptyBlock() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("Empty block not found");
    }

    ;

    private int[][] copyBlocksAndSwap(int x, int y, int i, int j) {
        int[][] copy = copyOf(blocks);
        swap(copy, x, y, i, j);
        return copy;
    }

    private boolean isInBounds(int i, int j) {
        return isInBounds(i) && isInBounds(j);
    }

    private boolean isInBounds(int i) {
        return i >= 0 && i < dimension();
    }

    protected final int[] goalPosition(int n) {
        if (n == 0) {
            int lastIndex = blocks.length - 1;
            return new int[]{lastIndex, lastIndex};
        }
        n--;
        return new int[]{(int) Math.floor(n / blocks.length), n % blocks.length};
    }

    private static void swap(int[][] array, int x_from, int y_from, int x_to, int y_to) {
        int temp = array[x_from][y_from];
        array[x_from][y_from] = array[x_to][y_to];
        array[x_to][y_to] = temp;
    }

    private static int[][] copyOf(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, copy[i], 0, array.length);
        }
        return copy;
    }

}