package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private Percolation p;
    private int N;
    private int numTrials;
    private double[] data;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.N = N;
        numTrials = T;
        data = new double[numTrials];
        performExperiments(pf);
    }

    private void performExperiments(PercolationFactory pf) {
        for (int i = 0; i < numTrials; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N),StdRandom.uniform(N));
            }
            data[i] = p.numberOfOpenSites() / Double.valueOf(N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(data);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return StdStats.mean(data) - 1.96 * StdStats.stddev(data) / Math.sqrt(numTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return StdStats.mean(data) + 1.96 * StdStats.stddev(data) / Math.sqrt(numTrials);
    }

}
