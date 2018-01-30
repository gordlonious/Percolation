import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
	private int times;
	private int n;
	private Percolation[] p;
	private int[] randomIntRange;

	public PercolationStats(int N, int T) { 
		this.times = T;
		this.n = N;
		for(int i = 0; i < T; i++) {
			p[i] = new Percolation(N);
			this.runExperiment(i);
		}
		this.randomIntRange = new int[N];
		for(int i = 0; i < N; i++) {
			this.randomIntRange[i] = i;
		}
	}
	public double mean() { return 0.00; } // return sample mean
	public double stddev() { return 0.00; } // return standard deviation
	public double confidenceLow() { return 0.00; }
	public double confidenceHigh() { return 0.00; }

	private void runExperiment(int index) {
		// uniformly random i and j
		// p.open(i, j)
		// does it percolate? if so, calculate stats
		// else keep opening random (i, j) sites

	}
	private int extractRandomInt() {	
		StdRandom.shuffle(this.randomIntRange); // make int[] uniformly random
		return this.randomIntRange[0]; // return arbitrary int
	}
}
