import unionfind.IUnionFind;
import unionfind.QuickFindUF;
import unionfind.QuickUnionImproveUF;

/**
 * Week 1 - Union Find
 * 
 * @author pao
 * 
 */
public class UnionFindDemo {

	public static void main(String[] args) {
		IUnionFind quickFindUF = new QuickFindUF(10);
		// IUnionFind quickUnionUF = new QuickUnionUF(10);
		IUnionFind weightedUnionUF = new QuickUnionImproveUF(10);

		int id[] = quickFindUF.getId();
		// int uId[] = quickUnionUF.getId();
		int wUId[] = weightedUnionUF.getId();

		StdOut.println("Quick Find");
		// for (int i = 0; i < id.length; i++)
		// StdOut.println(id[i]);

		// 3-8 7-3 8-5 5-9 8-1 0-9
		StdOut.println("union(3, 8)");
		quickFindUF.union(3, 8);

		StdOut.println("union(7, 3)");
		quickFindUF.union(7, 3);

		StdOut.println("union(8, 5)");
		quickFindUF.union(8, 5);

		StdOut.println("union(5, 9)");
		quickFindUF.union(5, 9);

		StdOut.println("union(8, 1)");
		quickFindUF.union(8, 1);

		StdOut.println("union(0, 9)");
		quickFindUF.union(0, 9);

		for (int i = 0; i < id.length; i++)
			StdOut.println(id[i]);

		// StdOut.println("connected(2, 4) is " + quickFindUF.connected(2, 4));

		// StdOut.println("Quick Union");
		// for (int i = 0; i < uId.length; i++)
		// StdOut.println(uId[i]);
		//
		// StdOut.println("union(3, 4)");
		// quickUnionUF.union(3, 4);
		//
		// for (int i = 0; i < uId.length; i++)
		// StdOut.println(uId[i]);
		//
		// StdOut.println("connected(3, 4) is " + quickUnionUF.connected(3, 4));

		StdOut.println("Weighted Quick Union");
		// for (int i = 0; i < wUId.length; i++)
		// StdOut.println(wUId[i]);

		// 4-2 0-3 8-9 2-5 3-8 7-4 7-0 3-1 4-6
		StdOut.println("union(4, 2)");
		weightedUnionUF.union(4, 2);

		StdOut.println("union(0, 3)");
		weightedUnionUF.union(0, 3);

		StdOut.println("union(8, 9)");
		weightedUnionUF.union(8, 9);

		StdOut.println("union(2, 5)");
		weightedUnionUF.union(2, 5);

		StdOut.println("union(3, 8)");
		weightedUnionUF.union(3, 8);

		StdOut.println("union(7, 4)");
		weightedUnionUF.union(7, 4);

		StdOut.println("union(7, 0)");
		weightedUnionUF.union(7, 0);

		StdOut.println("union(3, 1)");
		weightedUnionUF.union(3, 1);

		StdOut.println("union(4, 6)");
		weightedUnionUF.union(4, 6);

		for (int i = 0; i < wUId.length; i++)
			StdOut.println(wUId[i]);

		// StdOut.println("connected(3, 4) is " + weightedUnionUF.connected(3,
		// 4));
	}

}
