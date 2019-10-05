import java.io.*;
import java.util.*;

public class maxcolinear2 {

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

		Line[] lines = new Line[points.length * (points.length - 1) / 2];
		int k = 0;
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				lines[k++] = new Line(points[i], points[j]);
			}
		}
		Arrays.sort(lines);
		int max = 2;
		int combine = 1;
		for (int i = 1; i < lines.length; i++) {
			if (lines[i].compareTo(lines[i - 1]) == 0) {
				combine++;
			} else {
				// x * (x-1)/2 = combine => x^2 - x = 2*combine
				max = Math.max(max, (int) Math.ceil(Math.sqrt(2 * combine)));
				combine = 1;
			}
		}
		max = Math.max(max, (int) Math.ceil(Math.sqrt(2 * combine)));
		return max;
	}

	static class Line implements Comparable<Line> {
		public double angle;
		public double b;

		public Line(Point p1, Point p2) {
			if (p2.x == p1.x) {
				angle = Integer.MAX_VALUE;
				b = p2.x;
			} else {
				angle = (double) (p2.y - p1.y) / (p2.x - p1.x);
				b = p1.y - p1.x * angle;
			}
			// y = angle * x + b
			// b = p1.y - p1.x * (p2.y-p1.y)/(p2.x - p1.x)
			// y = (p2.y-p1.y)/(p2.x-p1.x)*x + p1.y - p1.x*(p2.y-p1.y)/(p2.x-p1.x)
		}

		@Override
		public int compareTo(Line line) {
			int cmp = 0;
			if (Math.abs(angle - line.angle) < EPS) {
				if (Math.abs(b - line.b) < EPS) {
					cmp = 0;
				} else {
					cmp = Double.compare(b, line.b);
				}
			} else {
				cmp = Double.compare(angle, line.angle);
			}
			return cmp;
		}

		@Override
		public String toString() {
			return angle + " " + b;
		}
	}

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
