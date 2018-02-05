import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Aidan Hubert,
 * Gordon Portzline
 */
public class Percolation 
{
  private WeightedQuickUnionUF wUnionUF, fullQuickUnionUF;
  private boolean[][] openSites;
  private int gridSize, top, bottom;

  /**
   * Creates a new percolation simulation, with all sites initialized to blocked
   */
  public Percolation(int n) 
  {
    if (n <= 0)
      throw new IllegalArgumentException("Illegal Percolation parameter");
    this.gridSize = n;
    top = (n * n) + 4;
    bottom = (n * n) + 2;
    // initialize all sites to a blocked status
    this.openSites = new boolean[n][n];
    wUnionUF = new WeightedQuickUnionUF((n * n) + 5);
    fullQuickUnionUF = new WeightedQuickUnionUF((n * n) + 5);
    for (int i = 0; i < n; i++)
    {
      wUnionUF.union(twoDToOneD(i,0), top);
      fullQuickUnionUF.union(twoDToOneD(i,0), top);
    }
    for (int i = 0; i < n; i++)
      wUnionUF.union(twoDToOneD(i,n-1), bottom);
  }
  /**
   * Opens a specific site, and unions it to any surrounding open sites.
   */
  public void open(int row, int col) 
  {
    if(openSites[col][row])
      return;
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      throw new IndexOutOfBoundsException("Open parameter(s) out of range");
    openSites[col][row] = true;
    if (isInBounds(row, col-1) && isOpen(row, col - 1))
    {
      wUnionUF.union(twoDToOneD(col, row), twoDToOneD(col - 1, row));
      fullQuickUnionUF.union(twoDToOneD(col, row), twoDToOneD(col - 1, row));
    }
    if (isInBounds(row, col+1) && isOpen(row, col + 1))
    {
      wUnionUF.union(twoDToOneD(col, row), twoDToOneD(col + 1, row));
      fullQuickUnionUF.union(twoDToOneD(col, row), twoDToOneD(col + 1, row));
    }
    if (isInBounds(row-1, col) && isOpen(row - 1, col))
    {
      wUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row - 1));
      fullQuickUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row - 1));
    }
    if (isInBounds(row+1, col) && isOpen(row + 1, col))
    {
      wUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row + 1));
      fullQuickUnionUF.union(twoDToOneD(col, row),twoDToOneD(col, row + 1));
    }
  }
   /**
   * Checkes if specific site is open, and returns the result
   */
  public boolean isOpen(int row, int col) 
  {
    // has been opened, can now count as connecting other nodes together
    if (row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1))
      throw new IndexOutOfBoundsException("IsOpen parameter(s) out of range");
    return openSites[col][row];
  }
  /**
   * Checks if a specific site is connected to open site in top row
   */
  public boolean isFull(int row, int col) 
  {
    int i = twoDToOneD(col, row);
    return isOpen(row, col) && fullQuickUnionUF.connected(i, top);
  }
  /**
   * Checks if the system percolates
   */
  public boolean percolates() 
  {
    return wUnionUF.connected(bottom, top);
  }

  private boolean isInBounds(int row, int col)
  {
    return !(row < 0 || row > (gridSize - 1) || col < 0 || col > (gridSize - 1));
  }

  private int twoDToOneD(int x, int y) 
  {
    return y + (x * gridSize);
  }
}
