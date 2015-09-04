import java.util.*;
import java.io.*;

class ProblemE {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		is = System.in;
		out = new PrintWriter(System.out);

		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}

		out.flush();

		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static final int MAX = 25;
	static final int MAXINT = Integer.MAX_VALUE;

	static void solve() {
		int[] types = new int[] { ni(), ni(), ni(), ni() };
		int n = ni();
		int[][] hrs = new int[][] { checkType(types[0]), checkType(types[1]),
				checkType(types[2]), checkType(types[3]) };

		long ans = MAXINT;
		for (int i0 = 0; i0 <= MAX; i0++) {
			for (int i1 = 0; i1 <= MAX; i1++) {
				for (int i2 = 0; i2 <= MAX; i2++) {
					int i3 = Math.max(0, n - i0 - i1 - i2);
					if (i3 <= MAX) {
						ans = Math.min(ans, (long) hrs[0][i0] + hrs[1][i1]
								+ hrs[2][i2] + hrs[3][i3]);
					}
				}
			}
		}
		System.out.println(ans < Integer.MAX_VALUE ? ans : "impossible");
	}

	static int[] checkType(int ni) {
		int[] points = new int[MAX + 1];
		Arrays.fill(points, MAXINT);
		points[0] = 0;
		for (int i = 0; i < ni; i++) {
			int point = ni(), hrs = ni();
			for (int j = MAX; j >= 0; j--) {
				int index = Math.min(MAX, j + point);
				if (points[j] < MAXINT) {
					points[index] = Math.min(points[index], points[j] + hrs);
				}
			}
		}
		for (int i = MAX - 1; i >= 0; i--) {
			points[i] = Math.min(points[i], points[i + 1]);
		}
		return points;
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
