import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Inventing {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int N = ni();

		int[] roots = new int[N + 1];
		int[] sizes = new int[N + 1];
		Arrays.fill(sizes, 1);
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}

		int[][] edges = new int[N - 1][3];
		for (int i = 0; i < N - 1; i++) {
			int[] edge = edges[i];
			edge[0] = ni();
			edge[1] = ni();
			edge[2] = ni();
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[2] - arg1[2];
			}
		});

		long result = 0;
		for (int i = 0; i < N - 1; i++) {
			int[] edge = edges[i];
			int root1 = getRoot(roots, edge[0]);
			int root2 = getRoot(roots, edge[1]);
			long w = edge[2];
			if (sizes[root1] > sizes[root2]) {
				int temp = root1;
				root1 = root2;
				root2 = temp;
			}
			result += sizes[root1] * sizes[root2] * (w + 1) - 1;
			roots[root1] = root2;
			sizes[root2] += sizes[root1];
		}
		System.out.println(result);
	}

	static int getRoot(int[] roots, int node) {
		int root = node;
		while (roots[root] != root) {
			root = roots[root];
		}
		// roots[node] = root;
		return root;
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
}
