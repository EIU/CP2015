import java.io.*;
import java.util.*;

// New idea? Longest Increase/Non-decrease subsequence with BIT. Complexity O(nlgn)
class P165PROB {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int n = ni();
		int m = ni();

		Tree[] trees = new Tree[n];
		for (int i = 0; i < n; i++) {
			trees[i] = new Tree(ni(), nd());
		}

		Arrays.sort(trees);

		sizeBit = m;
		maxBit = new int[sizeBit + 1];

		for (int i = 0; i < n; i++) {
			int t = trees[i].type;
			setMax(t, getMax(t) + 1);
		}
		System.out.println(n - getMax(m));
	}

	static int sizeBit;
	static int[] maxBit;

	static void setMax(int i, int value) {
		for (; i <= sizeBit; i += (i & -i)) {
			maxBit[i] = Math.max(maxBit[i], value);
		}
	}

	static int getMax(int i) {
		int max = 0;
		for (; i > 0; i -= (i & -i)) {
			max = Math.max(max, maxBit[i]);
		}
		return max;
	}

	static class Tree implements Comparable<Tree> {
		public int type;
		public double x;

		public Tree(int type, double position) {
			this.type = type;
			this.x = position;
		}

		@Override
		public int compareTo(Tree arg0) {
			int cmp = Double.compare(this.x, arg0.x);
			if (cmp == 0) {
				return this.type - arg0.type;
			}
			return cmp;
		}
	}

	/* *
	 */

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

	static byte[] inbuf = new byte[4096];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b));
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
}
