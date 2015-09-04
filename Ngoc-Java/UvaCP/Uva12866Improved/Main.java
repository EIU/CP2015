import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);

		solve();
		out.flush();
	}

	static void solve() {

		initMapLong();
		StringBuilder buffer = new StringBuilder();
		while (true) {
			long low = nl();
			long high = nl();
			if (low == 0 && high == 0) {
				break;
			}
			// System.out.println(solveLong(high) - (low == 0 ? 0 : solveLong(low - 1)));
			BigInteger hValue = solveLong(high);
			BigInteger lValue = (low == 0 ? BigInteger.valueOf(-1) : solveLong(low - 1));
			hValue = hValue.subtract(lValue);
			buffer.append(hValue.toString() + "\n");
		}
		System.out.print(buffer.toString());
	}

	// Solution:
	// + SB(n) number of bit 1 of n.
	// + SO(n) = |{C(i,n) mode 2 = 1, 0<=i<=n}| = 2^SB(n)!
	// + GS(n) = Sum(SO(i), i=0..n)
	// => GS(2^(k+1)) = GS(2^k) + 2*GS(2^k) - 2!
	// => GS(2^ak + 2^a[k-1] + ...) = GS(2^ak) + 2*GS(2^a[k-1]) + 4*GS(2^a[k-2])...

	static long[] mapGSLong;
	static BigInteger mapBig = BigInteger.valueOf(0);

	static void initMapLong() {
		int level = 40;
		mapGSLong = new long[level];
		mapGSLong[0] = 2;
		for (int i = 1; i < level; i++) {
			mapGSLong[i] = 3 * mapGSLong[i - 1] - 2;
		}
		mapBig = BigInteger.valueOf(mapGSLong[39]).multiply(BigInteger.valueOf(3)).subtract(BigInteger.valueOf(2));
	}

	static BigInteger solveLong(long n) {
		int count = 0;
		long sum = 0;
		BigInteger bigSum = BigInteger.valueOf(0);
		long flag = 0x10000000000l; // 16 * 10^11 = 2^40 * 1.5
		for (int i = 40; i >= 0; i--) {
			if ((n & flag) != 0) {
				if (i == 40) {
					bigSum = mapBig;
				}
				else if (i > 35) {
					bigSum = bigSum.add(BigInteger.valueOf(mapGSLong[i]).multiply(BigInteger.valueOf(1 << count)));
				}
				else {
					sum += mapGSLong[i] << count;
				}
				count++;
			}

			flag >>= 1;
		}

		return bigSum.add(BigInteger.valueOf(sum));
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
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

	static long nl() {
		long num = 0;
		int b;
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
