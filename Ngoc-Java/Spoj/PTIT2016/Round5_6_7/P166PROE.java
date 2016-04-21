import java.io.*;
import java.util.*;

class P166PROE {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		//long s = System.currentTimeMillis();

		int MAX = 100001;
		int[][] preItems = new int[MAX][];
		int[] nPreItems = new int[MAX];
		Arrays.fill(nPreItems, -1);

		int n = ni();
		int limit = 0;
		for (int i = 0; i < n; i++) {
			int value = ni();
			preItems[value] = new int[8];
			nPreItems[value] = 0;
			limit = Math.max(limit, value);
		}

		boolean[] primes = new boolean[limit + 1];
		Arrays.fill(primes, true);

		for (int i = 2; i < limit; i++) {
			if (primes[i]) {
				int pre = -1;
				for (int j = i; j <= limit; j += i) {
					primes[j] = false;
					if (nPreItems[j] >= 0) {
						if (pre > 0) {
							preItems[j][nPreItems[j]++] = pre;
						}
						pre = j;
					}
				}
			}
		}

		int[] maxValues = new int[limit + 1];
		int result = 0;
		for (int i = 1; i <= limit; i++) {
			if (nPreItems[i] < 0) {
				continue;
			}

			int maxValue = 0;
			int[] list = preItems[i];
			int len = nPreItems[i];
			for (int j = 0; j < len; j++) {
				maxValue = Math.max(maxValue, maxValues[list[j]]);
			}
			maxValues[i] = maxValue + 1;
			result = Math.max(result, maxValue + 1);
		}

		System.out.println(result);
		//System.out.println(System.currentTimeMillis() - s + "ms");
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
}
