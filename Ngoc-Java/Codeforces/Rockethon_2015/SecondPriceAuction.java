import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class SecondPriceAuction {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "3 4 7 8 10 5 5";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static int MAX = 10000;
	static int n;
	static double[] lefts;
	static double[] rights;

	/**
	 * @Solution: probability of x to be second price: one company choose value greater than x while (n-1) company choose value less than x in which at least one choose x
	 * @Solution: Sum of x multiply: f(si > x) *(f(S/{si} <= x) - f(S/{si} <= x-1)) + f(S<=x and at least two value = x)
	 */
	static void solve() {
		n = ni();
		lefts = new double[n];
		rights = new double[n];

		for (int i = 0; i < n; i++) {
			lefts[i] = ni();
			rights[i] = ni();
		}

		double result = 0;
		for (int x = 1; x <= MAX; x++) {
			for (int i = 0; i < n; i++) {
				result += x * (1 - probToX(x, i))
						* (probXExclude(x, i) - probXExclude(x - 1, i));
			}
			result += x * prob2isX(x);
		}
		System.out.println(result);
	}

	/*
	 * f(At least two value is x while none of them greater than x)
	 */
	static double prob2isX(int x) {
		double result = probXExclude(x, -1) - probXExclude(x - 1, -1);
		for (int i = 0; i < n; i++) {
			result -= ((x < lefts[i] || x > rights[i]) ? 0 : 1) / (rights[i] - lefts[i] + 1) * probXExclude(x - 1, i);
		}
		return result;
	}

	/**
	 * f(S/si <= x)
	 */
	static double probXExclude(int x, int u) {
		double result = 1;
		for (int k = 0; k < n; k++) {
			if (k != u) {
				result *= probToX(x, k);
			}
		}
		return result;
	}

	/**
	 * f(si <= x)
	 */
	static double probToX(int x, int i) {
		return (Math.min(rights[i], Math.max(lefts[i] - 1, x)) - lefts[i] + 1) / (rights[i] - lefts[i] + 1);
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
