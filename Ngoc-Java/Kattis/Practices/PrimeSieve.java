import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrimeSieve {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		int n = ni();
		int q = ni();
		byte[] primes = new byte[(n >> 3) + 1];
		primes[0] |= 3;
		int n2 = (int) Math.sqrt(n);
		for (int i = 2; i <= n2; i++) {
			if ((primes[i >> 3] & (1 << (i & 7))) == 0) {
				for (int j = i * i; j <= n; j += i) {
					primes[j >> 3] |= (1 << (j & 7));
				}
			}
		}
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if ((primes[i >> 3] & (1 << (i & 7))) == 0) {
				count++;
			}
		}
		StringBuilder bf = new StringBuilder();
		bf.append(count + "\n");
		for (int j = 0; j < q; j++) {
			int i = ni();
			if ((primes[i >> 3] & (1 << (i & 7))) == 0) {
				bf.append("1\n");
			} else {
				bf.append("0\n");
			}
		}

		System.out.println(bf.toString());
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
