import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PB_ColoredBlankets_Unordered {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "6 2 1 1 2 2 -1 2";
	// static String INPUT = "8 4 4 -1 1 -1 4 3 -1 -1";

	public static void main(String[] args) throws Exception {
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @solution: count(i): number of blanket has color i. Uncolored blanket
	 *            will be colored 1. There must be at least one color A:
	 *            count(A) < k/n.
	 * @+ Then there must have color B: count(B) > k/n. We create a kit of
	 *    count(A) color A and count(B) - count(A) color B
	 * @+ So now: Problem is decreased to (n-1) color and k - (k/n) blankets
	 */
	static void solve() {
		int k = ni();
		int n = ni();

		int[] countColors = new int[n + 1];
		for (int i = 0; i < k; i++) {
			// Uncolored blanket will be colored 1
			countColors[Math.max(ni(), 1)]++;
		}

		int numberInKit = k / n;
		Integer[] colorPositions = new Integer[n + 1];
		int lower = -1, upper = n + 1;
		for (int i = 0; i <= n; i++) {
			int count = countColors[i];
			if (count <= numberInKit) {
				colorPositions[++lower] = i;
			} else {
				colorPositions[--upper] = i;
			}
		}

		StringBuilder outString = new StringBuilder();
		for (int i = 0; i <= n; i++) {
			int color = colorPositions[i];
			int countColor = countColors[color];
			if (countColor != 0) {
				int upperColor = upper <= n ? colorPositions[upper] : 1;
				for (int j = 0; j < Math.max(numberInKit, countColor); j++) {
					outString.append(color + " " + upperColor + "\n");
				}
				if (countColor < numberInKit) {
					countColors[upperColor] -= countColor;
					if (countColors[upperColor] <= numberInKit) {
						upper++;
					}
				}
			}
		}
		System.out.println("Yes");
		System.out.println(outString.toString());
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
