/*--------------------------------------------
 * Author: Pao Im
 * Written: 02/15/2014
 * Last updated: 02/16/2014
 * 
 * Compilation: javac PercolationStats.java
 * Execution: java PercolationStats
 *--------------------------------------------*/
public class PercolationStats {

	private int tt;
	private double thresholds[];
	private final double CON_INTERVAL = 1.96;

	/*-------------------------------------------------------------------
	 * perform T independent computational experiments on an N-by-N grid
	 *-------------------------------------------------------------------*/
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}

		tt = T;
		int quare = N * N;
		Percolation percolation;
		thresholds = new double[T];

		for (int t = 0; t < T; t++) {
			int count = 0;
			percolation = new Percolation(N);

			while (count < quare) {

				int i = StdRandom.uniform(N) + 1;
				int j = StdRandom.uniform(N) + 1;

				if (percolation.isOpen(i, j))
					continue;

				count++;
				percolation.open(i, j);

				if (percolation.percolates()) {
					thresholds[t] = count / (double) (quare);
					break;
				}
			}
		}
	}

	/*--------------------------------------
	 * sample mean of percolation threshold
	 *--------------------------------------*/
	public double mean() {
		return StdStats.mean(thresholds);
	}

	/*---------------------------------------------------
	 * sample standard deviation of percolation threshold
	 *---------------------------------------------------*/
	public double stddev() {
		return StdStats.stddev(thresholds);
	}

	/*---------------------------------------------------
	 * returns lower bound of the 95% confidence interval
	 *---------------------------------------------------*/
	public double confidenceLo() {
		double m = mean();
		double s = stddev();
		double confideneLow = m - ((CON_INTERVAL * s) / Math.sqrt(tt));

		return confideneLow;
	}

	/*---------------------------------------------------
	 * returns upper bound of the 95% confidence interval
	 *---------------------------------------------------*/
	public double confidenceHi() {
		double m = mean();
		double s = stddev();
		double confideneHigh = m + ((CON_INTERVAL * s) / Math.sqrt(tt));

		return confideneHigh;
	}

	/*-----------------------------
	 * test client, described below
	 *-----------------------------*/
	public static void main(String[] args) {
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats ps = new PercolationStats(N, T);

		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + " , " + ps.confidenceHi());
	}
}