package uva;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Scanner;

public class ArmWrestlingTournament {

	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int test = ni();
		while (test-- > 0) {
			int n = ni();
			int k = ni();
			int l = (int) Math.pow(2, n);
			HashMap<Integer, StringBuilder> map = new HashMap<Integer, StringBuilder>();
			int[] a = new int[l];
			for (int i = 0; i < l; i++) {
				a[i] = ni();
				map.put(i, new StringBuilder(""));
			}
			int[] b = Arrays.copyOf(a, l);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < l; j++) {
					if (a[j] < 0) {
						continue;
					}
					for (int m = j + 1; m < l; m++) {
						if (a[m] < 0) {
							continue;
						}
						if (a[j] < a[m]) {
							a[m] = a[m] - a[j] + k;
							if (a[m] > b[m]) {
								a[m] = b[m];
							}
							a[j] = -1;
							map.put(m, map.get(m).append((j + 1) + " "));
							j = m;
							break;
						} else {
							a[j] = a[j] - a[m] + k;
							if (a[j] > b[j]) {
								a[j] = b[j];
							}
							map.put(j, map.get(j).append((m + 1) + " "));
							a[m] = -1;
							j = m;
							break;
						}
					}
				}
			}
			for (int i = 0; i < l; i++) {
				if (a[i] > 0) {
					System.out.println(i + 1);
					String s = map.get(i).deleteCharAt(map.get(i).length() - 1)
							.toString();
					System.out.println(s);
					break;
				}
			}
		}
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
