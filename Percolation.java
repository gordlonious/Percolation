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
      wUnionUF.union(twoDToOneD(i,0), top);
    for (int i = 0; i < n; i++)
      wUnionUF.union(twoDToOneD(i,n-1), bottom);
  }

  public void open(int row, int col) {
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      throw new IndexOutOfBoundsException("Open parameter(s) out of range");
    openSites[col][row] = true;
    if (isInBounds(row, col-1) && isOpen(row, col - 1))
      wUnionUF.union(twoDToOneD(col, row), twoDToOneD(col - 1, row));
    if (isInBounds(row, col+1) && isOpen(row, col + 1))
      wUnionUF.union(twoDToOneD(col, row), twoDToOneD(col + 1, row));
    if (isInBounds(row-1, col) && isOpen(row - 1, col))
      wUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row - 1));
    if (isInBounds(row+1, col) && isOpen(row + 1, col))
      wUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row + 1));
  }
 
  public boolean isOpen(int row, int col) {
    // has been opened, can now count as connecting other nodes together
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      throw new IndexOutOfBoundsException("IsOpen parameter(s) out of range");
    return openSites[col][row];
  }

  public boolean isFull(int row, int col) {
    // connected to open site in top row
    int i = twoDToOneD(col, row);
    return wUnionUF.connected(i, top) && isOpen(row, col);
  }

  public boolean percolates() {
    // is there a Full node on the bottom row from which a path exists to the top row
    return wUnionUF.connected(bottom, top);
  }

  private boolean isInBounds(int row, int col)
  {
    return !(row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1));
  }

  private int twoDToOneD(int x, int y) {
    return y + (x * gridSize);
  }

  public static void main(String args[]) 
  {
    // test percolation class
    Scanner input = new Scanner(System.in);
    Percolation p = new Percolation(10);
    for (int i = 0; i < 10; i++) 
    {
      p.open(i, 5);
    }
    //System.out.println(p.wUnionUF.find(104));
    //System.out.println(p.IsFull(5, 5));
    System.out.println(p.percolates());

    input.close();
  }
}
