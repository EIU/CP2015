import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PK_Treeland {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "3 2 1 2 2 1 5 1 4 5 3 2 2 3 4 1 5 3 4 2 5 1 4 3 1 5 2 5 4 3 1 2 3 1 3 2 2 1 3 3 1 2";

	public static void main(String[] args) throws Exception {
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

	/**
	 * @solution:
	 * @+ Initial: the farthest vertex must be leaf. It must have only one edge
	 *    to its parent => we can easily find its parent
	 * @+ In general: the farthest vertex (A) which has not been examined must
	 *    have exactly on edge to a vertex (B) which has not been examined. B
	 *    will be parent of A, A is examined!
	 */
	static void solve() {
		int n = ni();
		int[] parents = new int[n + 1];
		int[][] matrix = new int[n + 1][n];
		for (int i = 1; i <= n; i++) {
			ni();
			for (int j = 1; j < n; j++) {
				matrix[i][j] = ni();
			}
		}

		for (int j = n - 1; j >= 1; j--) {
			for (int i = 1; i <= n; i++) {
				int newVertex = matrix[i][j];
				if (parents[newVertex] == 0) {
					for (int k = 1; k < n; k++) {
						int nextVertex = matrix[newVertex][k];
						if (parents[nextVertex] == 0) {
							parents[newVertex] = nextVertex;
							break;
						}
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			if (parents[i] != 0) {
				System.out.println(i + " " + parents[i]);
			}
		}
		System.out.println();
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
