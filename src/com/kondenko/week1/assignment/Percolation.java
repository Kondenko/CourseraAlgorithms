package com.kondenko.week1.assignment;

@SuppressWarnings("WeakerAccess")
public class Percolation {

    private static final int TOP_VIRTUAL_SITE_ID = 0;

    private int n;

    private LogUF uf;

    private boolean[][] isOpen;

    private int numberOfOpenSites = 0;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException(String.format("N should be > 0 (was %d)", n));
        this.n = n;
        uf = new LogUF((n * n) + 2);
        isOpen = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int firstRowQ = n - 1 + coordsToId(1, i);
            int lastRowQ = coordsToId(n - 1, i);
            uf.union(TOP_VIRTUAL_SITE_ID, firstRowQ);
            uf.union(getBottomVirtualSiteId(), lastRowQ);
        }
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        assertRowColumn(row, col);
        numberOfOpenSites++;
        isOpen[row - 1][col - 1] = true;
        connectNeighbours(row, col);
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
        return isOpen(row, col) && uf.connected(coordsToId(row, col), TOP_VIRTUAL_SITE_ID);
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
        return uf.connected(0, getBottomVirtualSiteId());
    }

    public int coordsToId(int row, int col) {
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
        return num >= 0 && num <= n;
    }

    private int getBottomVirtualSiteId() {
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