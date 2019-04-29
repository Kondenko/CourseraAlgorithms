package com.kondenko.week4.assignment;

import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int[][] blocks;

    private final Stack<Board> neighbors = new Stack<>();

    private Board twin = null;

    private int[] emptyBlock = null;

    private int hamming = -1;

    private int manhattan = -1;

    /**
     * construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        this.blocks = copyOf(blocks);
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
        if (hamming == -1) {
            hamming = 0;
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks.length; j++) {
                    int block = blocks[i][j];
                    boolean isLastNumber = i == dimension() - 1 && j == dimension() - 1;
                    int correctBlock = i * (dimension()) + j + 1;
                    if (block != correctBlock && !isLastNumber) {
                        hamming++;
                    }
                }
            }
        }
        return hamming;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (manhattan == -1) {
            manhattan = 0;
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks.length; j++) {
                    int block = blocks[i][j];
                    if (block != 0) {
                        int[] goalPosition = goalPosition(block);
                        int goalX = goalPosition[0];
                        int goalY = goalPosition[1];
                        manhattan += Math.abs(goalX - i) + Math.abs(goalY - j);
                    }
                }
            }
        }
        return manhattan;
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
        } else if (twin == null) {
            int[][] twinArray = copyOf(blocks);
            int[] emptyBlockCoords = findEmptyBlock();
            int x = emptyBlockCoords[0];
            int y = emptyBlockCoords[1];
            Stack<int[]> neighbors = getLegalNeighbors(x, y);
            int[] from = neighbors.pop();
            int[] to = neighbors.pop();
            swap(twinArray, from[0], from[1], to[0], to[1]);
            twin = new Board(twinArray);
        }
        return twin;
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

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        if (neighbors.isEmpty() && dimension() > 1) {
            int[] emptyBlockCoordinates = findEmptyBlock();
            int x = emptyBlockCoordinates[0];
            int y = emptyBlockCoordinates[1];
            getLegalNeighbors(x, y).forEach(coords -> {
                neighbors.push(new Board(copyBlocksAndSwap(x, y, coords[0], coords[1])));
            });
        }
        return neighbors;
    }

    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(blocks.length).append("\n");
        for (int[] row : blocks) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", row[j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private Stack<int[]> getLegalNeighbors(int x, int y) {
        Stack<int[]> neighborCoods = new Stack<>();
        if (isInBounds(x - 1, y)) neighborCoods.push(new int[]{x - 1, y});
        if (isInBounds(x + 1, y)) neighborCoods.push(new int[]{x + 1, y});
        if (isInBounds(x, y - 1)) neighborCoods.push(new int[]{x, y - 1});
        if (isInBounds(x, y + 1)) neighborCoods.push(new int[]{x, y + 1});
        return neighborCoods;
    }

    private int[] findEmptyBlock() {
        if (emptyBlock == null) {
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks.length; j++) {
                    if (blocks[i][j] == 0) {
                        emptyBlock = new int[]{i, j};
                    }
                }
            }
        }
        return emptyBlock;
    }

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

    private int[] goalPosition(int n) {
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