import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MovieCollection {
	static InputStream is;

	static StringBuilder outBf = new StringBuilder();

	public static void main(String[] args) throws Exception {
		is = System.in;

		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		System.out.print(outBf);
	}

	private static void solve() {
		int m = ni();
		int r = ni();

		MAX = m + r + 1;
		Arrays.fill(bit, 0, MAX, 0);

		for (int i = 1; i <= m; i++) {
			positions[i] = m - i + 1;
		}

		for (int i = 1; i <= m; i++) {
			setValue(i, 1);
		}

		for (int i = 1; i <= r; i++) {
			int q = ni();
			outBf.append((m - getValue(positions[q])) + " ");
			setValue(positions[q], -1);
			positions[q] = i + m;
			setValue(positions[q], 1);
		}
		outBf.append("\n");
	}

	static int MAX = 200001;
	static int[] positions = new int[MAX];
	static int[] bit = new int[MAX];

	static void setValue(int i, int value) {
		for (; i < MAX; i += (i & -i)) {
			bit[i] += value;
		}
	}

	static int getValue(int i) {
		int sum = 0;
		for (; i > 0; i -= (i & -i)) {
			sum += bit[i];
		}
		return sum;
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
