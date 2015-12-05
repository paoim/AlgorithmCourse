import java.util.Iterator;
import java.util.NoSuchElementException;

/*-----------------------------------------
 * Author: Pao Im
 * Written: 02/21/2014
 * Last updated: 02/21/2014
 * 
 * Compilation: javac Deque.java
 * Execution: java Deque
 *-----------------------------------------*/
public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node<Item> last;
	private Node<Item> first;
	
	/*--------------------------
	 * construct an empty deque
	 *---------------------------*/
	public Deque() {
		size = 0;
		last = null;
		first = null;
	}

	/*----------------------
	 * is the deque empty?
	 *-----------------------*/
	public boolean isEmpty() {
		return size == 0;
	}

	/*-----------------------------------------
	 * return the number of items on the deque
	 *-----------------------------------------*/
	public int size() {
		return size;
	}

	/*--------------------------------
	 * insert the item at the front
	 *--------------------------------*/
	public void addFirst(Item item) {
		validateInputItem(item);
		
		Node<Item> node = new Node<Item>(item, null, first);
		
		if (!isEmpty())
			first.previous = node;
		
		first = node;
		
		if (last == null)
			last = first;
		
		size++;
	}

	/*-----------------------------
	 * insert the item at the end
	 *-----------------------------*/
	public void addLast(Item item) {
		validateInputItem(item);

		Node<Item> node = new Node<Item>(item, last, null);
		if (last != null)
			last.next = node;

		last = node;

		if (isEmpty())
			first = last;

		size++;
	}

	/*------------------------------------------
	 * delete and return the item at the front
	 *------------------------------------------*/
	public Item removeFirst() {
		checkEmpty();
		
		Item item = first.item;
		if (first.next != null)
			first.next.previous = null;
		if (last == first)
			last = null;
		
		first = first.next;
		size--;

		return item;
	}

	/*---------------------------------------
	 * delete and return the item at the end
	 *---------------------------------------*/
	public Item removeLast() {
		checkEmpty();

		Item item = last.item;
		if (last.previous != null)
			last.previous.next = null;
		if (last == first)
			first = last = null;
		if (last != null)
			last = last.previous;

		size--;

		return item;
	}

	/*----------------------------------------------------------
	 * return an iterator over items in order from front to end
	 *----------------------------------------------------------*/
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class Node<Item> {
		public Item item;
		public Node<Item> next;
		public Node<Item> previous;

		public Node(Item item, Node<Item> previous, Node<Item> next) {
			this.next = next;
			this.item = item;
			this.previous = previous;
		}
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {

			if (!hasNext())
				throw new NoSuchElementException();

			Item item = current.item;

			current = current.next;

			return item;
		}
	}
	
	private void validateInputItem(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
	}
	
	private void checkEmpty() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
	}

	/*--------------------------------------
	 * unit testing
	 *--------------------------------------*/
	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		String first = StdIn.readString();
		String second = StdIn.readString();
		deque.addFirst(first);
		deque.addLast(second);
		
		Iterator<String> item = deque.iterator();
		while(item.hasNext()){
			String s = item.next();
			StdOut.println(s);
		}
		
		StdOut.println("Total sizes is " + deque.size());
	}
}