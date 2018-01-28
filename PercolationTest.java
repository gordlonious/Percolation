public class PercolationTest {
	private Percolation p;
	private int pSize;
	public PercolationTest(int n) { 
		this.p = new Percolation(n); 
		this.pSize = n;
	}
	
	public void runAll() {
		System.out.println(TestHorizontalPercolation());
		System.out.println(TestDiagonalLinePercolation());
	}
	
	public boolean TestHorizontalPercolation() {
		for(int i = 0; i < this.pSize; i++) {
			this.p.open(pSize - 1, i);
		}
		return !this.p.percolates(); // test passes if horizontal line of open sites does not percolate
	}
	
	public boolean TestDiagonalLinePercolation() {
		for (int i = 0; i < this.pSize; i++) {
			this.p.open(pSize - (i+1), i);
		}
		return !this.p.percolates();
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			PercolationTest t = new PercolationTest(5);
			t.runAll();
		}
		else {
			//PercolationVisualizer.draw(new Percolation(5), 5);
			PercolationVisualizer.main(new String[1]);
		}
	}
}