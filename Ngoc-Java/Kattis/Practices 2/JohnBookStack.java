import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JohnBookStack {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		long result = 0;
		int n = ni();
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			int weight = weights[i] = ni();
			if (i == 0 || weight >= weights[i - 1]) {
				continue;
			}

			int j = i - 1;
			while (j >= 0 && weights[j] >= weight) {
				weights[j + 1] = weights[j];
				j--;
			}
			j++;
			weights[j] = weight;

			int preCount = 1;
			long pre = 1;
			for (int k = 1; k <= j; k++) {
				if (weights[k - 1] < weights[k]) {
					pre = pre * (preCount + 1);
					preCount = 1;
				} else if (weights[k - 1] == weights[k]) {
					preCount++;
				}
			}
			result += pre;
		}

		System.out.println(result);
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
