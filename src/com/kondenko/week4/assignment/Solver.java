package com.kondenko.week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private int moves = 0;

    private Queue<Board> solution = new Queue<>();

    private boolean isSolvable;

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Initial board shouldn't be null");
        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(new Node(initial));
        MinPQ<Node> twinPq = new MinPQ<>();
        twinPq.insert(new Node(initial.twin()));
        Node node;
        Node twinNode;
        while (true) {
            node = pq.delMin();
            twinNode = twinPq.delMin();
            solution.enqueue(node.board);
            for (Board neighbor : node.board.neighbors()) {
                if (node.prev == null || !neighbor.equals(node.prev.board)) {
                    pq.insert(new Node(node, neighbor));
                }
            }
            for (Board twinNeighbor : twinNode.board.neighbors()) {
                if (twinNode.prev == null || !twinNeighbor.equals(twinNode.prev.board)) {
                    twinPq.insert(new Node(twinNode, twinNeighbor));
                }
            }
            moves++;
            if (node.board.isGoal()) {
                isSolvable = true;
                break;
            } else if (twinNode.board.isGoal()) {
                isSolvable = false;
                solution = null;
                break;
            }
        }
    }

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return isSolvable() ? moves : -1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return solution;
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
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
