import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PA_GoodLoot {
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

	/**
	 * @solution: Knapsack with three states: 0-activated, 1-activated, 2-activated
	 */
	static void solve() {
		int n = ni();
		int m = ni();

		int base = -200;
		int noStatus = 901;

		int minValue = Integer.MIN_VALUE;
		int[] status0 = new int[noStatus];
		int[] status1 = new int[noStatus];
		int[] status2 = new int[noStatus];
		Arrays.fill(status0, minValue);
		Arrays.fill(status1, minValue);
		Arrays.fill(status2, minValue);
		status0[-base] = 0;

		for (int i = 0; i < n; i++) {
			int p = ni();
			int w = ni();
			int d = ni();
			for (int j = noStatus - 1; j >= 0; j--) {
				if (j - w >= 0 && status2[j - w] >= 0) {
					status2[j] = Math.max(status2[j], status2[j - w] + p);
				}
				if (j + d - w >= 0 && j + d - w < noStatus && status1[j + d - w] >= 0) {
					status2[j] = Math.max(status2[j], status1[j + d - w] + p);
				}
			}

			for (int j = noStatus - 1; j >= 0; j--) {
				if (j - w >= 0 && status1[j - w] >= 0) {
					status1[j] = Math.max(status1[j], status1[j - w] + p);
				}
				if (j + d - w >= 0 && j + d - w < noStatus && status0[j + d - w] >= 0) {
					status1[j] = Math.max(status1[j], status0[j + d - w] + p);
				}
			}

			for (int j = noStatus - 1; j >= 0; j--) {
				if (j - w >= 0 && status0[j - w] >= 0) {
					status0[j] = Math.max(status0[j], status0[j - w] + p);
				}
			}
		}

		for (int j = 1; j < noStatus; j++) {
			status0[j] = Math.max(status0[j], status0[j - 1]);
			status1[j] = Math.max(status1[j], status1[j - 1]);
			status2[j] = Math.max(status2[j], status2[j - 1]);
		}

		System.out.println(Math.max(Math.max(status0[m - base], status1[m - base]), status2[m - base]));
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1) throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0) return -1;
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

	static long nl() {
		long num = 0;
		int b;
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
