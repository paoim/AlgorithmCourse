/*-----------------------------------------
 * Author: Pao Im
 * Written: 02/15/2014
 * Last updated: 02/16/2014
 * 
 * Compilation: javac Percolation.java
 * Execution: java Percolation
 *-----------------------------------------*/
public class Percolation {
	private enum PITEM {
		BLOCKED, OPEN, FULL
	}

	private PITEM pItems[][];
	private WeightedQuickUnionUF wqUnionUF;

	/*--------------------------------------------
	 * create N-by-N grid, with all sites blocked
	 *--------------------------------------------*/
	public Percolation(int N) {
		pItems = new PITEM[N][N];
		wqUnionUF = new WeightedQuickUnionUF(N * N + 2);

		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				pItems[row][col] = PITEM.BLOCKED;
	}

	/*--------------------------------------------------
	 * open site (row i, column j) if it is not already
	 *--------------------------------------------------*/
	public void open(int i, int j) {
		pItems[i - 1][j - 1] = PITEM.OPEN;
		int quare = pItems.length * pItems.length;

		if (i == 1)
			wqUnionUF.union(j - 1, quare);
		if (i == pItems.length)
			wqUnionUF.union(pItems.length * (pItems.length - 1) + (j - 1), quare + 1);

		// top
		merge(i, j, i - 1, j);

		// right
		merge(i, j, i, j + 1);

		// bottom
		merge(i, j, i + 1, j);

		// left
		merge(i, j, i, j - 1);
	}

	/*---------------------------------
	 * is site (row i, column j) open?
	 *---------------------------------*/
	public boolean isOpen(int i, int j) {
		validateInput(i, j);
		return pItems[i - 1][j - 1] == PITEM.OPEN;
	}

	/*--------------------------------
	 * is site (row i, column j) full?
	 *--------------------------------*/
	public boolean isFull(int i, int j) {
		validateInput(i, j);

		boolean isOpen = isOpen(i, j);
		boolean isConnected = wqUnionUF.connected(
			pItems.length * pItems.length, 
			(i - 1) * pItems.length + (j - 1)
		);

		return isConnected && isOpen;
	}

	/*------------------------------
	 * does the system percolate?
	 *------------------------------*/
	public boolean percolates() {
		boolean isConnected = wqUnionUF.connected(
			pItems.length * pItems.length,
			(pItems.length * pItems.length) + 1
		);

		return isConnected;
	}

	private void merge(int i, int j, int i1, int j1) {
		if (i1 < 1 || i1 > pItems.length || j1 < 1 || j1 > pItems.length) {
			return;
		}

		if (isBlocked(i1, j1)) {
			return;
		}

		wqUnionUF.union((i - 1) * pItems.length + (j - 1), (i1 - 1) * pItems.length + (j1 - 1));
	}

	private boolean isBlocked(int i, int j) {
		validateInput(i, j);

		return pItems[i - 1][j - 1] == PITEM.BLOCKED;
	}

	private void validateInput(int i, int j) {
		if (i < 1 || i > pItems.length || j < 1 || j > pItems.length)
			throw new IndexOutOfBoundsException();
	}
}