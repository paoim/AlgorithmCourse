import java.util.Iterator;

public class FixedCapacityStackOfInteger implements Iterable<Integer> {
	private int a[];
	private int N;

	public FixedCapacityStackOfInteger(int capacity) {
		a = new int[capacity];
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	public boolean isFull() {
		return (N == a.length);
	}

	public void push(int item) {
		a[N++] = item;
	}

	public int pop() {
		return a[--N];
	}

	public int peek() {
		return a[N - 1];
	}

	public Iterator<Integer> iterator() {
		return new ReverseArrayIterator();
	}

	public class ReverseArrayIterator implements Iterator<Integer> {
		private int i = N - 1;

		public boolean hasNext() {
			return i >= 0;
		}

		public Integer next() {
			return a[i--];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String args[]){
		int nums [] = {0,1,2,3,4,5,6,7,8,9};
		FixedCapacityStackOfInteger fcs = new FixedCapacityStackOfInteger(10);
		for(int item : nums){
			fcs.push(item);
			//StdOut.print(item);
		}
		
		for(int item : fcs)
			StdOut.print(fcs.pop());
	}

}
