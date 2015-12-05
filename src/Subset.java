/*-----------------------------------------
 * Author: Pao Im
 * Written: 02/21/2014
 * Last updated: 02/21/2014
 * 
 * Compilation: javac Subset.java
 * Execution: java Subset
 *-----------------------------------------*/
public class Subset {

	public static void main(String[] args) {
		int count = 0;
		String[] items = new String[2];
		
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			
			items[count++] = s;
			
			if (count == items.length) {
				String[] newStrings = new String[count * 2];
				for (int i = 0; i < count; i++) {
					newStrings[i] = items[i];
				}
				
				items = newStrings;
			}
			
			int r = StdRandom.uniform(count);
			String swap = items[count - 1];
			items[count - 1] = items[r];
			items[r] = swap;
		}
		
		int k = Integer.parseInt(args[0]);
		for (int i = 0; i < k && i < count; i++) {
			StdOut.println(items[i]);
		}
	}

}