package com.kondenko.week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int topVirtualSiteId = 0;

    private final int bottomVirtualSiteId;

    private int n;

    private WeightedQuickUnionUF uf;

    private boolean[][] isOpen;

    private int numberOfOpenSites = 0;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException(String.format("N should be > 0 (was %d)", n));
        this.n = n;
        bottomVirtualSiteId = n * n + 1;
        uf = new WeightedQuickUnionUF((n * n) + 2);
        isOpen = new boolean[n][n];
        for (int i = 1; i <= n && n > 1; i++) {
            int firstRowQ = coordsToId(1, i);
            uf.union(topVirtualSiteId, firstRowQ);
        }
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        assertRowColumn(row, col);
        if (!isOpen(row, col)) {
            isOpen[row - 1][col - 1] = true;
            numberOfOpenSites++;
            connectNeighbours(row, col);
            int id = coordsToId(row, col);
            if (row == n && uf.connected(id, topVirtualSiteId)) {
                uf.union(id, bottomVirtualSiteId);
            }
        }
    }

    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= n || col >= n) return false;
        return isOpen[row][col];
    }

    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        int id = coordsToId(row, col);
        return isOpen(row, col) && uf.connected(id, topVirtualSiteId) || n == 1;
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(topVirtualSiteId, bottomVirtualSiteId) || n == 1 && isOpen(1, 1);
    }

    private int coordsToId(int row, int col) {
        assertRowColumn(row, col);
        return col + (n * (row - 1));
    }

    private void connectNeighbours(int row, int col) {
        assertRowColumn(row, col);
        connectIfOpen(row, col, row, col - 1);
        connectIfOpen(row, col, row, col + 1);
        connectIfOpen(row, col, row - 1, col);
        connectIfOpen(row, col, row + 1, col);
    }

    private void connectIfOpen(int originalRow, int originalCol, int row, int col) {
        if (isOpen(row, col)) {
            uf.union(coordsToId(originalRow, originalCol), coordsToId(row, col));
        }
    }

    private void assertRowColumn(int row, int col) {
        if (!isInRange(row) || !isInRange(col))
            throw new IllegalArgumentException(String.format("Coordinates are outside the grid: row=%d, col=%d", row, col));
    }

    private boolean isInRange(int num) {
        return num > 0 && num <= n;
    }

    private static void print(int i) {
        System.out.println(i);
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static void print(boolean s) {
        System.out.println(s);
    }

}