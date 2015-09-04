import java.math.BigInteger;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class P151PROJ {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		is = System.in;
		out = new PrintWriter(System.out);

		initFactoriors();

		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}

		// for (int i = 1; i < 20; i++) {
		// for (int j = 0; j <= i; j++) {
		// System.out.print(nCr(i, j) + "\t");
		// }
		// System.out.println();
		// }

		// Random rand = new Random();
		// for (int i = 0; i < 1000000; i++) {
		// int r = rand.nextInt(MAX);
		// nCr(r, r / 2);
		// }

		out.flush();

		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		int n = ni();
		int m = ni();
		int k = ni();
		int[][] ps = new int[k + 1][3];
		for (int i = 0; i < k; i++) {
			ps[i][0] = ni() - 1;
			ps[i][1] = ni() - 1;
		}
		ps[k][0] = n - 1;
		ps[k][1] = m - 1;

		Arrays.sort(ps, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				int cmp = arg0[0] - arg1[0];
				if (cmp != 0) {
					return cmp;
				}
				return arg0[1] - arg1[1];
			}
		});

		for (int i = 0; i <= k; i++) {
			int[] pi = ps[i];
			long excluded = 0;
			for (int j = i - 1; j >= 0; j--) {
				int[] pj = ps[j];
				if (pj[0] <= pi[0] && pj[1] <= pi[1]) {
					excluded = (excluded + ((long) pj[2] * nCr(pi[0] + pi[1] - pj[0] - pj[1], pi[0] - pj[0])) % MOD) % MOD;
				}
			}
			pi[2] = (int) ((long) nCr(pi[0] + pi[1], pi[0]) + MOD - excluded) % MOD;
		}
		System.out.println(ps[k][2]);
	}

	static final int K = 1000;
	static final int MOD = K * K * K + 7;
	static final int MAX = 100 * K + 1;

	static int[] factoriors = new int[2 * MAX + 1];
	static int[] invFactoriors = new int[MAX + 1];

	static void initFactoriors() {
		long acc = 1;
		int MAX2 = 2 * MAX;
		factoriors[0] = 1;
		for (int i = 1; i < MAX2; i++) {
			acc = (acc * i) % MOD;
			factoriors[i] = (int) acc;
		}

		acc = 1;
		BigInteger mod = BigInteger.valueOf(MOD);
		invFactoriors[0] = 1;
		for (int i = 1; i < MAX; i++) {
			acc = (acc * BigInteger.valueOf(i).modInverse(mod).intValue()) % MOD;
			invFactoriors[i] = (int) acc;
		}
	}

	static int nCr(int n, int r) {
		return (int) (((((long) factoriors[n] * invFactoriors[r]) % MOD) * invFactoriors[n - r]) % MOD);
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
