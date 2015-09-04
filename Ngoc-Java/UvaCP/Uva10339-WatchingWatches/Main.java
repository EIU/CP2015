import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
	static InputStream is;
	static PrintWriter out;

	final static int SPD = 3600 * 24; // Seconds per Day
	final static int SPC = 3600 * 12; // Seconds per Cycle

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);

		while (lenbuf == 0 || ptrbuf < lenbuf - 1) {
			int p1 = ni();
			int p2 = ni();

			int n = Math.min(p1, p2);
			int k = Math.max(p1, p2);

			long time1 = (long) SPC * (SPD - k) / (k - n);

			time1 %= SPC;
			long hour = time1 / 3600;
			time1 %= 3600;
			long minute = (time1 + 30) / 60;

			if (minute == 60) {
				minute = 0;
				hour++;
			}
			if (hour == 0) {
				hour = 12;
			}

			out.printf("%d %d %02d:%02d%n", p1, p2, hour, minute);
		}

		out.flush();
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