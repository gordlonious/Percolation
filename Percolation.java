public class Percolation {
  private boolean[][] openSites;
  public Percolation (int n) {
   // initialize all sites to a blocked status
   this.openSites = new boolean[n][n];
   for(boolean[] row : this.openSites) {
     for(boolean site : row) {
       site = false;
    }
   }
  }
  public void Open(int row, int col) { 
    openSites[row][col] = true;
  }
  public boolean IsOpen(int row, int col) {
    // has been opened, can now count as connecting other nodes together
   return openSites[row][col]; 
  }
  public boolean IsFull(int row, int col) {
    // Is open but also there exists a path from node(row, col) using only open nodes to the top row
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
