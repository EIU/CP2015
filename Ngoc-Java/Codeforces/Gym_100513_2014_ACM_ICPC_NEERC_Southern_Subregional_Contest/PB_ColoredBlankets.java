import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PB_ColoredBlankets {
	static InputStream is;
	static PrintWriter out;
	// static String INPUT = "6 2 1 1 2 2 -1 2";
	// static String INPUT = "8 4 4 -1 1 -1 4 3 -1 -1";
	static String INPUT = "9 3 1 1 1 1 2 2 2 2 2";

	public static void main(String[] args) throws Exception {
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @solution: count(i): number of blanket has color i. Uncolored blanket will be colored 1.
	 * @+ If there is one color A: count(A) < k/n. Then there must have color B: count(B) > k/n. We create a kit of count(A) color A and k/n - count(A) color B
	 * @+ So now: Problem is decreased to (n-1) color and k - (k/n) blankets
	 * @+ Find A: count(A) is minimum. if(count(A) % (k/n) != 0) => create count(A)/(k/n) kits with color A. Then repeat step 1
	 */
	static void solve() {
		int k = ni();
		int n = ni();

		List<List<Integer>> colorToBlanket = new ArrayList<List<Integer>>();
		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> colorBlanket = new ArrayList<Integer>();
			colorBlanket.add(i);
			colorToBlanket.add(colorBlanket);
		}
		for (int i = 1; i <= k; i++) {
			// Uncolored blanket will be colored 1
			int color = Math.max(ni(), 1);
			colorToBlanket.get(color).add(i);
		}

		int numberInKit = k / n;
		int lower = 0, upper = n;
		while (lower < upper) {
			while (lower < upper && colorToBlanket.get(lower).size() - 1 <= numberInKit) {
				lower++;
			}
			while (upper > lower && colorToBlanket.get(upper).size() - 1 > numberInKit) {
				upper--;
			}
			if (lower < upper) {
				List<Integer> temp = colorToBlanket.get(lower);
				colorToBlanket.set(lower, colorToBlanket.get(upper));
				colorToBlanket.set(upper, temp);
			}
		}

		while (upper <= n && colorToBlanket.get(upper).size() - 1 <= numberInKit) {
			upper++;
		}

		int[][] blanketColors = new int[k + 1][2];
		for (int i = 1; i <= n; i++) {
			List<Integer> colorBlankets = colorToBlanket.get(i);
			int color = colorBlankets.get(0);
			upper = Math.max(i + 1, upper);
			int countColor = colorBlankets.size() - 1;
			if (countColor != 0) {
				int upperColor = upper <= n ? colorToBlanket.get(upper).get(0) : 1;
				int totalInSet = (countColor + numberInKit - 1) / numberInKit * numberInKit;
				for (int j = 1; j <= countColor; j++) {
					blanketColors[colorBlankets.get(j)] = new int[]{color, upperColor};
				}
				if (countColor < totalInSet) {
					int need = totalInSet - countColor;
					List<Integer> upperColorBlankets = colorToBlanket.get(upper);
					int countUpper = upperColorBlankets.size() - 1;

					for (int j = countUpper - need + 1; j <= countUpper; j++) {
						blanketColors[upperColorBlankets.get(j)] = new int[]{upperColor, color};
					}
					upperColorBlankets = upperColorBlankets.subList(0, countUpper - need + 1);
					colorToBlanket.set(upper, upperColorBlankets);
					if (upperColorBlankets.size() - 1 <= numberInKit) {
						upper++;
					}
				}
			}
		}
		StringBuffer outString = new StringBuffer();
		for (int i = 1; i <= k; i++) {
			outString.append(blanketColors[i][0] + " " + blanketColors[i][1] + "\n");
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
