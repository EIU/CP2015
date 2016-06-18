import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class StringTheory {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		do {
			solve();
		} while (hasNext());
	}

	static void solve() {
		n = ni();
		quotes = new int[n];
		for (int i = 0; i < n; i++) {
			sum += (quotes[i] = ni());
		}

		if (sum % 2 == 1 || sum == 4) {
			System.out.println("no quotation");
			return;
		}

		for (int k = 100; k > 1; k--) {
			if (testLevel(k)) {
				System.out.println(k);
				return;
			}
		}

		System.out.println(sum == 2 ? 1 : "no quotation");
	}

	static int n;
	static int[] quotes;
	static int sum;

	static boolean testLevel(int k) {
		if (sum < k * (k + 1)) {
			return false;
		}

		int level = k;
		int i = 0;
		int quote = quotes[i];
		while (level > 0 && quote >= level) {
			quote -= level;
			level--;
			if (quote == 0) {
				i++;
				quote = quotes[i];
			}
		}
		if (level > 0) {
			return false;
		}

		level = k;
		i = n - 1;
		quote = quotes[i];
		while (level > 0 && quote >= level) {
			quote -= level;
			level--;
			if (quote == 0) {
				i--;
				quote = quotes[i];
			}
		}

		return level == 0;
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
