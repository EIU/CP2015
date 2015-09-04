import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class PTIT015F {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		int n = ni();
		int m = ni();
		while (n != 0 || m != 0) {
			solve(n, m);
			n = ni();
			m = ni();
		}
		out.flush();
	}

	/**
	 * @Solution: Simple binary search for L in range [0,1000001]
	 * @Enhance: n in [1, 10^9], m in [1, 10^5], Pi in [0, 10^15]
	 */
	static void solve(int n, int m) {
		int[] p = new int[m];
		for (int i = 0; i < m; i++) {
			p[i] = ni();
		}
		Arrays.sort(p);
		int L = p[0] / n;
		int Y = L > 0 ? p[0] : 0;
		int min = L;
		int max = 1000001;
		while (min < max - 1) {
			int mid = (min + max + 1) / 2;
			int cost = check(p, mid, n);
			if (cost < 0) {
				max = mid;
			} else {
				L = mid;
				Y = cost;
				min = mid;
			}
		}
		System.out.println(L + " " + Y);
	}

	static int check(int[] p, int seed, int count) {
		int cost = 0;
		int len = p.length;
		int i = 0;
		while (i < len && count > 0) {
			int c = p[i] / seed;
			if (c > 0) {
				count -= c;
				cost += p[i];
			}
			i++;
		}
		return count > 0 ? -1 : cost;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096];
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
}
