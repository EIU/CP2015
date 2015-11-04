import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Fenwick {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		solve();
	}

	static void solve() {
		n = ni();
		sumBIT = new long[n + 1];
		int q = ni();
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < q; i++) {
			char op = ns().charAt(0);
			if (op == '+') {
				setValue(ni() + 1, ni());
			} else {
				int pos = ni();
				bf.append(getSum(pos) + "\n");
			}
		}
		System.out.println(bf);
	}

	static int n;
	static long[] sumBIT;

	static void setValue(int i, long value) {
		for (; i <= n; i += (i & -i)) {
			sumBIT[i] += value;
		}
	}

	static long getSum(int i) {
		long value = 0;
		for (; i > 0; i -= (i & -i)) {
			value += sumBIT[i];
		}
		return value;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 20];
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
}