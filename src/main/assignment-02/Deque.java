import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private LinkedListItem first;
  private LinkedListItem last;
  private int size = 0;

  private class LinkedListItem {
    public Item item;
    public LinkedListItem next;
    public LinkedListItem prev;

    public LinkedListItem(Item item) {
      this.item = item;
    }
  }

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException();

    if (isEmpty()) {
      first = new LinkedListItem(item);
      last = first;
    } else {
      LinkedListItem oldFirst = first;
      first = new LinkedListItem(item);
      oldFirst.prev = first;
      first.next = oldFirst;
    }

    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();

    if (isEmpty()) {
      first = new LinkedListItem(item);
      last = first;
    } else {
      LinkedListItem oldLast = last;
      last = new LinkedListItem(item);
      oldLast.next = last;
      last.prev = oldLast;
    }

    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    size--;
    LinkedListItem oldFirst = first;
    first = oldFirst.next;
    // first removed
    if (first == null) {
      last = null;
    } else {
      first.prev = null;
    }
    return oldFirst.item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    size--;
    LinkedListItem oldLast = last;
    last = oldLast.prev;
    // last removed
    if (last == null) {
      first = null;
    } else {
      last.next = null;
    }
    return oldLast.item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {

    private LinkedListItem current = first;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<String> deque = new Deque<>();

    System.out.println(deque.size());
    System.out.println(deque.isEmpty());
    for (String item : deque) {
      System.out.println(item);
    }

    deque.addFirst("mid1");
    deque.addLast("mid2");
    deque.addFirst("first");
    deque.addLast("last");

    System.out.println(deque.size());
    System.out.println(deque.isEmpty());
    for (String item : deque) {
      System.out.println(item);
    }

    deque.removeLast();
    deque.removeFirst();
    deque.removeFirst();
    deque.removeFirst();
  }
}
