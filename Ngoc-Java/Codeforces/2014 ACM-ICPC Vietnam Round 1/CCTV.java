import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class CCTV {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static int X;
	static int Y;
	static Point[][] rects;

	static void solve() {
		X = ni();
		Y = ni();

		int N = ni();
		rects = new Point[N + 4][];
		for (int i = 0; i < N; i++) {
			int l = ni();
			int t = ni();
			int r = ni();
			int b = ni();
			if (l > r) {
				int temp = l;
				l = r;
				r = temp;
			}
			if (t > b) {
				int temp = t;
				t = b;
				b = temp;
			}
			rects[i] = new Point[]{new Point(ni(), ni()), new Point(ni(), ni())};
		}

		rects[N] = new Point[]{new Point(0, 0), new Point(X, 0)};
		rects[N + 1] = new Point[]{new Point(X, 0), new Point(X, Y)};
		rects[N + 2] = new Point[]{new Point(X, Y), new Point(0, Y)};
		rects[N + 3] = new Point[]{new Point(0, Y), new Point(0, 0)};

		System.out.print(calculate() + " ");
		rotate();
		System.out.println(calculate());

		rotate();
		System.out.print(calculate() + " ");
		rotate();
		System.out.println(calculate());

	}
	static double calculate() {

		Vector[] vectors = new Vector[rects.length * 6];
		
		for (int i = 0, j = 0; i < rects.length; i++, j += 6) {
			Point[] rect = rects[i];

			Point[] inters = getIntersec(new Point(rect[1].x, rect[0].y));
			vectors[j] = new Vector(inters[0].x, inters[0].y, -1);
			vectors[j + 1] = new Vector(inters[0].x, inters[0].y, 1);

			inters = getIntersec(rect[0]);
			vectors[j] = new Vector(inters[0].x, inters[0].y, -1);
			vectors[j + 1] = new Vector(inters[0].x, inters[0].y, 1);

			inters = getIntersec(new Point(rect[0].x, rect[1].y));
			vectors[j] = new Vector(inters[0].x, inters[0].y, -1);
			vectors[j + 1] = new Vector(inters[0].x, inters[0].y, 1);
		}

		Arrays.sort(vectors);

		double area = 0;
		Vector up = null;
		for (Vector v : vectors) {
			if (v.dir > 0) {
				up = v;
				continue;
			} else {
				if (up != null) {
					area += getArea(Point.P0, new Point(v.x, v.y), new Point(up.x, up.y));
				}
				up = null;
			}
		}

		return area;
	}

	static Point[] getIntersec(Point p) {
		Point[] result = new Point[2];

		for (Point[] rect : rects) {
			
		}

		return result;
	}
	
	static void rotate() {
		for (Point[] rect : rects) {
			rotate_0_0_0_Y(rect[0]);
			rotate_0_0_0_Y(rect[1]);
		}
		int temp = X;
		X = Y;
		Y = temp;
	}

	static double getArea(Point A, Point B, Point C) {
		double a = getLength(B, C);
		double b = getLength(C, A);
		double c = getLength(A, B);
		double p = (a + b + c) / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	static double getLength(Point A, Point B) {
		return Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}

	static void rotate_0_0_0_Y(Point point) {
		double x = Y - point.y;
		point.y = point.x;
		point.x = x;
	}

	static class Vector implements Comparable<Vector> {
		public double x;
		public double y;
		public double angle;
		public int dir;

		public Vector(double x, double y, int dir) {
			this.x = x;
			this.y = y;
			this.angle = y / x;
			this.dir = dir;
		}

		@Override
		public int compareTo(Vector arg0) {
			int cmp = Double.compare(angle, arg0.angle);
			if (cmp != 0) {
				return dir - arg0.dir;
			}
			return cmp;
		}

	}

	static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		static Point P0 = new Point(0, 0);
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
