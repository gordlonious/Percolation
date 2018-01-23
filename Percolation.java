import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class Percolation {
  private boolean[][] openSites;
  private int gridSize;
  public Percolation (int n) {
   if(n <= 0) throw new IllegalArgumentException("Illegal Percolation parameter");
   this.gridSize = n;
   // initialize all sites to a blocked status
   this.openSites = new boolean[n][n];
   for(boolean[] row : this.openSites) {
     for(boolean site : row) {
       site = false;
    }
   }
  }
  public void Open(int row, int col) {
    if(row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1)) throw new IndexOutOfBoundsException("Open parameter(s) out of range");
    openSites[row][col] = true;
  }
  public boolean IsOpen(int row, int col) {
    // has been opened, can now count as connecting other nodes together
   if(row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1)) throw new IndexOutOfBoundsException("IsOpen parameter(s) out of range");
   return openSites[row][col]; 
  }
  public boolean IsFull(int row, int col) {
    // connected to open site in top row
   return false; 
  }
  public boolean Percolates() {
   // is there a Full node on the bottom row from which a path exists to the top row
    return false;
  }
  
  public static void main(String args[]) {
   // test percolation class

  }
}
