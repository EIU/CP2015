import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

class A {
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
		int n = ni();
		int[] arrays = new int[n];
		for (int i = 0; i < n; i++) {
			arrays[i] = ni();
		}
		Arrays.sort(arrays);

		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> counts = new ArrayList<Integer>();

		int count = 1;
		for (int i = 0; i < n; i++) {
			if (i == n - 1 || arrays[i] != arrays[i + 1]) {
				values.add(arrays[i]);
				counts.add(count);
				count = 1;
			}
			else {
				count++;
			}
		}

		BigInteger P = BigInteger.valueOf(1000000007);
		int result = 0;
		int len = values.size();
		for (int i = 0; i < len; i++) {
			if (counts.get(i) > 0) {
				int v = values.get(i);
				int inv = BigInteger.valueOf(v).modInverse(P).intValue();
				if (v == inv) { // v = 1 || v == P - 1
					result += counts.get(i) / 2;
					counts.set(i, 0);
				}
				else {
					int invIndex = Collections.binarySearch(values, inv);
					if (invIndex >= 0) {
						result += Math.min(counts.get(i), counts.get(invIndex));
						counts.set(i, 0);
						counts.set(invIndex, 0);
					}
				}
			}
		}
		System.out.println(result);
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
