package com.kondenko.week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import static edu.princeton.cs.algs4.StdOut.println;

public class Solver {

    private MinPQ<Node> pq = new MinPQ<>();

    private int moves = 0;

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        pq.insert(new Node(initial));
    }

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        // TODO Implementation
        return true;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return moves;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        Queue<Board> steps = new Queue<>();
        Node node;
        do {
            node = pq.delMin();
            steps.enqueue(node.board);
            for (Board neighbor : node.board.neighbors()) {
                if (node.prev == null || !neighbor.equals(node.prev.board)) {
                    Node neighborNode = new Node(node, neighbor);
                    pq.insert(neighborNode);
                }
            }
            moves++;
        } while (!node.board.isGoal());
        return steps;
    }

    private class Node implements Comparable<Node> {

        public final Board board;

        public final Node prev;

        private int manhattanDistance = -1;

        public Node(Board board) {
            this.prev = null;
            this.board = board;
        }

        public Node(Node prev, Board board) {
            this.prev = prev;
            this.board = board;
        }

        public int priority() {
            if (manhattanDistance == -1) manhattanDistance = board.manhattan() + moves;
            return manhattanDistance;
        }

        @Override
        public int compareTo(Node o) {
            return priority() - o.priority();
        }

    }

    /**
     * solve a slider puzzle (given below)
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            println("No solution possible");
        else {
            println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                println(board);
        }
    }

}
