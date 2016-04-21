import java.io.*;
import java.util.*;

class P166PROD {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		long n = ni();
		long m = ni();

		long k = nl();

		long left = 1;
		long right = m * n + 1;

		while (left < right - 1) {
			long mid = (left + right) / 2;
			long nLower = 0;
			long equal = 0;
			for (int i = 1; i <= n; i++) {
				long div = mid / i;
				if (div * i == mid && div <= m) {
					equal++;
					nLower += div - 1;
				}
				else {
					nLower += Math.min(div, m);
				}
			}
			if (nLower + equal < k) {
				left = mid;
			} else if (nLower >= k) {
				right = mid;
			}
			else /* if (equal > 0) */{
				left = mid;
				break;
			}
		}
		System.out.println(left);
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
