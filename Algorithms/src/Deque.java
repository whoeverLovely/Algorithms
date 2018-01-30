import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first, last;
	private int count;

	private class Node {
		Item item;
		Node next, previous;
	}

	// construct an empty deque
	public Deque() {

	}

	// is the deque empty?
	public boolean isEmpty() {
		return (count == 0);
	}

	// return the number of items on the deque
	public int size() {
		return count;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		if (count == 0) {
			first = new Node();
			first.item = item;
			last = first;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			oldFirst.previous = first;
		}

		count++;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		if (count == 0) {
			last = new Node();
			last.item = item;
			first = last;
		} else {
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.previous = oldLast;
			oldLast.next = last;
		}

		count++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();

		Node oldFirst = first;
		first = first.next;
		count--;
		return oldFirst.item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();

		Item item = last.item;
		last = last.previous;
		count--;
		return item;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		private int countI = 0;

		public boolean hasNext() {
			return countI < count;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (current == null)
				throw new NoSuchElementException();

			Item item = current.item;
			current = current.next;
			countI++;
			return item;
		}
	}

	// unit testing (optional)1
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		deque.addFirst(4);
		deque.removeLast();
		 deque.removeFirst();
		 deque.removeLast();
		System.out.println(deque.removeLast());
	deque.removeLast();

		Iterator<Integer> t = deque.iterator();
		while (t.hasNext())
			System.out.println(t.next());

	}
}
