import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;
public class PercolationStats {
	private int times, n;
	private Percolation[] p;
	private int[] openCount;

	public PercolationStats(int N, int T) { 
		this.times = new Integer(T);
		this.n = new Integer(N);
		this.openCount = new int[T];
		this.p = new Percolation[T];
		for(int i = 0; i < T; i++) {
			p[i] = new Percolation(N);
			this.runExperiment(i);
		}
	}
	public double mean() {
		double[] psThresh = new double[openCount.length];
		for (int i = 0; i < psThresh.length; i++) {
			double tmpCount = new Double(openCount[i]);
			double tmpSize = new Double((n*n));
			psThresh[i] = tmpCount/tmpSize;
		}
		return StdStats.mean(psThresh);
	}
	public double stddev() { 
		double[] psThresh = new double[openCount.length];
		for (int i = 0; i < psThresh.length; i++) {
			double oc = new Double(openCount[i]);
			double s = new Double(n*n);
			psThresh[i] = new Double((oc / s) * (oc / s));
		}
		return StdStats.stddev(psThresh);
	}
	public double confidenceLow() { 
		double m = this.mean();
		double tstddev = new Double(1.96 * this.stddev());
		double sqrtN = Math.sqrt(this.n);
		return new Double(m - (tstddev / sqrtN));
	}
	public double confidenceHigh() { 
		double m = this.mean();
		double tstddev = new Double(1.96 * this.stddev());
		double sqrtN = Math.sqrt(this.n);
		return new Double(m + (tstddev / sqrtN));
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
