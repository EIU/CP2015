import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BalancedDiet {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
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

		// final int MAX = 100001;
		int MAX = sum + 1;
		int[] needs = new int[MAX];

		for (int i = 0; i < m; i++) {
			double fi = (double) weights[i] / sum;
			int bi = bs[i];

			int j = 0;
			while (j < MAX) {
				j = (int) Math.ceil((bi + 1) / fi - k - 0.000000001d);
				if (j < MAX) {
					needs[j]++;
					bi++;
				}
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
}
