import java.io.*;
import java.util.*;

class PTIT016A_2 {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		long A = ni();
		long B = ni();

		long AB = A > B ? (A - B) : (B - A);

		long lcm = (A + 1) * (B + 1) / gcd(A + 1, B + 1);
		long minN = 1;
		long temp;
		long limit = (long) Math.sqrt(AB);
		for (long div1 = 1; div1 <= limit; div1++) {
			if (AB % div1 == 0) {
				if (lcm > (temp = findLCM(A, B, div1))) {
					minN = N;
					lcm = temp;
				}
				if (lcm > (temp = findLCM(A, B, AB / div1))) {
					minN = N;
					lcm = temp;
				}
			}
		}

		System.out.println(minN);
	}

	static long N;
	static long findLCM(long A, long B, long div) {
		N = div - A % div;
		return (A + N) * (B + N) / gcd(A + N, B + N);
	}

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
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
