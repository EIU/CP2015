import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class BinarySearchTree {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static final int MAX = 100000 + 2;
	static final int L = 0;
	static final int R = 1;
	static final int V = 2;
	static final int NNODE = 3;
	static int[][] nodes = new int[MAX][4];
	static int max = 0;

	static void solve() {
		max = 0;
		int n = ni();
		for (int i = 1; i <= n; i++) {
			int[] node = nodes[i];
			node[L] = ni();
			node[R] = ni();
			node[V] = ni();
		}

		traverse(1, L, -1);
		System.out.println(max);
	}

	static int traverse(int i, int LorR, int parentValue) {
		int[] node = nodes[i];
		int count = 1;
		if (node[L] > 0) {
			int countL = traverse(node[L], R, node[V]);
			if (countL == 0) {
				node[L] = 0;
			}
			count += countL;
		}
		if (node[R] > 0) {
			int countR = traverse(node[R], L, node[V]);
			if (countR == 0) {
				node[R] = 0;
			}
			count += countR;
		}
		max = Math.max(count, max);
		node[NNODE] = count;

		int invalid = i;
		int next = LorR;
		while (invalid > 0 &&
				((LorR == L && parentValue < nodes[invalid][V])
				|| (LorR == R && nodes[invalid][V] < parentValue))) {
			invalid = nodes[invalid][next];
		}
		int reduceNode = nodes[invalid][NNODE];
		if (i == invalid) {
			nodes[i][NNODE] -= reduceNode;
		}
		int j = i;
		while (j != invalid) {
			nodes[j][NNODE] -= reduceNode;
			if (nodes[j][next] == invalid) {
				nodes[j][next] = 0;
				break;
			}
			j = nodes[j][next];
		}
		return nodes[i][NNODE];
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
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

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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

	static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
