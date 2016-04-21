import java.io.*;
import java.util.*;

class P165PROD {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		int n = ni();
		int m = ni();
		int k = ni();
		int p = ni();

		long[] sumRows = new long[n];
		long[] sumColumns = new long[m];

		int item;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				item = ni();
				sumRows[i] += item;
				sumColumns[j] += item;
			}
		}

		PriorityQueue<Long> queueRow = new PriorityQueue<Long>();
		for (int i = 0; i < n; i++) {
			queueRow.add(-sumRows[i]);
		}

		long[] predictSumRows = new long[k + 1];
		for (int i = 1; i < k + 1; i++) {
			long top = -queueRow.poll();
			predictSumRows[i] = predictSumRows[i - 1] + top;
			top -= m * p;
			queueRow.add(-top);
		}

		PriorityQueue<Long> queueColumn = new PriorityQueue<Long>();
		for (int j = 0; j < m; j++) {
			queueColumn.add(-sumColumns[j]);
		}

		long[] predictSumColumns = new long[k + 1];
		for (int j = 1; j < k + 1; j++) {
			long top = -queueColumn.poll();
			predictSumColumns[j] = predictSumColumns[j - 1] + top;
			top -= n * p;
			queueColumn.add(-top);
		}

		long result = Long.MIN_VALUE;
		for (int i = 0; i <= k; i++) {
			long adjustValue = (long) i * (k - i) * p;
			long value = predictSumColumns[i] + predictSumRows[k - i] - adjustValue;
			result = Math.max(result, value);
		}

		System.out.println(result);
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
