import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrimeSieve_Fast3 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		final byte[] flags = {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, -0x80};

		int n = ni();
		int q = ni();
		byte[] primes = new byte[(n >> 4) + 2];
		primes[0] |= 1;
		int sqrtN = (int) Math.sqrt(n) >> 1;
		int n2 = (n >> 1);
		for (int i = 1, i2 = 2; i <= sqrtN; i += 1, i2 += 2) {
			if ((primes[i >> 3] & flags[i & 7]) == 0) {
				int i21 = i2 + 1;
				for (int j = i2 * i + i2; j <= n2; j += i21) {
					primes[j >> 3] |= flags[j & 7];
				}
			}
		}

		int count = (q >= 2) ? 1 : 0;
		for (int i = 1; i <= n2; i += 1) {
			if ((primes[i >> 3] & flags[i & 7]) == 0) {
				count++;
			}
		}

		String[] result = new String[q];
		for (int j = 0; j < q; j++) {
			int i = ni();
			if ((i & 1) == 0) {
				if (i == 2) {
					result[j] = "1";
				} else {
					result[j] = "0";
				}
			} else {
				int k = (i - 1) >> 1;
				if ((primes[k >> 3] & flags[k & 7]) == 0) {
					result[j] = "1";
				} else {
					result[j] = "0";
				}
			}
		}

		System.out.println(count + "\n" + String.join("\n", result));
	}
	/*****************************************************************
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
