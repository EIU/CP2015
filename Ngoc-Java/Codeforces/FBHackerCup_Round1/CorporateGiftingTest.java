import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class CorporateGiftingTest {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

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
	static final int F = 0, L = 1, N = 2, C1 = 3, C1V = 4, C2 = 5, C2V = 6;
	// First, Last, Next
	static int[][] nodes = new int[MAX][7];
	static int[] minMap = new int[MAXLEVEL];

	static void solve() {
		int T = 2;
		for (int t = 0; t < T; t++) {
			int n = 200000;
			nodes[0][F] = nodes[0][L] = nodes[0][N] = -1;
			buildTree(n);
			solveOneNode(nodes[1]);
			System.out.println("Case #" + (t + 1) + ": " + nodes[1][C1V]);
		}
	}

	static void buildTree(int n) {

		nodes[1][F] = nodes[1][L] = nodes[1][N] = 0;
		for (int i = 2; i <= n; i++) {
			int p = i - 1;
			int[] pNode = nodes[p];
			int[] node = nodes[i];
			node[F] = node[L] = node[N] = 0;
			if (pNode[F] == 0) {
				pNode[F] = pNode[L] = i;
			}
			else {
				pNode[L] = nodes[pNode[L]][N] = i;
			}
		}
	}

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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
