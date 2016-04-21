import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

class P167PROF {
	static InputStream is;
	public static void main(String[] args) {
		is = System.in;
		int T = ni();
		StringBuilder outBf = new StringBuilder();
		for (int t = 0; t < T; t++) {
			outBf.append(solve() + "\n");
		}
		System.out.print(outBf);
	}

	static long[] values = new long[70000];
	static boolean[] usedNumbers = new boolean[70000];

	public static long solve() {
		int n = ni();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = ni();
			}
		}

		Arrays.fill(values, Long.MAX_VALUE / 2);
		Arrays.fill(usedNumbers, false);
		values[0] = 0;
		usedNumbers[0] = true;

		List<Integer> preList = new ArrayList<Integer>();
		List<Integer> curList;
		preList.add(0);
		for (int i = 0; i < n; i++) {
			curList = new ArrayList<Integer>();
			for (Integer number : preList) {
				for (int bit = 0, flag = 1; bit < n; bit++, flag <<= 1) {
					if ((number & flag) == 0) {
						int newNumber = number | flag;
						if (!usedNumbers[newNumber]) {
							curList.add(newNumber);
							usedNumbers[newNumber] = true;
						}
						values[newNumber] = Math.min(values[newNumber], values[number] + matrix[i][bit]);
					}
				}
			}
			preList = curList;
		}
		return values[preList.get(0)];
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
