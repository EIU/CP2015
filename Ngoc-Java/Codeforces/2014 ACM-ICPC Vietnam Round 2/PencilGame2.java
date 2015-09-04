import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PencilGame2 {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();

		buildPrimes();
		// test();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @Des Assume rectangle is (r, c, h, w)
	 * @=> L = sum(rec(i,j)) = h*w*(r*n + c) + h*w*(w-1)/2 + h*w*(h-1)*n/2
	 * @=> 2L/w/h - (w-1) - (h-1)*n = 2r*n + 2c
	 */
	static void solve() {
		long m = ni();
		long n = ni();
		long l = nl();
		if (l < m * n) {
			System.out.println(1);
			return;
		}
		long min = Long.MAX_VALUE;
		l <<= 1;
		parseDivisors(l, Math.max(n, m) + 1);
		long[] sortedDivs = Arrays.copyOfRange(divisors, 0, nDiv);
		Arrays.sort(sortedDivs);
		for (int i = 0; i < nDiv; i++) {
			long w = sortedDivs[i];
			for (int j = 0; j < nDiv; j++) {
				long h = sortedDivs[j];
				long area = h * w;
				if (area >= min) break;
				if (l % area == 0) {
					long f = l / area - (w - 1) - (h - 1) * n;
					if ((f & 1) == 0 && f >= 0) {
						f >>= 1;
						long r = f / n;
						long c = f % n;
						if (r + h <= m && c + w <= n) {
							min = area;
						}
					}
				}
			}
		}
		System.out.println(min == Long.MAX_VALUE ? -1 : min);
	}

	static final int MAX = 8200;
	static long[] divisors = new long[MAX + 1];
	static int nDiv = 0;

	static final int MAXPRIME = 1000000 + 7;
	static boolean[] primes = new boolean[MAXPRIME + 1];

	static void buildPrimes() {
		Arrays.fill(primes, true);
		int sqmax = (int) (Math.sqrt(MAXPRIME)) + 1;
		for (int i = 2; i < sqmax; i++) {
			if (primes[i]) {
				for (int j = i * i; j < MAXPRIME; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	static void parseDivisors(long x, long limit) {
		divisors[0] = 1;
		nDiv = 1;
		for (int i = 2; i < limit; i++) {
			if (primes[i]) {
				int start = nDiv;
				int powi = 1;
				while (x % i == 0) {
					powi *= i;
					for (int j = 0; j < start; j++) {
						long div = divisors[j] * powi;
						if (div <= limit) {
							divisors[nDiv++] = div;
						}
					}
					x /= i;
				}
			}
		}
		if (x > 1) {
			int start = nDiv;
			for (int j = 0; j < start; j++) {
				long div = divisors[j] * x;
				if (div <= limit) {
					divisors[nDiv++] = div;
				}
			}
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
