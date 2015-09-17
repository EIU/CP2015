package katis;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class U11849 {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		while (true) {
			int n = ni();
			int m = ni();
			if (n == 0 && m == 0) {
				break;
			}
			int res = 0;
			int[] a = new int[n];
			int[] b = new int[m];
			for (int i = 0; i < n; i++) {
				a[i] = ni();
			}
			for (int i = 0; i < m; i++) {
				b[i] = ni();
			}
			int lena = a.length;
			int lenb = b.length;
			int i = 0;
			int j = 0;
			while (true) {
				if (a[i] < b[j]) {
					i++;
					if (i > lena - 1) {
						break;
					}
				} else if (a[i] == b[j]) {
					res++;
					i++;
					j++;
					if (i > lena - 1 || j > lenb - 1) {
						break;
					}
				} else {
					j++;
					if (j > lenb - 1) {
						break;
					}
				}
			}
			System.out.println(res);
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

}