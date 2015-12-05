import java.util.Iterator;
import java.util.NoSuchElementException;

/*-----------------------------------------
 * Author: Pao Im
 * Written: 02/21/2014
 * Last updated: 02/21/2014
 * 
 * Compilation: javac RandomizedQueue.java
 * Execution: java RandomizedQueue
 *-----------------------------------------*/
public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private int count;
	private Item[] items; 
	
	/*---------------------------------------
	 * construct an empty randomized queue
	 *---------------------------------------*/
	public RandomizedQueue() {
		size = 0;
		count = 0;
		items = (Item[]) new Object[1];
	}

	/*-----------------------
	 * is the queue empty?
	 *-----------------------*/
	public boolean isEmpty() {
		return size == 0;
	}

	/*-----------------------------------------
	 * return the number of items on the queue
	 *-----------------------------------------*/
	public int size() {
		return size;
	}

	/*-----------------------------
	 * add the item
	 *-----------------------------*/
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();

		if (count == items.length)
			resize(count * 2);

		size++;
		items[count++] = item;
	}

	/*----------------------------------
	 * delete and return a random item
	 *----------------------------------*/
	public Item dequeue() {
		checkEmpty();
		
		int index;
		do {
			index = StdRandom.uniform(count);
		} while (items[index] == null);
		
		Item item = items[index];
		
		size--;
		items[index] = null;
		
		if (size > 0 && size == items.length / 4)
			resize(items.length / 2);

		return item;
	}

	/*-------------------------------------------
	 * return (but do not delete) a random item
	 *-------------------------------------------*/
	public Item sample() {
		checkEmpty();
		
		int index;
		do {
			index = StdRandom.uniform(count + 1);
		} while (items[index] == null);
		
		return items[index];
	}

	/*------------------------------------------------------------
	 * return an independent iterator over items in random order
	 *------------------------------------------------------------*/
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private void checkEmpty(){
		if (isEmpty())
			throw new NoSuchElementException();
	}
	
	private void resize(int newSize) {
		count = 0;
		Item[] newItems = (Item[]) new Object[newSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] == null)
				continue;

			newItems[count++] = items[i];
		}

		items = newItems;
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int n = 0;
		private Item[] data = (Item[]) new Object[size];

		public RandomizedQueueIterator() {
			int j = 0;
			for (int i = 0; i < items.length; i++) {
				if (items[i] == null)
					continue;
				
				data[j] = items[i];
				
				int r = StdRandom.uniform(j + 1);
				
				Item tmp = data[j];
				data[j] = data[r];
				data[r] = tmp;
				
				j++;
			}
		}

		public boolean hasNext() {
			return n < data.length;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {

			if (!hasNext())
				throw new NoSuchElementException();

			return data[n++];
		}
	}

	/*------------------------------------
	 * unit testing
	 *------------------------------------*/
	public static void main(String[] args) {
		RandomizedQueue<String> rqueue = new RandomizedQueue<String>();
		String item1 = StdIn.readString();
		String item2 = StdIn.readString();
		rqueue.enqueue(item1);
		rqueue.enqueue(item2);
		
		Iterator<String> strIterator = rqueue.iterator();
		while(strIterator.hasNext()){
			String s = strIterator.next();
			StdOut.println(s);
		}
	}

}