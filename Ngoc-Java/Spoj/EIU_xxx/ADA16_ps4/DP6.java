import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class DP6 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			int n = ni();
			int k = ni();

			int nCache = n + 4 * k + 1;
			long[] sumCosts = new long[nCache];
			for (int i = 2 * k + 1; i <= n + 2 * k; i++) {
				sumCosts[i] = sumCosts[i - 1] + nl();
			}

			for (int i = n + 2 * k + 1; i < nCache; i++) {
				sumCosts[i] = sumCosts[i - 1];
			}

			long[] sumEarns = new long[nCache];
			for (int i = 1; i <= n + 2 * k; i++) {
				sumEarns[i] = Math.max(sumEarns[i], sumEarns[i - 1]);
				for (int j = i + k + 1; j <= i + 2 * k; j++) {
					sumEarns[j] = Math.max(sumEarns[j], sumEarns[i] + sumCosts[j] - sumCosts[i + k]);
				}
			}

			long max = 0;
			for (int i = 0; i < nCache; i++) {
				max = Math.max(max, sumEarns[i]);
			}

			// System.out.print(sumCosts[nCache - 1] + " : ");
			System.out.println(max);
		}
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
