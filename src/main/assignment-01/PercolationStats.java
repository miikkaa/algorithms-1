import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;

  private final double mean;
  private final double stddev;
  private final double confidenceLo;
  private final double confidenceHi;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int gridSize, int noOfTrials) {
    if (gridSize <= 0 || noOfTrials <= 0) {
      throw new IllegalArgumentException();
    }

    final double[] thresholds = computeThresholds(gridSize, noOfTrials);

    this.mean = StdStats.mean(thresholds);
    this.stddev = StdStats.stddev(thresholds);

    final double suffix = CONFIDENCE_95 * this.stddev / Math.sqrt(noOfTrials);
    this.confidenceLo = this.mean - suffix;
    this.confidenceHi = this.mean + suffix;
  }

  private double[] computeThresholds(int gridSize, int noOfTrials) {
    final double[] thresholds = new double[noOfTrials];

    for (int i = 0; i < noOfTrials; i++) {
      // "Initialize all sites to be blocked"
      final Percolation percolation = new Percolation(gridSize);

      while (!percolation.percolates()) {
        // "Choose a site uniformly at random among all blocked sites"
        // "Open the site"
        percolation.open(StdRandom.uniformInt(gridSize) + 1, StdRandom.uniformInt(gridSize) + 1);
      }

      // Save results of the trial:
      // "The fraction of sites that are opened when the system percolates provides an estimate of
      // the percolation threshold"
      thresholds[i] = (double) percolation.numberOfOpenSites() / (gridSize * gridSize);
    }

    return thresholds;
  }

  // sample mean of percolation threshold
  public double mean() {
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return this.confidenceLo;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return this.confidenceHi;
  }

  // test client (see below)
  public static void main(String[] args) {
    if (args.length == 0) {
      args = new String[] {"10", "200"};
    }

    final int gridSize;
    final int noOfTrials;
    try {
      gridSize = Integer.parseInt(args[0]);
      noOfTrials = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(e);
    }

    final PercolationStats percolationStats = new PercolationStats(gridSize, noOfTrials);

    StdOut.println("mean = " + percolationStats.mean());
    StdOut.println("stddev = " + percolationStats.stddev());
    StdOut.println(
        "95% confidence interval = ["
            + percolationStats.confidenceLo()
            + ", "
            + percolationStats.confidenceHi()
            + "]");
  }
}
