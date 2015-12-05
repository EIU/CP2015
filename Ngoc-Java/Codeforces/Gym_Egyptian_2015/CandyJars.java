import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CandyJars {
	static InputStream is;

	// 1..(N-1) Loose
	// N..N(N-1) Win | F = N(N-1)
	// F+1...F+N-1 Loose
	// Let ai = k*F + x. Suppose Alice loose when 1 <= x <= N-1 while Alice win when N <= x <= F for k < K
	// We can prove the statement will also be true for k = K
	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		StringBuilder bf = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = ni();
			int F = N * (N - 1);
			boolean isAliceWin = false;
			for (int i = 0; i < N; i++) {
				int mi = ni() % F;
				if (mi == 0 || mi >= N) {
					isAliceWin = true;
				}
			}
			bf.append(isAliceWin ? "Alice\n" : "Bob\n");
		}
		System.out.print(bf);
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
}