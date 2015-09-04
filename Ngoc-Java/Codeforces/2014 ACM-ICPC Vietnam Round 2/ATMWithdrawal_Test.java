import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ATMWithdrawal_Test {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		// long s = System.currentTimeMillis();
		for (int t = 0; t < 1000000; t++) {
			long x = (long) (Math.random() * 1000000000l) * 1000;
			int c = (int) (Math.random() * 15);
			solve(x, c);
			solve2(x, c);
			if (n2 != n || s2 != s) {
				System.out.println(x + ", " + c + " => " + s + ", " + n + " vs " + s2 + ", " + n2);
			}
		}
		out.flush();
		// tr(System.currentTimeMillis() - s + "ms");
	}

	static long s = 0;
	static long n = 0;

	static void solve(long w, int c) {
		if (w % 1000 != 0) {
			s = n = 0;
			System.out.println(0);
			return;
		}

		w /= 1000;

		long[][] map = new long[][] { { 0, 0, 1 },
				{ 1, 1, 1 }, { 2, 1, 1 }, { 3, 1, 1 },
				{ 4, 2, 2 }, { 5, 1, 1 }, { 6, 2, 2 },
				{ 7, 2, 1 }, { 8, 2, 1 }, { 9, 3, 3 } };

		long m = (long) Math.pow(10, c + 1);

		s = w / m * 2;
		w %= m;
		n = 1;
		m /= 10;
		boolean firstDigit = s > 0;
		while (w > 0) {
			int x = (int) (w / m);
			s += map[x][1];
			n *= map[x][2];
			w = w % m;
			m /= 10;
			if (firstDigit) {
				// In case: we do not have note 10x
				if (x == 4) {
					// 14 => 3 3 3 5 (new), 5 5 2 2, 5 5 3 1 (has been counted)
					n = n / 2 * 3;
				}
				if (x == 1) {
					// 11 => 3 3 5 (new), 5 5 1 (has been counted)
					n *= 2;
				}
				firstDigit = false;
			}
		}
	}

	static long s2 = 0;
	static long n2 = 0;

	static void solve2(long w, int c) {
		if (w % 1000 != 0) {
			s2 = n2 = 0;
			System.out.println(0);
			return;
		}
		w /= 1000;
		long[][] map = new long[][] { { 0, 0, 1 },
				{ 1, 1, 1 }, { 2, 1, 1 }, { 3, 1, 1 },
				{ 4, 2, 2 }, { 5, 1, 1 }, { 6, 2, 2 },
				{ 7, 2, 1 }, { 8, 2, 1 }, { 9, 3, 3 } };
		long m = (long) Math.pow(10, c);
		long max = 5 * m;
		s2 = (w - max) / max;
		n2 = 1;
		w -= s2 * max;
		while (w > 0) {
			// for (int j = 0; j <= c; j++) {
			int x = (int) (w / m);
			s2 += map[x][1];
			n2 *= map[x][2];
			w = w % m;
			m /= 10;
		}
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
