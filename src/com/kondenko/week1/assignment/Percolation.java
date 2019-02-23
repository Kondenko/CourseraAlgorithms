package com.kondenko.week1.assignment;

@SuppressWarnings("WeakerAccess")
public class Percolation {

    private int n;

    private LogUF uf;

    private boolean[][] isOpen;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException(String.format("N should be > 0 (was %d", n));
        this.n = n;
        uf = new LogUF((n * n) + 2);
        isOpen = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int firstRowQ = n + coordsToId(1, i);
            int lastRowQ = coordsToId(n, i);
            uf.union(0, firstRowQ);
            uf.union(getBottomVirtualSite(), lastRowQ);
        }
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        assertRowColumn(row, col);
        isOpen[row][col] = true;
        // TODO Connect the neighbors
    }


    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        assertRowColumn(row, col);
        return isOpen[row][col];
    }


    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        assertRowColumn(row, col);
        return false;
    }


    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return 0;
    }


    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(0, getBottomVirtualSite());
    }

    public int coordsToId(int row, int col) {
        return col + (n * (row - 1));
    }

    private void assertRowColumn(int row, int col) {
        if (!isInRange(row) || !isInRange(col))
            throw new IllegalArgumentException(String.format("Coordinates are outside the grid: row=%d, col=%d", row, col));
    }

    private void unionNeighbours(int row, int col) {

    }

    private boolean isInRange(int num) {
        return num >= 0 && num <= n;
    }

    private int getBottomVirtualSite() {
        return n * n + 1;
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