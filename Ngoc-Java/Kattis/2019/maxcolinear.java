import java.io.*;
import java.util.*;

public class maxcolinear {
	static double EPS = 0.0000000001d;

	public static void main(String[] args) {
		int nPoint = reader.nextInt();
		while (nPoint > 0) {
			Point[] points = new Point[nPoint];
			for (int i = 0; i < points.length; i++) {
				points[i] = new Point(reader.nextInt(), reader.nextInt());
			}
			System.out.println(findMaxColinear(points));
			nPoint = reader.nextInt();
		}
	}

	static int findMaxColinear(Point[] points) {
		if (points.length == 1)
			return 1;
		int max = 2;
		double[] angles = new double[points.length];
		for (int i = 0; i < points.length - max; i++) {
			// double[] angles = new double[points.length - i - 1];
			for (int j = i + 1; j < points.length; j++) {
				angles[j - i - 1] = (points[j].x - points[i].x) == 0 ? Integer.MAX_VALUE
						: (double) (points[j].y - points[i].y) / (points[j].x - points[i].x);
			}
			Arrays.sort(angles, 0, points.length - i - 1);
			int colinear = 2;
			for (int k = 1; k < points.length - i - 1; k++) {
				if (Math.abs(angles[k] - angles[k - 1]) < EPS) {
					// if (Double.compare(angles[k], angles[k - 1]) == 0) {
					colinear++;
				} else {
					max = Math.max(colinear, max);
					colinear = 2;
				}
			}
			max = Math.max(colinear, max);
		}
		return max;
	}

	/*
	 * 7 -6 -6 -6 7 -6 -5 -6 -9 -6 9 -6 5 -6 -1 0
	 */

	static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
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

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
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

		long nextLong() {
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
}
