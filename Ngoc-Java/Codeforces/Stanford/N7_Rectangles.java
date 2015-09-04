import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class N7_Rectangles {
	static InputStream is;
	static PrintWriter out;
	// static String INPUT = "6 1 6 1 10 2 6 2 10 3 6 3 10";
	// static String INPUT = "4 3 5 3 7 4 5 4 7";
	static String INPUT = "10 3 5 3 7 4 5 4 7 1 6 1 10 2 6 2 10 3 6 3 10";

	public static void main(String[] args) throws Exception {
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @solution:
	 */
	static void solve() {
		int n = ni();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = ni();
			y[i] = ni();
		}

		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int dx = x[j] - x[i];
				int dy = y[j] - y[i];
				if (dx < 0) {
					dx = -dx;
					dy = -dy;
				} else if (dx == 0 && dy < 0) {
					dy = -dy;
				}
				long c = 0;
				if (dx == 0) {
					c = y[i] > y[j] ? y[j] : y[i];
				} else if (dy == 0) {
					c = x[i] > x[j] ? x[j] : x[i];
				} else if (x[i] < x[j]) {
					c = (long) x[i] * dx + (long) y[i] * dy;
				} else {
					c = (long) x[j] * dx + (long) y[j] * dy;
				}
				edges.add(new Edge(dx, dy, c));
			}
		}
		Collections.sort(edges);
		int size = edges.size();
		int count = 1;
		long sum = 0;
		for (int i = 1; i < size; i++) {
			if (edges.get(i).compareTo(edges.get(i - 1)) == 0) {
				sum += count;
				count++;
			} else {
				count = 1;
			}
		}
		System.out.println(sum / 2);
	}

	static int gcd(int x, int y) {
		while (x != 0 && y != 0) {
			if (x > y) {
				x %= y;
			} else {
				y %= x;
			}
		}
		return x + y;
	}

	static class Edge implements Comparable<Edge> {
		public int DX;
		public int DY;
		public long C;

		public Edge(int dx, int dy, long c) {
			DX = dx;
			DY = dy;
			C = c;
		}

		@Override
		public int compareTo(Edge arg0) {
			if (DX != arg0.DX) return DX - arg0.DX;
			if (DY != arg0.DY) return DY - arg0.DY;
			long dc = C - arg0.C;
			if (dc > 0l) return 1;
			else if (dc < 0l) return -1;
			return 0;
		}
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
