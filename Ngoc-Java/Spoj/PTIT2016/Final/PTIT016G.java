import java.io.*;
import java.util.*;

class PTIT016G {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int k = ni();
		int M = ni();

		for (int i = 0; i < k; i++) {
			n -= ni();
		}

		if (n < 0) {
			System.out.println(0);
			return;
		}

		// Result = C(k-1, n+k-1) % M. M is any number in range 10^9

		k--;
		if (n < k) {
			int temp = k;
			k = n;
			n = temp;
		}

		int limit = n + k;
		boolean[] primes = new boolean[limit + 1];
		Arrays.fill(primes, true);
		for (int i = 2; i * i <= limit; i++) {
			if (primes[i]) {
				for (int j = i * i; j <= limit; j += i) {
					primes[j] = false;
				}
			}
		}

		int[] countPrimes = new int[limit + 1];
		for (int i = 2; i <= k; i++) {
			if (primes[i]) {
				for (long j = i; j <= k; j *= i) {
					countPrimes[i] -= k / j;
				}
			}
		}

		for (int i = 2; i <= limit; i++) {
			if (primes[i]) {
				for (long j = i; j <= limit; j *= i) {
					countPrimes[i] += limit / j - n / j;
				}
			}
		}

		long result = 1;
		for (int i = 2; i <= limit; i++) {
			if (countPrimes[i] > 0) {
				result = result * powMod(i, countPrimes[i], M) % M;
			}
		}
		System.out.println(result);
	}

	static int powMod(long a, int n, int M) {
		long result = 1;
		while (n > 0) {
			if ((n & 1) != 0) {
				result = result * a % M;
			}
			a = a * a % M;
			n >>= 1;
		}
		return (int) result;
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
