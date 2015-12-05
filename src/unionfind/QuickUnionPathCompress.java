package unionfind;

/**
 * Quick Union Improve (Path Compress)
 * 
 * @author pao
 * 
 */
public class QuickUnionPathCompress implements IUnionFind {

	private int id[];

	public QuickUnionPathCompress(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}

		return i;
	}

	@Override
	public boolean connected(int p, int q) {

		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}

	@Override
	public int[] getId() {

		return id;
	}

}
