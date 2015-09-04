import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main_412 {

	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		// out.println(System.currentTimeMillis() - s + "ms");
		out.flush();
	}

	static final int MAXSIZE = 101;
	static int[][] mapMinValues = new int[MAXSIZE][MAXSIZE];
	static int[][] mapMaxValues = new int[MAXSIZE][MAXSIZE];

	static int[][] mapOldMinValues = new int[MAXSIZE][MAXSIZE];
	static int[][] mapOldMaxValues = new int[MAXSIZE][MAXSIZE];

	static void solve() {
		int R = ni();
		int C = ni();
		int deep = Math.min(R, C);

		int[][] gridValue = new int[R][C];
		int[] minValues = new int[deep];
		int[] maxValues = new int[deep];

		int maxValue = 100 * 1000 + 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				mapMinValues[i][j] = mapMaxValues[i][j] = gridValue[i][j] = ni();
			}
			Arrays.fill(mapMinValues[i], maxValue);
			Arrays.fill(mapOldMinValues[i], maxValue);
			Arrays.fill(mapMaxValues[i], -1);
			Arrays.fill(mapOldMaxValues[i], -1);
		}

		int pre, top, left;
		for (int d = 0; d < deep; d++) {
			int min = maxValue;
			int max = -1;
			for (int i = d; i < R; i++) {
				for (int j = d; j < C; j++) {
					int value = gridValue[i][j];

					pre = ((d > 0 && i > 0 && j > 0) ? mapOldMinValues[i - 1][j - 1] : 0) + value;
					top = i > d ? mapMinValues[i - 1][j] : maxValue;
					left = j > d ? mapMinValues[i][j - 1] : maxValue;
					pre = (pre <= top && pre <= left) ? pre : (top <= left ? top : left);
					mapMinValues[i][j] = pre;
					if (pre < min) {
						min = pre;
					}

					pre = ((d > 0 && i > 0 && j > 0) ? mapOldMaxValues[i - 1][j - 1] : 0) + value;
					top = i > d ? mapMaxValues[i - 1][j] : -1;
					left = j > d ? mapMaxValues[i][j - 1] : -1;
					pre = (pre >= top && pre >= left) ? pre : (top >= left ? top : left);
					mapMaxValues[i][j] = pre;
					if (pre > max) {
						max = pre;
					}
				}
			}

			for (int i = d; i < R; i++) {
				for (int j = d; j < C; j++) {
					mapOldMinValues[i][j] = mapMinValues[i][j];
					mapOldMaxValues[i][j] = mapMaxValues[i][j];
				}
			}
			minValues[d] = min;
			maxValues[d] = max;
		}

		int result = 0;
		int deep2 = deep / 2;
		for (int i = 0; i < deep2; i++) {
			int newValue = maxValues[i] - minValues[2 * i + 1];
			if (newValue > result) {
				result = newValue;
			}
		}
		out.println(result);
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096 * 10];
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
