import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "4	11	12	8	11	11	10	4	9	6	8	9	10	7	8	11	11	7	6	6	9	6	6	8	6	11	4	5	7	8	7	7	10	5	6	4	6	11	9	10 10	12	9	4	5	6	4	4	4	7	9	6	9	8	6	7	12	11 92 2991 9 11 6 7 6 9 7 5 7 6 7 7 8 4 8 5 8 6 8 7 9 11 6 3 5 6 9 11 10 12 12 105 37 107 89 1003 92 2991 60 96 60 87 60 50 100 3000 99 2999 56 31 0";
	// static String INPUT = "17 17 0";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();

		int t = 1;
		int n = ni();
		while (n != 0) {
			int m = ni();
			solve(t, n, m);
			n = ni();
			t++;
		}

		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static int MAXMATCH = 128;
	static int MAXNUM = 3072;
	static short[][] maxNums = new short[MAXMATCH][MAXNUM];
	static boolean[][] backTracks = new boolean[MAXMATCH][MAXNUM];

	static int matches[] = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

	/**
	 * @solution:
	 */
	static void solve(int t, int n, int m) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				maxNums[i][j] = -1;
				backTracks[i][j] = false;
			}
		}

		maxNums[0][0] = 0;
		int maxNum = 0;
		for (int i = 0; i <= n; i++) {
			for (int div = 0; div < m; div++) {
				if (maxNums[i][div] < 0) {
					continue;
				}
				for (int d = 0; d < 10; d++) {
					int next = i + matches[d];
					if (next > n) {
						continue;
					}
					int nextDiv = (div * 10 + d) % m;
					maxNums[next][nextDiv] = (short) Math.max(maxNums[next][nextDiv], maxNums[i][div] + 1);
				}
				maxNum = Math.max(maxNum, maxNums[i][0]);
			}
		}

		for (int i = n; i >= 0; i--) {
			if (maxNums[i][0] == maxNum) {
				backTracks[i][0] = true;
			}
			for (int div = m - 1; div >= 0; div--) {
				if (maxNums[i][div] < 0) {
					continue;
				}
				for (int d = 0; d < 10; d++) {
					int next = i + matches[d];
					if (next > n) {
						continue;
					}
					int nextDiv = (div * 10 + d) % m;
					if (maxNums[i][div] + 1 == maxNums[next][nextDiv]) {
						backTracks[i][div] |= backTracks[next][nextDiv];
					}
				}
			}
		}

		String result = "";
		int i = 0;
		int div = 0;
		for (int num = 0; num < maxNum; num++) {
			for (int d = 9; d >= 0; d--) {
				int next = i + matches[d];
				int nextDiv = (div * 10 + d) % m;
				if (backTracks[next][nextDiv] && maxNums[i][div] + 1 == maxNums[next][nextDiv]) {
					i = next;
					div = nextDiv;
					result += d;
					break;
				}
			}
		}
		System.out.println("Case " + t + ": " + (maxNum > 0 ? result : "-1"));
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
