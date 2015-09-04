import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);

		while (lenbuf == 0 || ptrbuf < lenbuf - 1) {
			int a = ni(); // 1..100K
			int b = ni(); // 1..100K
			double eps = nd(); // 1/10...10/100K

			int _gcd = gcd(a, b);
			a /= _gcd;
			b /= _gcd;

			int n = a / b;
			a = a % b; // => a < b

			// Suppose c/d = (ak + x)/(bk + y) => (bx - ay)/(bk + y)/b <= eps
			// f(a,b) = bx - ay as small as possible => bk + y as small as possible
			// x < a, y < b => need O(a,b) to find min f(a,b)
			// bk + y >= (bx - ay)/b/d => k >= (E - y)/b

			int x = 1, y = 0, bx = b, ay = 0;
			int c = 1, d = 1000000000;
			while (x < a || y < b) {
				y++;
				ay += a;
				if (bx < ay) {
					x++;
					bx += b;
				}
				int k = (int) Math.ceil(((double) (bx - ay) / b / eps - y) / b);
				int bk = b * k;
				if ((double) (bx - ay) / (bk + y) / b <= eps) {
					int d1 = bk + y;
					int c1 = a * k + x;
					int _gcd1 = gcd(c1, d1);
					d1 /= _gcd1;
					c1 /= _gcd1;
					if (d1 < d) {
						c = c1;
						d = d1;
					}
				}
			}

			out.printf("%d %d%n", c + n * d, d);
		}

		out.flush();
	}

	public static int gcd(int a, int b) {
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		while (a > 0) {
			int temp = a;
			a = b % a;
			b = temp;
		}
		return b;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096 * 100];
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

// Solution
// Note: 0 <= n < k < SPC
// standardTime = time1 * SPD/(SPD - n)
// standardTime = time2 * SPD/(SPD - k)
// => time2 = time1 * (SPD - k)/(SPD - n)
// firstOverlapse: time1 - time2 = SPC
// => time1 = SPC / (1 - rate)