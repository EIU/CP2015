import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

public class ForeverYoung {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		long y = nl();
		long l = nl();

		for (; l < 100; l++) {
			long d1 = l / 10;
			long d0 = l % 10;
			// d1*b + d0 = y
			if ((y - d0) % d1 == 0) {
				System.out.println((y - d0) / d1);
				return;
			}
		}

		for (; l < 1000; l++) {
			// d2*b^2 + d1*b = y - d0
			long b = squareroot(l / 100, (l / 10) % 10, y - l % 10);
			if (b > 0) {
				System.out.println(b);
				return;
			}
		}

		for (int b = 1000000; b >= 10; b--) {
			long tempy = y;
			long value = 0;
			long pow10 = 1;
			while (tempy > 0) {
				long d = tempy % b;
				tempy /= b;
				if (d < 0 || 9 < d) {
					value = -1;
					break;
				}
				value += pow10 * d;
				pow10 *= 10;
			}
			if (value >= l) {
				System.out.println(b);
				return;
			}
		}
	}

	/*
	 * a, b, c > 0: a*x*x + b*x = c
	 */
	static long squareroot(long a, long b, long c) {
		long left = 10;
		long right = (long) Math.sqrt(c / a) + 1;
		while (left < right - 1) {
			long mid = (left + right) / 2;
			long value = (a * mid + b) * mid;
			if (value == c) {
				return mid;
			} else if (value > c) {
				right = mid;
			}
			else {
				left = mid;
			}
		}
		return 0;
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
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
