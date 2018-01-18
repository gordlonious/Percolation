public class Percolation {
  public Percolation (int n) {
   // initialize all sites to a blocked status 
  }
  public void Open(int row, int col) { }
  public boolean IsOpen(int row, int col) {
    // has been opened, can now count as connecting other nodes together
   return false; 
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