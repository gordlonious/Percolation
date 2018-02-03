import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

 /**
 * @author Gordon Portzline
 * Aidan Hubert
 * Creates a PercolationStats object that runs experiments on an N by N grid T times
 */  
public class PercolationStats {
	private int times, n;
	private Percolation[] p;
	private int[] openCount;

	/*
	 * Creates a PercolationStats object that runs experiments on an N by N grid T times
	 */
	public PercolationStats(int N, int T) { 
		if(N <= 0 || T <= 0) {
			throw new IllegalArgumentException("N and T cannot be 0 or less: 'PercolationStats'");
		}
		this.times = new Integer(T);
		this.n = new Integer(N);
		this.openCount = new int[T];
		this.p = new Percolation[T];
		for(int i = 0; i < T; i++) {
			p[i] = new Percolation(N);
			this.runExperiment(i);
		}
	}
	
	/*
	 * Calculates the mean for all T percolation thresholds
	 */
	public double mean() {
		double[] psThresh = new double[openCount.length];
		for (int i = 0; i < psThresh.length; i++) {
			double tmpCount = openCount[i];
			double tmpSize = (n*n);
			psThresh[i] = tmpCount/tmpSize;
		}
		return StdStats.mean(psThresh);
	}
	
	/*
	 * Calculates the standard deviation for all T percolation thresholds
	 */
	public double stddev() { 
		double[] psThresh = new double[openCount.length];
		for (int i = 0; i < psThresh.length; i++) {
			double oc = openCount[i];
			double s = (n*n);
			psThresh[i] = new Double((oc / s) * (oc / s));
		}
		return StdStats.stddev(psThresh);
	}
	
	/*
	 * Calculates the low end of the 95% confidence interval for all T percolation thresholds
	 */
	public double confidenceLow() { 
		double m = this.mean();
		double tstddev = new Double(1.96 * this.stddev());
		double sqrtN = Math.sqrt(this.n);
		return (m - (tstddev / sqrtN));
	}
	
	/*
	 * Calculates the high end of the 95% confidence interval for all T percolation thresholds
	 */
	public double confidenceHigh() { 
		double m = this.mean();
		double tstddev = (1.96 * this.stddev());
		double sqrtN = Math.sqrt(this.n);
		return (m + (tstddev / sqrtN));
	}
	
	
	private double threshold(int index) {
		return (this.openCount[index] / this.n);
	}
	
	private void runExperiment(int index) {
		int tOpenCount = 0;
		while(!this.p[index].percolates()) {
			int i = StdRandom.uniform(0, this.n);
			int j = StdRandom.uniform(0, this.n);
			if(!this.p[index].isOpen(i, j)) {
				this.p[index].open(i, j);
				tOpenCount++;
			}
		}
		this.openCount[index] = tOpenCount;
	}
	
	
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(200, 100);
		System.out.printf(" mean was: %.15f%n standard deviation was: %.15f%n confidenceLow was: %.15f%n confidenceHigh was: %.15f%n", ps.mean(), ps.stddev(), ps.confidenceLow(), ps.confidenceHigh());
	}
	
}
