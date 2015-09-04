import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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
		//out.println(System.currentTimeMillis() - s + "ms");
		out.flush();
	}

	static final int MAXSIZE = 101;
	static int[][][] mapMaxValues = new int[MAXSIZE][MAXSIZE][MAXSIZE];
	static int[][][] mapMinValues = new int[MAXSIZE][MAXSIZE][MAXSIZE];

	static void solve() {
		int R = ni();
		int C = ni();
		int deep = Math.min(R, C) + 1;

		int[][] gridValue = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				gridValue[i][j] = ni();
			}
		}

		int maxValue = 100 * 1000 + 1;
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				Arrays.fill(mapMaxValues[i][j], -1);
				mapMaxValues[i][j][0] = 0;
				Arrays.fill(mapMinValues[i][j], maxValue);
				mapMinValues[i][j][0] = 0;
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int value = gridValue[i][j];
				int[] values = mapMinValues[i][j];
				int deepij = (i < j ? i : j) + 1;
				if (i == 0 || j == 0) {
					values[1] = getMin(value,
							i > 0 ? mapMinValues[i - 1][0][1] : maxValue,
							j > 0 ? mapMinValues[0][j - 1][1] : maxValue);
				}
				else {
					int[] preValues = mapMinValues[i - 1][j - 1];
					int[] preTopValues = mapMinValues[i - 1][j];
					int[] preLeftValues = mapMinValues[i][j - 1];

					values[1] = getMin(value, preTopValues[1], preLeftValues[1]);
					for (int k = 2; k <= deepij; k++) {
						values[k] = getMin(preValues[k - 1] + value,
								preTopValues[k], preLeftValues[k]);
					}
				}

				value = gridValue[i][j];
				values = mapMaxValues[i][j];
				if (i == 0 || j == 0) {
					values[1] = getMax(value,
							i > 0 ? mapMaxValues[i - 1][0][1] : -1,
							j > 0 ? mapMaxValues[0][j - 1][1] : -1);
				}
				else {
					int[] preValues = mapMaxValues[i - 1][j - 1];
					int[] preTopValues = mapMaxValues[i - 1][j];
					int[] preLeftValues = mapMaxValues[i][j - 1];

					values[1] = getMax(value, preTopValues[1], preLeftValues[1]);
					for (int k = 2; k <= deepij; k++) {
						values[k] = getMax(preValues[k - 1] + value,
								preTopValues[k], preLeftValues[k]);
					}
				}
			}
		}
		int[] minValues = mapMinValues[R - 1][C - 1];
		minValues[0] = 0;
		int[] maxValues = mapMaxValues[R - 1][C - 1];
		maxValues[0] = 0;

		int result = 0;
		int deep2 = (deep + 1) / 2;
		for (int i = 0; i < deep2; i++) {
			int newValue = maxValues[i] - minValues[i * 2];
			if (newValue > result) {
				result = newValue;
			}
		}
		out.println(result);
	}

	static int getMax(int x, int y, int z) {
		x = (x > y ? x : y);
		return (x > z ? x : z);
		// return x > t ? x : t;
	}

	static int getMin(int x, int y, int z) {
		x = (x < y ? x : y);
		return (x < z ? x : z);
		// return x < t ? x : t;
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
