package com.kondenko.week1.assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double INTERVAL_CONST = 1.96;

    private final int trials;

    private int[] openSiteNumbers;

    /*
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Arguments should be > 0");
        }
        this.trials = trials;
        openSiteNumbers = new int[trials];
        for (int i = 0; i < trials; i++) {
            openSiteNumbers[i] = performTrial(n);
        }
    }

    private int performTrial(int n) {
        Percolation p = new Percolation(n);
        do {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            if (!p.isOpen(row, col)) {
                p.open(row, col);
            }
        } while (!p.percolates());
        return p.numberOfOpenSites();
    }

    /*
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(openSiteNumbers);
    }

    /*
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(openSiteNumbers);
    }

    /*
     * low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - getSecondConfidencePart();
    }

    /*
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + getSecondConfidencePart();
    }

    private double getSecondConfidencePart() {
        return INTERVAL_CONST * Math.sqrt(stddev() / trials);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("mean = %f", mean()));
        sb.append('\n');
        sb.append(String.format("stddev = %f", stddev()));
        sb.append('\n');
        sb.append(String.format("95%% confidence interval = [%f, %f]", confidenceLo(), confidenceHi()));
        sb.append('\n');
        return sb.toString();
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.out.println(stats);
    }

}