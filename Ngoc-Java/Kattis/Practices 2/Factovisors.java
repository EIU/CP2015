import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Factovisors {
	static InputStream is = System.in;

	public static void main(String[] args) throws Exception {
		StringBuilder outBf = new StringBuilder();
		do {
			int n = ni(), m = ni(), x = m;
			boolean undivided = false;
			for (int i = 2; (long) i * i <= x; i++) {
				int power = 0;
				while (x % i == 0) {
					x /= i;
					power++;
				}
				if (power > 0 && getFactorialPower(n, i) < power) {
					undivided = true;
					break;
				}
			}

			if (x == 0 || x > 1 && !undivided && getFactorialPower(n, x) < 1) {
				undivided = true;
			}

			if (undivided) {
				outBf.append(String.format("%d does not divide %d!\n", m, n));
			} else {
				outBf.append(String.format("%d divides %d!\n", m, n));
			}
		} while (hasNext());

		System.out.print(outBf);
	}

	static int getFactorialPower(long n, long p) {
		int count = 0;
		long q = p;
		while (n >= q) {
			count += n / q;
			q *= p;
		}
		return count;
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 16];
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

	static boolean hasNext() {
		return ptrbuf + 3 < lenbuf;
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
}
