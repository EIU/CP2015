import java.io.*;
import java.util.*;

class P167PROG {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int n = ni();
		int l = ni();
		int r = ni();

		long[] boys = new long[n + 1];
		long[] girls = new long[n + 1];
		long[] partialSum = new long[n + 2];
		for (int i = 1; i <= n; i++) {
			boys[i] = ni();
			girls[i] = ni();
			partialSum[i] = partialSum[i - 1] + boys[i] - girls[i];
		}

		int[] maxValues = new int[n + 2];
		Arrays.fill(maxValues, -3 * n);
		maxValues[0] = 0;

		TreeSet<Integer> rangeValueTree = new TreeSet<Integer>((i1, i2) ->
		{
			int valueDiff = maxValues[i1] - maxValues[i2];
			if (valueDiff != 0) {
				return valueDiff;
			}
			long sumDiff = partialSum[i2] - partialSum[i1];
			if (sumDiff > 0) {
				return 1;
			} else if (sumDiff < 0) {
				return -1;
			}
			return i1 - i2;
		});

		partialSum[n + 1] = Long.MIN_VALUE / 2;
		rangeValueTree.add(0);
		for (int i = 0; i <= n - l; i++) {
			if (i - r + l - 1 >= 0) {
				rangeValueTree.remove(i - r + l - 1);
			}
			rangeValueTree.add(i);

			Integer maxIndex = rangeValueTree.last();
			long diff = partialSum[i + l] - partialSum[maxIndex];
			int newValue = maxValues[maxIndex] + (diff > 0 ? 1 : (diff < 0 ? -1 : 0));
			maxValues[i + l] = newValue;

			maxValues[n + 1] = maxValues[maxIndex] - 1;
			maxIndex = rangeValueTree.lower(n + 1);
			if (maxIndex != null) {
				diff = partialSum[i + l] - partialSum[maxIndex];
				newValue = maxValues[maxIndex] + (diff > 0 ? 1 : (diff < 0 ? -1 : 0));
				if (newValue > maxValues[i + l]) {
					maxValues[i + l] = newValue;
				}
			}
		}

		System.out.println(maxValues[n]);
	}

	/* *
	 */

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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
