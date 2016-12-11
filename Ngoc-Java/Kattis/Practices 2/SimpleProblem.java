import java.io.*;
import java.math.*;
import java.util.*;

public class SimpleProblem {
	static InputStream is = System.in;

	public static void main(String[] args) throws Exception {
		BigInteger[] factorials = new BigInteger[101];
		BigInteger factorial = BigInteger.valueOf(1);
		factorials[0] = factorial;
		for (int i = 1; i <= 100; i++) {
			factorials[i] = factorial = factorial.multiply(BigInteger.valueOf(i));
		}

		StringBuilder outBf = new StringBuilder();
		do {
			int nChar = 'z' - 'A' + 1;
			int[] flags = new int[nChar];
			char[] chars = ns().toCharArray();
			for (char c : chars) {
				flags[c - 'A']++;
			}

			BigInteger result = factorials[chars.length];
			for (int i = 0; i < nChar; i++) {
				if (flags[i] > 1) {
					result = result.divide(factorials[flags[i]]);
				}
			}
			outBf.append(result.toString() + "\n");
		} while (hasNext());
		System.out.print(outBf);
	}

	/* ****************************************************************
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

	static boolean hasNext() {
		return ptrbuf + 2 < lenbuf;
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b));
		return b;
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