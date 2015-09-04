import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class AutocompleteStrikesBack {
	static InputStream is;
	static PrintWriter out;
	// static String INPUT = "5 6 4 tin tiny tinny gigantic tilt tilts 3 2 apple apricot cherry 5 3 a aa aaa aaaa aaaaa 5 3 the quick brown fox jumped 8 7 cork work card ward font front word sword ";
	static String INPUT = "5 6 4 tin tiny tinny gigantic tilt tilts 3 2 apple apricot cherry 5 3 a aa aaa aaaa aaaaa 5 3 the quick brown fox jumped 8 7 cork work card ward font front word sword ";

	public static void main(String[] args) throws Exception {
		oj = false;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static final int MAX = 4001;
	static final int N = 20001;
	static final int NCHAR = 'z' - 'a' + 1;
	static final int MAXLEN = 100;

	static int[][] dic = new int[N][NCHAR];
	static final int[][] nodeValues = new int[N][MAXLEN + 1];
	static int lastItem = 1;

	static void solve() {
		int T = ni();
		for (int t = 0; t < T; t++) {
			int result = 100000000;
			int N = ni();
			int K = ni();
			Arrays.fill(dic[0], -1);
			lastItem = 1;
			for (int i = 0; i < N; i++) {
				buildDictionary();
			}
			solveOne(0, K);
			// for (int i = 1; i <= NCHAR; i++) {
			// result = Math.min(result, nodeValues[0][i]);
			// }
			System.out.println("Case #" + (t + 1) + ": " + nodeValues[0][K]);
		}
	}

	static void buildDictionary() {
		int b = skip();
		int[] row = dic[0];
		while (!isSpaceChar(b)) {
			int cIndex = b - 'a';
			if (row[cIndex] == -1) {
				Arrays.fill(dic[lastItem], -1);
				row[cIndex] = lastItem;
				lastItem++;
			}
			row = dic[row[cIndex]];
			b = readByte();
		}
	}

	static void solveOne(int nodeIndex, int K) {
		int[] node = dic[nodeIndex];
		int[] curValues = nodeValues[nodeIndex];
		int nChild = 0;
		Arrays.fill(curValues, 0);
		for (int i = 0; i < NCHAR; i++) {
			int childIndex = node[i];
			if (childIndex > 0) {
				nChild++;
				solveOne(childIndex, K);
				int[] childValues = nodeValues[childIndex];
				int[] temp = new int[MAXLEN + 1];
				for (int j = 1; j <= MAXLEN; j++) {
					for (int k = 1; k <= MAXLEN - j; k++) {
						temp[j + k] = Math.min(curValues[j + k], curValues[i] + childValues[k]);
					}
				}
				for (int u = 1; u <= MAXLEN; u++) {
					curValues[u] = temp[u];
				}
			}
		}
		for (int u = 1; u <= nChild; u++) {
			curValues[u] += u;
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		// if (!oj) {
		System.out.println(Arrays.deepToString(o));
		// }
	}
}

// static void buildDictionary(String word) {
// int len = word.length();
// int[] row = dic[0];
// for (int i = 0; i < len; i++) {
// char c = word.charAt(i);
// int cIndex = c - 'a';
// if (row[cIndex] == -1) {
// Arrays.fill(dic[lastItem], -1);
// row[cIndex] = lastItem;
// lastItem++;
// }
// row = dic[row[cIndex]];
// }
// }
