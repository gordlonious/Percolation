package Percolation.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;
import java.util.*;

public class Percolation {
  WeightedQuickUnionUF wUnionUF;
  private boolean[][] openSites;
  private int gridSize, top, bottom;

  public Percolation(int n) {
    if (n <= 0)
      throw new IllegalArgumentException("Illegal Percolation parameter");
    this.gridSize = n;
    top = (n * n) + 4;
    bottom = (n * n) + 2;
    // initialize all sites to a blocked status
    this.openSites = new boolean[n][n];
    wUnionUF = new WeightedQuickUnionUF((n * n) + 5);
    for (int i = 0; i < n; i++)
      wUnionUF.union(i, top);
    for (int i = 0; i < n; i++)
      wUnionUF.union(twoDToOneD(i, n - 1), bottom);

    //  for(boolean[] row : this.openSites) {
    //    for(boolean site : row) {
    //      site = false;
    //   }
    //  }
  }

  public void Open(int row, int col) {
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      throw new IndexOutOfBoundsException("Open parameter(s) out of range");
    openSites[row][col] = true;
    if (IsOpen(row, col - 1))
      wUnionUF.union(twoDToOneD(row, col), twoDToOneD(row, col - 1));
    if (IsOpen(row, col + 1))
      wUnionUF.union(twoDToOneD(row, col), twoDToOneD(row, col + 1));
    if (IsOpen(row - 1, col))
      wUnionUF.union(twoDToOneD(row, col), twoDToOneD(row - 1, col));
    if (IsOpen(row + 1, col))
      wUnionUF.union(twoDToOneD(row, col), twoDToOneD(row + 1, col));
  }

  public boolean IsOpen(int row, int col) {
    // has been opened, can now count as connecting other nodes together
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      return false;
    // throw new IndexOutOfBoundsException("IsOpen parameter(s) out of range");
    return openSites[row][col];
  }

  public boolean IsFull(int row, int col) {
    // connected to open site in top row
    int i = twoDToOneD(row, col);
    wUnionUF.connected(i, top);
    return false;
  }

  public boolean Percolates() {
    // is there a Full node on the bottom row from which a path exists to the top row
    return wUnionUF.connected(bottom, top);
  }

  private int twoDToOneD(int x, int y) {
    return y + (x * gridSize);
  }

  public static void main(String args[]) {
    // test percolation class
    Scanner input = new Scanner(System.in);
    Percolation p = new Percolation(10);
    for (int i = 0; i < 5; i++) {
      p.Open(i, 8);
    }
    //System.out.println(p.wUnionUF.find(104));
    System.out.println(p.IsFull(5, 5));

    input.close();
  }
}
