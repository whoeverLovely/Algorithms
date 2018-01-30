import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int n = 0;

	// construct an empty randomized queue
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return n;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			copy[i] = items[i];
		}
		items = copy;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (n == items.length)
			resize(2 * items.length);
		items[n] = item;
		n++;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		int i = StdRandom.uniform(n); // return 0 to n-1
		Item item = items[i];
		n--;
		
		if (n > 0 && n == items.length / 4)
			resize(items.length / 2); 
		
		
			for (int j = i; j < n; j++) {
				items[j] = items[j + 1];
			
		}
		
		
		return item;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();

		int i = StdRandom.uniform(n); // return 0 to n-1
		Item item = items[i];
		return item;
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		int i = 0;
		Item[] newItems = (Item[]) new Object[n];
		
		public RandomizedQueueIterator() {
			for(int i = 0; i < n; i++) {
				newItems[i] = items[i];
			}
			StdRandom.shuffle(newItems);
		}

		@Override
		public boolean hasNext() {
			return i < newItems.length;
		}

		@Override
		public Item next() {
			if (i > n-1)
				throw new NoSuchElementException();
			
			
			Item item = newItems[i];
			i++;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// unit testing (optional)
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(3);
		rq.enqueue(5);
		System.out.println(rq.dequeue());
		rq.enqueue(7);
		rq.enqueue(4);
		rq.enqueue(2);
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());
		System.out.println(rq.dequeue());



		Iterator<Integer> i = rq.iterator();
		while (i.hasNext())
			System.out.println(i.next());

	}

}
