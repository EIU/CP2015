import java.io.*;
import java.util.*;

class PTIT016J {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int n = ni();
		int m = ni();
		int k = ni();

		int[] fans = new int[n];

		boolean[][] adjMatrix = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			fans[i] = ni();
		}

		for (int i = 0; i < m; i++) {
			int v = ni() - 1;
			int u = ni() - 1;
			adjMatrix[v][u] = adjMatrix[u][v] = true;
		}

		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][0] = fans[i];
			for (int j = 0; j < i; j++) {
				if (!adjMatrix[i][j]) {
					for (int t = 0; t < i - 1; t++) {
						if (dp[j][t] > 0) {
							int value = dp[i][t + 1] = dp[j][t] + fans[i];
							if (value >= dp[j][t + 1]) {
								dp[j][t + 1] = value;
							}
						}
					}
				}
			}
		}

		int result = -1;
		for (int i = 0; i < n; i++) {
			for (int t = k; t < n; t++) {
				result = Math.max(dp[i][t], result);
			}
		}

		System.out.println(result);
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
