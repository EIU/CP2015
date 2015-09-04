import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class PTIT015G {
	static InputStream is;

	static int m;
	static int n;
	static int[] a;
	static int[] b;

	public static void main(String[] args) {
		is = System.in;
		m = ni();
		while (m > 0) {
			n = ni();
			a = new int[m];
			b = new int[n];
			long sum = 0;
			for (int i = 0; i < m; i++) {
				sum += (a[i] = ni());
			}
			for (int i = 0; i < n; i++) {
				sum -= (b[i] = ni());
			}
			if (sum != 0) {
				System.out.println("-1");
			} else {
				solve();
			}
			m = ni();
		}
	}

	/*
	 * + isValid(...): if there is a solution.
	 * + At stepi (0..m-1): find top ai-th column value bi then decrease each of them by 1
	 * + If number of {bi==m-i) is not greater than ai => valid
	 * + Solution: Try to test if cells(i,j) == 1 (ai--, bj--)
	 * + isValid: try to test next cell
	 * + !isValid: rollback to previous valid cell
	 */
	public static void solve() {
		boolean[][] map = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			int ai = a[i];
			int k = -1;
			while (ai > 0) {
				int j = k + 1;
				for (; j <= n - ai; j++) {
					boolean isValid = isValid(i, j);
					if (!isValid) {
						break;
					}
				}
				if (j == k + 1) {
					System.out.println("-1");
					return;
				}
				k = j - 1;
				ai--;
				a[i]--;
				b[k]--;
				map[i][k] = true;
			}
		}

		print(map);
	}

	static boolean isValid(int i, int j) {

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int k = j; k < n; k++) {
			queue.add(-b[k]);
		}

		if (-queue.peek() > m - i) return false;
		if (!remove(queue, a[i])) {
			return false;
		}

		for (int k = 0; k < j; k++) {
			queue.add(-b[k]);
		}
		if (-queue.peek() >= m - i) return false;
		i++;

		for (; i < m; i++) {
			if (!remove(queue, a[i])) {
				return false;
			}
			if (-queue.peek() >= m - i) return false;
		}

		return true;
	}

	static boolean remove(PriorityQueue<Integer> queue, int ai) {
		if (queue.size() < ai) {
			return false;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ai; i++) {
			list.add(queue.poll());
		}
		for (int i = 0; i < ai; i++) {
			queue.add(list.get(i) + 1);
		}
		return true;
	}

	static void print(boolean[][] map) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				bf.append(map[i][j] ? '1' : '0');
			}
		}
		System.out.println(bf.toString());
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096];
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
}
