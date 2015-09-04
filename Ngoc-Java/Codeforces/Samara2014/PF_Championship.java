import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PF_Championship {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "3 4 7 8 10 5 5";

	public static void main(String[] args) throws Exception {
		oj = false;
		// is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		is = System.in;
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		// tr(System.currentTimeMillis() - s + "ms");
	}

	static int n;
	static int m;
	static boolean[][] masks;

	/**
	 * @solution:
	 */
	static void solve() {
		n = ni();
		m = ni();
		int[] products = new int[n];
		for (int i = 0; i < n; i++) {
			products[i] = ni();
		}
		String[] names = new String[n];
		for (int i = 0; i < n; i++) {
			names[i] = ns();
		}
		masks = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String maskString = ns();
			for (int j = 0; j < m; j++) {
				masks[i][j] = maskString.charAt(j) == '1';
			}
		}
		int grandMax = 0;
		int im = 0, jm = 1, km = 2;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					int max = find(masks[i], masks[j], masks[k], products[i], products[j], products[k]);
					if (max > grandMax) {
						grandMax = max;
						im = i;
						jm = j;
						km = k;
					}
				}
			}
		}
		System.out.println(grandMax + "\n" + names[im] + " " + names[jm] + " " + names[km]);
	}

	// @Conditions: x1+x2<=a12, y1+y2<=a23, z+z3<=a31, z2+x1<=pi, x2+y1<=pj, y2+z1<=pk
	// @Target: Max(x1+x2+y1+y2+z1+z2)
	// @Solution: a12, a23, a31 form a ring
	// ..................a12
	// ................/.....\
	// .............../.......\
	// ............../.........\
	// ............./...........\
	// ............a31----------a23
	static int find(boolean[] row1, boolean[] row2, boolean[] row3, int p1, int p2, int p3) {
		int a1 = 0, a2 = 0, a3 = 0, a12 = 0, a23 = 0, a31 = 0, a123 = 0;
		for (int c = 0; c < m; c++) {
			boolean b1 = row1[c], b2 = row2[c], b3 = row3[c];
			if (b1 && !b2 && !b3) a1++;
			if (!b1 && b2 && !b3) a2++;
			if (!b1 && !b2 && b3) a3++;
			if (b1 && b2 && !b3) a12++;
			if (!b1 && b2 && b3) a23++;
			if (b1 && !b2 && b3) a31++;
			if (b1 && b2 && b3) a123++;
		}
		int result = Math.min(p1, a1) + Math.min(p2, a2) + Math.min(p3, a3);
		p1 -= Math.min(p1, a1);
		p2 -= Math.min(p2, a2);
		p3 -= Math.min(p3, a3);

		int temp;
		if (a31 > p3) {
			temp = Math.min(p1, a31 - p3);
			result += temp;
			p1 -= temp;
			a31 -= temp;
		}
		temp = Math.min(p1, a12);
		result += temp;
		p1 -= temp;
		a12 -= temp;

		temp = Math.min(a12, p2);
		result += temp;
		p2 -= temp;
		a12 -= temp;

		temp = Math.min(p2, a23);
		result += temp;
		p2 -= temp;
		a23 -= temp;

		temp = Math.min(a23, p3);
		result += temp;
		p3 -= temp;
		a23 -= temp;

		temp = Math.min(p3, a31);
		result += temp;
		p3 -= temp;
		a31 -= temp;

		temp = Math.min(a31, p1);
		result += temp;
		p1 -= temp;
		a31 -= temp;

		result += Math.min(p1 + p2 + p3, a123);
		return result;
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
