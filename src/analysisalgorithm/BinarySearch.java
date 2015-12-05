package analysisalgorithm;

/**
 * Binary Search
 * @author pao
 *
 */
public class BinarySearch {

	private int a[];

	public BinarySearch(int N) {
		a = new int[N];
	}

	public int doBinarySearch(int key) {
		int lo = 0, hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}

		return -1;
	}
}
