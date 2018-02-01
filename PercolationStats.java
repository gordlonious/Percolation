import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
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
			psThresh[i] = openCount[i] / (n*n);
		}
		return StdStats.mean(psThresh);
	} // return sample mean (avg over all T)
	public double stddev() { 
		double[] psThresh = new double[openCount.length];
		for (int i = 0; i < psThresh.length; i++) {
			psThresh[i] = ((openCount[i] / (n*n)) * (openCount[i] / (n*n)));
		}
		return StdStats.stddev(psThresh);
	} // return standard deviation (avg over all T)
	public double confidenceLow() { return 0.00; }
	public double confidenceHigh() { return 0.00; }
	private double threshold(int index) {
		return (this.openCount[index] / this.n);
	}
	private void runExperiment(int index) {
		int tOpenCount = 0;
		while(!this.p[index].percolates()) {
			this.p[index].open(StdRandom.uniform(0, this.n), StdRandom.uniform(0, this.n));
			tOpenCount++;
		}
		this.openCount[index] = tOpenCount;
	}
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(200, 100);
		System.out.println(ps.mean());
	}
}
