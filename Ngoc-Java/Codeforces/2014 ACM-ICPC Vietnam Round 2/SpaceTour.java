import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class SpaceTour {
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

	static final int MAX = 1000;
	static int m;
	static int n;
	static char[][] land;
	static short[][][] directions;
	static int br = 0;
	static int tl = 1;
	static int tr = 2;
	static int bl = 3;

	static int[][] vectors = new int[][] { { 1, 1, 0, -1 }, { -1, -1, 0, 1 }, { -1, 1, 1, 0 }, { 1, -1, -1, 0 } };

	static void solve() {
		m = ni();
		n = ni();
		directions = new short[4][m][n];
		land = new char[m][];
		for (int i = 0; i < m; i++) {
			land[i] = ns().toCharArray();
		}

		for (int i = 0; i < m; i++) {
			visit(i, 0, new int[] { tr, br });
			visit(i, n - 1, new int[] { tl, bl });
		}

		for (int j = 0; j < n; j++) {
			visit(0, j, new int[] { bl, br });
			visit(m - 1, j, new int[] { tl, tr });
		}

		int max = 0;
		for (int x = 0; x < m; x++) {
			for (int y = 0; y < n; y++) {
				int value = directions[0][x][y] + directions[1][x][y] + directions[2][x][y] + directions[3][x][y] - 3;
				if (value >= max) {
					max = value;
				}
			}
		}
		System.out.println(max);
	}

	static void visit(int i, int j, int[] dirs) {

		for (int dir : dirs) {
			int[] vector = vectors[dir];
			short[][] direction = directions[dir];

			int x = i, y = j;
			int dx = vector[0], dy = vector[1];
			int predx = vector[2], predy = vector[3];
			short value = 0;
			int prex, prey;
			char pre;

			if (dir != 0 && dir != 3) {
				// continue;
			}

			while (0 <= x && x < m && 0 <= y && y < n) {
				prex = x + predx;
				prey = y + predy;
				pre = (0 <= prex && prex < m && 0 <= prey && prey < n) ? land[prex][prey] : '0';
				if (pre == '1') {
					value += 1;
				}
				else {
					value = 0;
				}
				if (land[x][y] == '1') {
					value += 1;
				}
				else {
					value = 0;
				}
				direction[x][y] = value;
				x += dx;
				y += dy;
			}
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
