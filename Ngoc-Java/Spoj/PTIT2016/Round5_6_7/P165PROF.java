import java.io.*;
import java.util.*;

class P165PROF {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		int MAXRANGE = 1000001;
		boolean[] flags = new boolean[MAXRANGE];
		int n = ni();
		for (int i = 0; i < n; i++) {
			flags[ni()] = true;
		}

		int max = 0;
		for (int i = MAXRANGE - 1; i > max; i--) {
			if (flags[i]) {
				for (int j = i - max - 1; j > max; j--) {
					if (flags[j]) {
						max = Math.max(max, i % j);
					}
				}
			}
		}
		System.out.println(max);
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
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
}
