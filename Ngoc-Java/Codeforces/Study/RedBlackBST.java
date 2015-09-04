/*************************************************************************
 *  Compilation:  javac RedBlackBST.java
 *  Execution:    java RedBlackBST < input.txt
 *  Dependencies: StdIn.java StdOut.java  
 *  Data files:   http://algs4.cs.princeton.edu/33balanced/tinyST.txt  
 *    
 *  A symbol table implemented using a left-leaning red-black BST.
 *  This is the 2-3 version.
 *
 *  Note: commented out assertions because DrJava now enables assertions
 *        by default.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java RedBlackBST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeSet;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

	/*****************************************************************************
	 * Test client
	 *****************************************************************************/
	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		// RedBlackBST<Integer, Integer> tree = new RedBlackBST<Integer, Integer>();

		int n = 1 * 100 * 1000;
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = (int) (Math.random() * 1000 * 1000 * 1000);
		}
		Arrays.sort(array);

		// StringBuilder result = new StringBuilder();
		// for (int i = 0; i < n; i++) {
		// Integer v = (int) (Math.random() * 1000 * 1000 * 1000);
		// int size = tree.size(-1, v);
		// tree.put(v, 0);
		// result.append(s + ": " + size + ", ");
		// }

		// System.out.println(result.length());
		System.out.println(System.currentTimeMillis() - s + "ms");
	}
}