import java.io.*;
import java.util.*;

public class CrazyDriver {

	static InputStream is = System.in;

	public static void main(String[] args) throws Exception {
		int N = ni();

		long[] C = new long[N - 1];
		for (int i = 0; i < N - 1; i++) {
			C[i] = ni();
		}

		long[] T = new long[N];
		for (int i = 0; i < N; i++) {
			T[i] = ni();
		}

		long t = 0;
		long cost = 0;
		long minC = C[0];
		for (int i = 1; i < N; i++) {
			minC = Math.min(minC, C[i - 1]);
			if (t + 1 >= T[i]) {
				cost += C[i - 1];
				t++;
			} else {
				long x = T[i] - t;
				x = x / 2 * 2;
				cost += x * minC + C[i - 1];
				t += x + 1;
			}
		}
		System.out.println(cost);
	}

	/******************************************************************/

	static byte[] inbuf = new byte[1 << 16];
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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