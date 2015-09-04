import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class MTAMSAO1M_Improve {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	static int MAXVALUE;
	static int n;
	static int[] bit;
	static int[] persons;
	static boolean[] sexes;

	static void set(int i, int x) {
		while (i <= MAXVALUE) {
			bit[i] += x;
			i += (i & -i);
		}
	}

	static int get(int i) {
		int sum = 0;
		while (i > 0) {
			sum += bit[i];
			i -= (i & -i);
		}
		return sum;
	}

	static long compute(boolean sex) {
		int g1 = -1, g2 = -1;
		long result = 0;
		for (int i = 0; i < n; i++) {
			if (sexes[i] != sex) {
				int temp = g1;
				g1 = g2;
				g2 = i;
				for (temp++; temp < g1; temp++) {
					set(persons[temp], 1);
				}
			} else {
				result += get(persons[i]); // Still be OK because gs_items are distinct
				// result += get(persons[i] - 1); OK
			}
		}
		return result;
	}

	static void solve() {
		n = ni();
		MAXVALUE = n + 1;
		persons = new int[n];
		sexes = new boolean[n];
		bit = new int[MAXVALUE + 1];

		for (int i = 0; i < n; i++) {
			int sq = ni();
			if (sq > 0) {
				persons[i] = MAXVALUE - sq;
				sexes[i] = true;
			} else {
				persons[i] = MAXVALUE + sq;
			}
		}

		long resBoy = compute(true);
		Arrays.fill(bit, 0);
		long resGirl = compute(false);

		System.out.println(resBoy + " " + resGirl);
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1) throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0) return -1;
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
