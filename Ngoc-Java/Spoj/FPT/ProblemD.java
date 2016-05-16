import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ProblemD {
	static InputStream is;

	final static int MAX = 200005;
	final static long MOD = (long) (1E9 + 7);
	static long[] F = new long[MAX];
	static {
		F[0] = 0;
		F[1] = F[2] = 1;
		for (int i = 3; i < MAX; i++) {
			F[i] = (F[i - 1] + F[i - 2]) % MOD;
		}
	}

	public static void main(String[] args) throws IOException {
		is = System.in;

		int n = ni();
		int m = ni();
		long[] A = new long[n + 1];

		for (int i = 1; i <= n; i++) {
			A[i] = ni();
		}

		long[] addF1 = new long[n + 2];
		long[] addF0 = new long[n + 2];

		for (int i = 0; i < m; i++) {
			int l = ni();
			int r = ni();
			int s = ni();

			addF1[l] = (addF1[l] + F[s]) % MOD;
			addF0[l] = (addF0[l] + F[s - 1]) % MOD;

			addF1[r + 1] = (MOD + addF1[r + 1] - F[s + r - l + 1]) % MOD;
			addF0[r + 1] = (MOD + addF0[r + 1] - F[s + r - l]) % MOD;
		}

		StringBuilder bf = new StringBuilder();
		long a0 = 0, a1 = 0;
		for (int i = 1; i <= n; i++) {

			a1 = (MOD + a1 + addF1[i]) % MOD;
			a0 = (MOD + a0 + addF0[i]) % MOD;

			bf.append(((a1 + A[i]) % MOD) + " ");

			long temp = a1;
			a1 = (a1 + a0) % MOD;
			a0 = temp;
		}

		System.out.println(bf);
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
