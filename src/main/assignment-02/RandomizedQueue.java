import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] array;
  private int size = 0;

  // construct an empty randomized queue
  public RandomizedQueue() {
    array = (Item[]) new Object[1];
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();

    if (array.length == size) {
      resize(array.length * 2);
    }

    array[size++] = item;
  }

  private void resize(int newSize) {
    Item[] newArray = (Item[]) new Object[newSize];
    System.arraycopy(array, 0, newArray, 0, size);
    array = newArray;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int index = StdRandom.uniformInt(size);
    Item item = array[index];

    // since the order doesn't matter let's just move the last one in place of the removed one
    array[index] = array[size - 1];

    size--;

    if (size > 0 && size == array.length / 4) {
      resize(array.length / 2);
    }

    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    return array[StdRandom.uniformInt(size)];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomArrayIterator();
  }

  private class RandomArrayIterator implements Iterator<Item> {
    private int currentIndex = 0;
    private final Item[] arrayRandomized = (Item[]) new Object[size];

    public RandomArrayIterator() {
      System.arraycopy(array, 0, arrayRandomized, 0, arrayRandomized.length);
      StdRandom.shuffle(arrayRandomized);
    }

    @Override
    public boolean hasNext() {
      return currentIndex < arrayRandomized.length;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return arrayRandomized[currentIndex++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<String> deque = new RandomizedQueue<>();

    System.out.println(deque.size());
    System.out.println(deque.isEmpty());
    for (String item : deque) {
      System.out.println(item);
    }

    deque.enqueue("AA");
    deque.enqueue("BB");
    deque.enqueue("CC");
    deque.enqueue("DD");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("EE");
    deque.enqueue("FF");

    System.out.println("=====");
    System.out.println(deque.size());
    System.out.println(deque.isEmpty());

    System.out.println("=====");
    for (String item : deque) {
      System.out.println(item);
    }

    System.out.println("=====");
    for (String ignored : deque) {
      System.out.println(deque.sample());
    }

    System.out.println("=====");
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
    System.out.println(deque.dequeue());
  }
}
