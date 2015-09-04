import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class CorporateGifting {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	/**
	 * @If StackOverflowError happen => should run by command line: java -Xss64M CorporateGifting < corporate_gifting.txt
	 */
	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static final int MAX = 200000 + 2;
	static final int MAXLEVEL = 22;
	static final int F = 0, L = 1, N = 2, C1 = 3, C1V = 4, C2 = 5, C2V = 6, FL = 7;
	// First, Last, Next
	static int[][] nodes = new int[MAX][8];
	static int[] minMap = new int[MAXLEVEL + 1];

	static void solve() {
		int T = ni();
		for (int t = 0; t < T; t++) {
			int n = ni();
			nodes[0][F] = nodes[0][L] = nodes[0][N] = -1;
			buildTree(n, t + 1);
			solveOneNode(nodes[1]);
			System.out.println("Case #" + (t + 1) + ": " + nodes[1][C1V]);
		}
	}

	/**
	 * @Description: Each node reference to firstChild, lastChild, and nexSibling
	 */
	static void buildTree(int n, int flag) {
		ni();
		nodes[1][F] = nodes[1][L] = nodes[1][N] = 0;
		nodes[1][FL] = flag;
		for (int i = 2; i <= n; i++) {
			int p = ni();
			int[] pNode = nodes[p];
			int[] node = nodes[i];
			if (pNode[FL] != flag) {
				pNode[F] = pNode[L] = pNode[N] = 0;
				pNode[FL] = flag;
			}
			if (node[FL] != flag) {
				node[F] = node[L] = node[N] = 0;
				node[FL] = flag;
			}
			if (pNode[F] == 0) {
				pNode[F] = pNode[L] = i;
			}
			else {
				pNode[L] = nodes[pNode[L]][N] = i;
			}
		}
	}

	/**
	 * @Define: min(node, c): minimum of its descendants when node has value c
	 * @Solution: Consider 1st level nodes (direct child of root).
	 * @Solution: For each node, we only need to consider two best value: min(node, c1), min(node, c2). Prefer c1, c2 as small as possible
	 * @Solution: => then we will find two best value for root
	 * @Solution: Continue to ith level
	 * @Assume: |{c1, c2: all child node}| <= K ~ log(n)? It will not so large. We well define after test with large testcases
	 */
	static void solveOneNode(int[] node) {
		if (node[F] == 0) {
			node[C1] = node[C1V] = 1;
			node[C2] = node[C2V] = 2;
			return;
		}

		int[] nextNode = nodes[node[F]];
		while (nextNode[N] >= 0) {
			solveOneNode(nextNode);
			nextNode = nodes[nextNode[N]];
		}

		nextNode = nodes[node[F]];
		int sumMin = 0;
		Arrays.fill(minMap, 0);
		int largest = 2;
		while (nextNode[N] >= 0) {
			sumMin += nextNode[C1V];
			minMap[nextNode[C1]] += (-nextNode[C1V] + nextNode[C2V]);
			largest = Math.max(Math.max(nextNode[C1], nextNode[C2]), largest);
			nextNode = nodes[nextNode[N]];
		}

		int c1 = 1, c1v = Integer.MAX_VALUE, c2 = 2, c2v = Integer.MAX_VALUE;
		for (int i = 1; i <= largest + 1; i++) {
			int value = sumMin + minMap[i] + i;
			if (value < c1v) {
				c2 = c1;
				c2v = c1v;
				c1 = i;
				c1v = value;
			}
			else if (value < c2v) {
				c2 = i;
				c2v = value;
			}
		}
		node[C1] = c1;
		node[C1V] = c1v;
		node[C2] = c2;
		node[C2V] = c2v;
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
