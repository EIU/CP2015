import java.io.*;
import java.util.*;

class PTIT016G_Brute {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int k = ni();
		int M = ni();

		for (int i = 0; i < k; i++) {
			n -= ni();
		}

		if (n < 0) {
			System.out.println(0);
			return;
		}

		// Result = C(k-1, n+k-1) % M. M is any number in range 10^9
		long[] map = new long[k];
		map[0] = 1;
		for (int i = 0; i <= n; i++) {
			long pre = 0;
			for (int j = 0; j < k; j++) {
				pre = map[j] = (pre + map[j]) % M;
			}
		}

		System.out.println(map[k - 1]);

	}
	static int divide(int[] numbers, int M, int n, int k, int v) {
		int u = v - (n + 1) % v;
		if (u == v) {
			u = 0;
		}
		while (v > 1 && u < k - 1) {
			int div = gcd(numbers[u], v);
			numbers[u] /= div;
			v /= div;
			u += v;
			if (v > 1) {
				System.out.println();
			}
		}
		return v;
	}

	static int gcd(int a, int b) {
		while (b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

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
}
