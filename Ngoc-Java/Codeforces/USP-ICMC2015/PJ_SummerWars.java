import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PJ_SummerWars {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "3 4 2 0 2 4 4 7 1 0 2 3 1 3 5 0 2 5 3 4";

	public static void main(String[] args) throws Exception {
		oj = true;
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
		int m = ni();
		int[] pYs = new int[n];
		int[] pXs = new int[n];

		for (int i = 0; i < n; i++) {
			pYs[i] = ni();
			pXs[i] = ni();
		}

		int m2 = 2 * m;
		int[] wallYs = new int[m];
		int[][] wallPoints = new int[m2][2];
		for (int i = 0; i < m2; i += 2) {
			int y = ni();
			wallYs[i >> 1] = y;
			wallPoints[i][0] = ni();
			wallPoints[i][1] = y;
			wallPoints[i + 1][0] = ni() + 1;
			wallPoints[i + 1][1] = y;
		}

		Arrays.sort(wallYs);
		int nY = 1;
		for (int i = 1; i < m; i++) {
			if (wallYs[i] != wallYs[i - 1]) {
				wallYs[nY++] = wallYs[i];
			}
		}

		Arrays.sort(wallPoints, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});

		Segment[] segments = new Segment[m2];
		int nSegment = 0;

		boolean[] slides = new boolean[nY];
		for (int i = 0; i < m2; i++) {
			int[] point = wallPoints[i];
			int y = point[1];
			int p = Arrays.binarySearch(wallYs, 0, nY, y);
			slides[p] = !slides[p];

			if (i < m2 - 1 && point[0] == wallPoints[i + 1][0]) {
				continue;
			}

			int upper = 0;
			while (upper < nY && !slides[upper]) {
				upper++;
			}
			if (upper < nY) {
				int lower = nY - 1;
				while (lower > upper && !slides[lower]) {
					lower--;
				}
				if (lower > upper && i < m2 - 1) {
					segments[nSegment++] = new Segment(wallYs[upper], wallYs[lower], point[0], wallPoints[i + 1][0] - 1);
				}
			}
		}

		int BASE = 1000 * 1000 + 10;
		int BOUND = 3 * BASE + 10;
		int[] values = new int[BOUND];
		for (int i = 0; i < n; i++) {
			int x = pXs[i];
			int y = pYs[i];
			for (int j = 0; j < nSegment; j++) {
				Segment sj = segments[j];
				if (sj.lower >= y && y >= sj.upper) {
					values[pXs[i] - sj.right + BASE]++;
					values[pXs[i] - sj.left + 1 + BASE]--;
				}
			}
		}
		for (int i = 1; i < BOUND; i++) {
			values[i] += values[i - 1];
		}

		int result = 0;
		for (int i = 0; i < BOUND; i++) {
			if (values[i] > result) {
				result = values[i];
			}
		}
		System.out.println(result);
	}

	static class Segment {
		public int upper;
		public int lower;
		public int left;
		public int right;
		public Segment(int upper, int lower, int left, int right) {
			this.upper = upper;
			this.lower = lower;
			this.left = left;
			this.right = right;
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
