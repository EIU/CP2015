import java.io.*;
import java.util.*;

class EIENCRYPT {
	static InputStream is;

	static int nPrime = 0;
	static int[] primes;

	static void generatePrimes()
	{
		int MAXPRIMES = 1000000 + 1;
		int sqrt = (int) Math.ceil(Math.sqrt(MAXPRIMES));

		boolean[] primeflags = new boolean[MAXPRIMES];
		Arrays.fill(primeflags, true);
		for (int i = 2; i <= sqrt; i++) {
			if (primeflags[i]) {
				for (int j = i * i; j < MAXPRIMES; j += i) {
					primeflags[j] = false;
				}
			}
		}

		for (int i = 2; i < MAXPRIMES; i++) {
			if (primeflags[i]) {
				nPrime++;
			}
		}

		primes = new int[nPrime];
		int j = 0;
		for (int i = 2; i < MAXPRIMES; i++) {
			if (primeflags[i]) {
				primes[j++] = i;
			}
		}
	}

	public static void main(String[] args) {
		is = System.in;

		generatePrimes();

		// primes = new int[]{100043};

		String numbers;
		int maxDigit = 18;
		long baseNumber = 1000000L * 1000000L * 1000000L;
		long[] powList = new long[maxDigit];
		powList[0] = 1;
		for (int i = 1; i < maxDigit; i++) {
			powList[i] = powList[i - 1] * 10;
		}
		StringBuffer outBf = new StringBuffer();

		while ((numbers = ns()).compareTo("0") != 0) {
			int L = ni();
			int badPrime = -1;
			for (int p : primes) {
				if (p < L) {
					int len = numbers.length();
					long baseMod = baseNumber % p;
					long mod = 0;
					for (int i = 0; i < len;) {
						long subNumber = 0;
						if (i + maxDigit >= len) {
							baseMod = powList[len - i] % p;
						}
						for (int j = 0; j < maxDigit && i < len; j++, i++) {
							subNumber = subNumber * 10 + (numbers.charAt(i) - '0');
						}
						mod = (mod * baseMod + subNumber) % p;
					}
					if (mod % p == 0) {
						badPrime = p;
						break;
					}
				}
			}
			if (badPrime > 0) {
				outBf.append("BAD " + badPrime + "\n");
			}
			else {
				outBf.append("GOOD\n");
			}
		}
		System.out.println(outBf);
	}

	/* *
	 */

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

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
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

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
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
