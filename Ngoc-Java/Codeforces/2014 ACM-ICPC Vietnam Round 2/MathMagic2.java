import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class MathMagic2 {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		initValidRange();

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static final int R = 1;
	static final int G = 8;
	static final int B = 64;
	static final int Y = 512;

	static int getColorValue(char c) {
		switch (c) {
		case 'R':
			return R;
		case 'G':
			return G;
		case 'B':
			return B;
		case 'Y':
			return Y;
		}
		return -1;
	}

	static int getScore(int color, int s, int x) {
		switch (color) {
		case R:
			return s * x;
		case G:
			return s + x;
		case B:
			return s - x;
		case Y:
			return (x > 0) ? (s / x) : 0;
		}
		return -1;
	}

	static void initValidRange() {
		int[] vds = new int[100];
		int valid = 0;
		for (int i = 0; i < VALIDRANGE; i++) {
			int total = (i & 7) + ((i >> 3) & 7) + ((i >> 6) & 7) + ((i >> 9) & 7);
			if (total == 4) {
				vds[valid++] = i;
			}
		}
		valids = Arrays.copyOfRange(vds, 0, valid);
	}

	static final int VALIDRANGE = 2048 + 5;
	static final int MININT = Integer.MIN_VALUE;
	static int[] statuses = new int[VALIDRANGE];
	static int[] newStatuses = new int[VALIDRANGE];
	static int[] valids;

	static void solve() {
		int n = ni();
		Arrays.fill(statuses, MININT);
		Arrays.fill(newStatuses, MININT);
		String box = ns();
		int status = getColorValue(box.charAt(0)) + getColorValue(box.charAt(2))
				+ getColorValue(box.charAt(4)) + getColorValue(box.charAt(6));
		statuses[status] = 0;

		for (int i = 0; i < n - 1; i++) {
			box = ns();
			int[] cs = new int[] { getColorValue(box.charAt(0)), getColorValue(box.charAt(2)),
					getColorValue(box.charAt(4)), getColorValue(box.charAt(6)) };
			int[] vs = new int[] { box.charAt(1) - '0', box.charAt(3) - '0',
					box.charAt(5) - '0', box.charAt(7) - '0' };

			int s = vs[0] + vs[1] + vs[2] + vs[3];

			for (int k : valids) {
				if (statuses[k] != MININT) {
					newStatuses[k] = statuses[k] - s;
				}
			}

			for (int k : valids) {
				if (statuses[k] == MININT) {
					continue;
				}

				for (int j = 0; j < 4; j++) {
					int c = cs[j];
					int cp = cs[(j + 2) & 4];
					int v = vs[j];
					if ((k & (c | (c << 1) | (c << 2))) != 0) {
						int newStatus = k - c + cp;
						newStatuses[newStatus] =
								Math.max(newStatuses[newStatus],
										statuses[k] + getScore(c, s, v));
					}
				}
			}
			int[] temp = statuses;
			statuses = newStatuses;
			newStatuses = temp;
		}

		int max = Integer.MIN_VALUE;
		for (int i : valids) {
			max = Math.max(max, statuses[i]);
		}
		System.out.println(max);
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
