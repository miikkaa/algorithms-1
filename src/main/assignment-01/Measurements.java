import edu.princeton.cs.algs4.Stopwatch;

public class Measurements {
  public static void main(String[] args) {
    // WeightedQuickUnionUF
    final Stopwatch stopwatch = new Stopwatch();
    PercolationStats.main(new String[]{"16000", "10"});
    System.out.println("elapsed time = " + stopwatch.elapsedTime());
  }
}
