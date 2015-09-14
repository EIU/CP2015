import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Mosquitoes {	
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int n = ni();
		double r = nd() / 2;
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = new Point(nd(), nd());
		}

		int max = 1;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				Point[] centers = findCircleCenter(points[i], points[j], r);

				if (centers != null) {
					max = Math.max(max, countPointInside(centers[0], r, points));
					max = Math.max(max, countPointInside(centers[1], r, points));
				}
			}
		}
		System.out.println(max);
	}

	static double esp = 10e-9;

	static public int countPointInside(Point center, double r, Point[] points) {
		int count = 0;
		r = r * r;
		for (Point p : points) {
			double len2 = (center.x - p.x) * (center.x - p.x) + (center.y - p.y) * (center.y - p.y);
			if (len2 < r + esp) {
				count++;
			}
		}
		return count;
	}

	static public Point[] findCircleCenter(Point p1, Point p2, double r) {
		double midX = (p1.x + p2.x) / 2;
		double midY = (p1.y + p2.y) / 2;
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;
		double len = Math.sqrt(dx * dx + dy * dy);

		if (len / 2 > r + esp) {
			return null;
		}

		double height = Math.sqrt(r * r - len * len / 4);
		double dMidX = height * (p1.y - p2.y) / len;
		double dMidY = height * (p2.x - p1.x) / len;

		return new Point[]{new Point(midX + dMidX, midY + dMidY), new Point(midX - dMidX, midY - dMidY)};
	}

	static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
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
