import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class MathMagic {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		initStatus();

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static int R = 1;
	static int G = 8;
	static int B = 64;
	static int Y = 512;

	static int mapColor(char c) {
		if (c == 'R')
			return 1;
		if (c == 'G')
			return 8;
		if (c == 'B')
			return 64;
		if (c == 'Y')
			return 512;
		return -1;
	}

	static void copyValids(long[] s, long[] t, long minus) {
		for (int i : valids) {
			t[i] = s[i];
			if (t[i] != MIN) {
				t[i] -= minus;
			}
		}
	}

	static int getS(String box) {
		return (int) box.charAt(1) + (int) box.charAt(3) + (int) box.charAt(5) + (int) box.charAt(7) - 4 * '0';
	}

	static int getX(String box, int i) {
		return box.charAt(i) - '0';
	}

	static int getStatus(String box) {
		int status = 0;
		for (int i = 0; i < 8; i += 2) {
			status += mapColor(box.charAt(i));
		}
		return status;
	}

	static void initStatus() {
		int[] vds = new int[MABSTATUS];
		int index = 0;
		for (int i = 0; i < MABSTATUS; i++) {
			int total = (i & 7) + ((i >> 3) & 7) + ((i >> 6) & 7) + ((i >> 9) & 7);
			if (total == 4) {
				vds[index++] = i;
			}
		}
		valids = Arrays.copyOfRange(vds, 0, index);
	}

	static final int MABSTATUS = 2048 + 5;
	static final long MIN = Long.MIN_VALUE;
	static long[] statuses = new long[MABSTATUS];
	static long[] newStatuses = new long[MABSTATUS];
	static int[] valids;

	static void solve() {
		int n = ni();
		Arrays.fill(statuses, MIN);
		Arrays.fill(newStatuses, MIN);
		String box = ns();
		statuses[getStatus(box)] = 0;

		for (int i = 0; i < n - 1; i++) {
			box = ns();
			int s = getS(box);
			copyValids(statuses, newStatuses, s);

			for (int j = 0; j < 8; j += 2) {
				int color = mapColor(box.charAt(j));
				int opColor = mapColor(box.charAt((j + 4) % 8));
				int x = getX(box, j + 1);

				int score = 0;
				if (color == R) {
					score = s * x;
				}
				else if (color == G) {
					score = s + x;
				}
				else if (color == B) {
					score = s - x;
				}
				else {
					score = (x != 0) ? s / x : 0;
				}

				for (int k : valids) {
					int mask = color | (color << 1) | (color << 2);
					if ((k & mask) != 0 && statuses[k] != MIN) {
						int newStatus = k - color + opColor;
						newStatuses[newStatus] = Math.max(newStatuses[newStatus],
								statuses[k] + score);
					}
				}
			}

			long[] temp = statuses;
			statuses = newStatuses;
			newStatuses = temp;
		}

		long max = Long.MIN_VALUE;
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
