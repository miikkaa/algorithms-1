import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int gridSize;
  private final boolean[][] openSites;
  private int noOfOpenSites = 0;
  private final WeightedQuickUnionUF data;

  private final int virtualTop = 0;
  private final int virtualBottom;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(final int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    this.gridSize = n;
    this.openSites = new boolean[n][n];
    this.data = new WeightedQuickUnionUF(n * n + 2); // account for the virtual top and bottom
    this.virtualBottom = n * n + 1;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validate(row, col);

    if (isOpen(row, col)) {
      return;
    }

    openSites[row - 1][col - 1] = true;
    noOfOpenSites++;

    int site = getIndex(row, col);

    // connect the site to the virtual top
    if (row == 1) {
      data.union(virtualTop, site);
    }

    // connect the site to the virtual bottom
    if (row == gridSize) {
      data.union(virtualBottom, site);
    }

    // connect up
    if (row > 1 && isOpen(row - 1, col)) {
      data.union(getIndex(row - 1, col), site);
    }

    // connect down
    if (row < gridSize && isOpen(row + 1, col)) {
      data.union(getIndex(row + 1, col), site);
    }

    // connect left
    if (col > 1 && isOpen(row, col - 1)) {
      data.union(getIndex(row, col - 1), site);
    }

    // connect right
    if (col < gridSize && isOpen(row, col + 1)) {
      data.union(getIndex(row, col + 1), site);
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row, col);
    return openSites[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validate(row, col);
    int site = getIndex(row, col);
    return data.find(virtualTop) == data.find(site);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return noOfOpenSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return data.find(virtualTop) == data.find(virtualBottom);
  }

  private void validate(int row, int col) {
    if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
      throw new IllegalArgumentException();
    }
  }

  private int getIndex(int row, int col) {
    validate(row, col);
    return (row - 1) * gridSize + (col - 1) + 1; // the virtual top shifts all elements by one
  }

  // test client (see below)
  public static void main(String[] args) {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 1);
    percolation.open(2, 1);
    percolation.open(3, 1);
    percolation.open(4, 3);
    percolation.open(2, 5);
    percolation.open(4, 5);
    percolation.open(3, 3);
    percolation.open(4, 2);
    percolation.open(2, 3);
    percolation.open(5, 5);
    percolation.open(2, 4);
    percolation.open(3, 5);
    percolation.open(4, 1);

    System.out.println("percolates? " + percolation.percolates());

    // "Visualize" the data structure
    for (int i = 0; i < percolation.gridSize; i++) {
      System.out.println("-".repeat(percolation.gridSize * 5 + 4));
      System.out.print("| ");
      for (int j = 0; j < percolation.gridSize; j++) {
        System.out.print((percolation.isOpen(i + 1, j + 1) ? "  o  " : "  .  "));
      }
      System.out.println(" |");
    }
    System.out.println("-".repeat(percolation.gridSize * 5 + 4));
  }
}
