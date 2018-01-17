import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private final double[] ratio;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {

	if (n < 1 || trials < 1)
	    throw new IllegalArgumentException();

	this.trials = trials;
	ratio = new double[trials];
	for (int i = 0; i < trials; i++) {
	    Percolation p = new Percolation(n);
	    while (!p.percolates()) {
		int row = StdRandom.uniform(1, n + 1);
		int col = StdRandom.uniform(1, n + 1);
		while (p.isOpen(row, col)) {
		    row = StdRandom.uniform(1, n + 1);
		    col = StdRandom.uniform(1, n + 1);
		}
		p.open(row, col);
	    }
	    ratio[i] = ((double) p.numberOfOpenSites()) / (n * n);
	}
    }

    // sample mean of percolation threshold
    public double mean() {
	return StdStats.mean(ratio);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
	if (trials == 1)
	    return Double.NaN;
	return StdStats.stddev(ratio);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
	return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
	return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (described below)
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	int t = Integer.parseInt(args[1]);

	PercolationStats p = new PercolationStats(n, t);
	System.out.println("mean = " + p.mean());
	System.out.println("stddev = " + p.stddev());
	System.out.println("95% confidence interval = [" + p.confidenceLo() + "," + p.confidenceHi() + "]");
    }

}
