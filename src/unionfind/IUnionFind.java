package unionfind;

public interface IUnionFind {
	
	boolean connected(int p, int q);

	void union(int p, int q);
	
	int[] getId();
}
