import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class BalancedDiet {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		do {
			int m = ni();
			int k = ni();

			int[] weights = new int[m];
			int sum = 0;
			for (int i = 0; i < m; i++) {
				sum += weights[i] = ni();
			}

			int[] bs = new int[m];
			for (int i = 0; i < k; i++) {
				bs[ni() - 1]++;
			}

			int MAX = sum + 1; // 100001
			int[] needs = new int[MAX];

			for (int i = 0; i < m; i++) {
				long j = 0;
				int wi = weights[i];
				long x = (long) bs[i] * sum + sum - (long) wi * k + wi - 1;
				while ((j = x / wi) < MAX) {
					needs[(int) j]++;
					x += sum;
				}
			}

			int totalNeed = 0;
			int i = 1;
			for (; i < MAX; i++) {
				if (totalNeed + needs[i] > i) {
					break;
				}
				totalNeed += needs[i];
			}

			System.out.println(i < MAX ? i - 1 : "forever");
		} while (hasNext());
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 25];
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
