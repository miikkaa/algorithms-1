import edu.princeton.cs.algs4.StdIn;

public class Permutation {
  public static void main(String[] args) {
    final int k;
    try {
      k = Integer.parseInt(args[0], 10);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException(ex);
    }

    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      randomizedQueue.enqueue(StdIn.readString());
    }

    for (int i = 0; i < k; i++) {
      System.out.println(randomizedQueue.dequeue());
    }
  }
}
