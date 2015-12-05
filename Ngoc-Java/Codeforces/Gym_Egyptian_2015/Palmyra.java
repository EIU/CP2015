import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Palmyra {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int N = ni();
		int M = ni();

		int[][] matrix2 = new int[N][M];
		int[][] matrix3 = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int v = ni();
				matrix2[i][j] = pow2s[v];
				matrix3[i][j] = pow3s[v];
			}
		}

		int nState = MAXPOW3 * (M + N) + 1;
		int[][] status = new int[M][nState];
		for (int j = 0; j < M; j++) {
			Arrays.fill(status[j], -1);
		}
		status[0][0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int pow2 = matrix2[i][j];
				int pow3 = matrix3[i][j];
				for (int k = nState - MAXPOW3 - 1; k >= 0; k--) {
					if (status[j][k] >= 0) {
						status[j][k + pow3] = status[j][k] + pow2;
					}
					if (j > 0 && status[j - 1][k] >= 0) {
						status[j][k + pow3] = Math.max(status[j][k + pow3], status[j - 1][k] + pow2);
					}
				}
			}
		}

		int countZero = 0;
		for (int j = 0; j < status[M - 1].length; j++) {
			countZero = Math.max(countZero, Math.min(j, status[M - 1][j]));
		}

		System.out.println(countZero);
	}

	final static int MAXVALUE = 1001;
	final static int MAXPOW3 = 6;
	static int[] pow2s = new int[MAXVALUE + 1];
	static int[] pow3s = new int[MAXVALUE + 1];

	static void preparePower(int p, int[] pows) {
		int i = p;
		while (i < MAXVALUE) {
			for (int j = i; j < MAXVALUE; j += i) {
				pows[j] += 1;
			}
			i *= p;
		}
	}

	static {
		preparePower(2, pow2s);
		preparePower(3, pow3s);
	}

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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