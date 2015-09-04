import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @SPOJ allow invoke method recursively deep up to 6K (http://www.spoj.com/PTIT/problems/P152PROC/)
 * @Codeforce: up to 1M (http://codeforces.com/contest/430/problem/C)
 */
public class P152PROC_TroChoiTrenCay {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		is = System.in;
		out = new PrintWriter(System.out);

		solve();

		out.flush();

		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static ArrayList<ArrayList<Integer>> allEdges = new ArrayList<ArrayList<Integer>>();
	static boolean[] statuses;
	static boolean[] newStatuses;
	static boolean[] flips;

	static void solve() {
		int n = ni();
		statuses = new boolean[n];
		newStatuses = new boolean[n];
		flips = new boolean[n];

		for (int i = 0; i < n; i++) {
			allEdges.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < n - 1; i++) {
			int u = ni() - 1;
			int v = ni() - 1;
			allEdges.get(u).add(v);
			allEdges.get(v).add(u);
		}
		for (int i = 0; i < n; i++) {
			statuses[i] = (ni() == 1);
		}
		for (int i = 0; i < n; i++) {
			newStatuses[i] = (ni() == 1);
		}
		int count = dfs(0, -1, false, false);
		System.out.println(count);
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (flips[i]) {
				bf.append((i + 1) + " ");
			}
		}
		System.out.println(bf.toString());
	}

	static int dfs(int node, int parentNode, boolean flipParent, boolean flipGrand) {
		int count = 0;
		if ((statuses[node] ^ newStatuses[node]) != flipGrand) {
			flips[node] = true;
			flipGrand = !flipGrand;
			count++;
		}
		for (int i : allEdges.get(node)) {
			if (i != parentNode) {
				count += dfs(i, node, flipGrand, flipParent);
			}
		}
		return count;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
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
}
