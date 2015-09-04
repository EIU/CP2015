import java.math.BigInteger;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class P152PROH_NguyenAmPhuAm {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		is = System.in;
		out = new PrintWriter(System.out);

		solve();

		out.flush();

		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		String s = ns();
		int len = s.length() + 1;
		int[] sums = new int[len];

		int sum = 0;
		int baseValue = 0;
		for (int i = 1; i < len; i++) {
			char c = s.charAt(i - 1);
			c |= 32; // To lower
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				sum++;
			}
			else {
				sum -= 2;
			}
			baseValue = Math.min(baseValue, sum);
			sums[i] = sum;
		}

		int[] firstIndexes = new int[2 * len];
		Arrays.fill(firstIndexes, len);

		// Projection
		for (int i = 0; i < len; i++) {
			int index = sums[i] - baseValue;
			firstIndexes[index] = Math.min(firstIndexes[index], i);
		}

		// Make sure firstIndex in increasing order
		int minValue = firstIndexes[2 * len - 1];
		for (int i = 2 * len - 1; i >= 0; i--) {
			minValue = Math.min(minValue, firstIndexes[i]);
			firstIndexes[i] = minValue;
		}

		int minLen = 0;
		int count = 0;
		for (int i = 1; i < len; i++) {
			int subLen = i - firstIndexes[sums[i] - baseValue];
			if (subLen > minLen) {
				minLen = subLen;
				count = 1;
			}
			else if (subLen == minLen) {
				count++;
			}
		}

		if (minLen == 0) {
			System.out.println("No solution");
		}
		else {
			System.out.println(minLen + " " + count);
		}
		// Note: For continuous list => there is another shorter & nicer code
		// This code can be used in more general problem

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
