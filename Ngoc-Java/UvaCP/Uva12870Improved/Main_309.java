import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main_309 {

	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
	}

	static final int MAXSIZE = 101;
	static int[][] mapMinValues = new int[MAXSIZE][MAXSIZE];
	static int[][] mapMaxValues = new int[MAXSIZE][MAXSIZE];
	static int[][] gridValue = new int[MAXSIZE][MAXSIZE];

	static int[] minValues = new int[MAXSIZE];
	static int[] maxValues = new int[MAXSIZE];
	static int maxValue = 100 * 1000 + 1;

	static void solve() {
		int R = ni();
		int C = ni();
		int deep = Math.min(R, C);

		for (int i = 1; i <= R; i++) {
			int[] row = gridValue[i];
			for (int j = 1; j <= C; j++) {
				row[j] = ni();
			}
			Arrays.fill(mapMinValues[i], 0);
			Arrays.fill(mapMaxValues[i], 0);
		}

		for (int d = 0; d < deep; d++) {
			int min = maxValue;
			int max = -1;
			for (int i = R; i > d; i--) {
				int[] preMin = mapMinValues[i - 1];
				int[] preMax = mapMaxValues[i - 1];
				int[] minRow = mapMinValues[i];
				int[] maxRow = mapMaxValues[i];
				for (int j = C; j > d; j--) {
					int value = gridValue[i][j];
					minRow[j] = preMin[j - 1] + value;
					maxRow[j] = preMax[j - 1] + value;
				}
			}
			for (int i = d + 1; i <= R; i++) {
				int[] preMin = mapMinValues[i - 1];
				int[] preMax = mapMaxValues[i - 1];
				int[] minRow = mapMinValues[i];
				int[] maxRow = mapMaxValues[i];
				int minLeft = maxValue;
				int maxLeft = -1;
				for (int j = d + 1; j <= C; j++) {
					minLeft = minRow[j] = getMin(minRow[j], i > d + 1 ? preMin[j] : maxValue, minLeft);
					maxLeft = maxRow[j] = getMax(maxRow[j], i > d + 1 ? preMax[j] : -1, maxLeft);
					if (min > minLeft) {
						min = minLeft;
					}
					if (max < maxLeft) {
						max = maxLeft;
					}
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

	static int getMax(int x, int y, int z) {
		x = (x > y ? x : y);
		return (x > z ? x : z);
	}

	static int getMin(int x, int y, int z) {
		x = (x < y ? x : y);
		return (x < z ? x : z);
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
