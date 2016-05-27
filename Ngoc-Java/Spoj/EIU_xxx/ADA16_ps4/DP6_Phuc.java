import java.util.*;
import java.io.*;

public class DP6_Phuc {

	static int arr[];

	public static void main(String[] args) throws IOException {
		lenbuf = is.read(inbuf);
		int t = ni();
		while (t-- > 0) {
			solveTestCase();
		}
	}

	private static void solveTestCase() {
		int n = ni();
		long ans = -1;
		SegmentTree notWork = new SegmentTree(n);
		SegmentTree work = new SegmentTree(n);
		long cS[] = new long[n];
		int k = ni();
		cS[0] = nl();
		ans = cS[0];
		notWork.update(0, -ans);
		work.update(0, 0);
		long maxBeforeK = -1;

		Queue<Long> q = new LinkedList<Long>();
		q.add(cS[0]);
		for (int i = 1; i < n; ++i) {
			int current = ni();
			cS[i] = current + cS[i - 1];
			if (i < k) {
				notWork.update(i, -cS[i]);
				q.add(cS[i]);
				ans = cS[i];
			} else {

				Max max = notWork.getMax(i - k, i - 1);
				q.add(max.value + cS[i]);
				maxBeforeK = Math.max(maxBeforeK, q.poll());
				notWork.update(i, maxBeforeK - cS[i]);
				ans = Math.max(ans, max.value + cS[i]);
			}
		}
		System.out.println(ans);
	}

	static InputStream is = System.in;
	static byte[] inbuf = new byte[1 << 23];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {

		if (ptrbuf >= lenbuf) {
			return -1;
		}

		return inbuf[ptrbuf++];
	}

	static boolean hasNext() {
		int t = skip();

		if (t == -1) {
			return false;
		}
		ptrbuf--;
		return true;
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

class SegmentTree {
	public int length;
	public Max[] max;

	public SegmentTree(int n) {
		this.length = n;
		max = new Max[n << 2];
	}

	public void update(int index, long value) {
		update(0, 0, length - 1, index, value);
	}

	private void update(int root, int left, int right, int index, long value) {
		if (left == right) {
			max[root] = new Max(index, value);

			return;
		}
		int mid = (left + right + 1) >> 1;
		int leftChild = (root << 1) + 1;
		int rightChild = (root << 1) + 2;
		if (index >= mid) {
			update(rightChild, mid, right, index, value);
		} else {
			update(leftChild, left, mid - 1, index, value);
		}
		max[root] = max[rightChild] == null
				|| max[leftChild].compareTo(max[rightChild]) >= 0 ? max[leftChild]
				: max[rightChild];

	}

	public Max getMax(int left, int right) {
		return get(0, 0, length - 1, left, right);
	}

	private Max get(int root, int left, int right, int leftRange, int rightRange) {
		if (leftRange <= left && rightRange >= right) {
			return max[root];
		}
		if (left > rightRange || right < leftRange) {
			return new Max(-1, Long.MIN_VALUE);
		}

		int mid = (left + right + 1) >> 1;
		int leftChild = (root << 1) + 1;
		int rightChild = (root << 1) + 2;
		Max leftMax = get(leftChild, left, mid - 1, leftRange, rightRange);
		Max rightMax = get(rightChild, mid, right, leftRange, rightRange);
		return rightMax == null || leftMax.value >= rightMax.value ? leftMax
				: rightMax;

	}
}

class Max implements Comparable<Max> {
	public long value;
	public int index;

	public Max(int index, long value) {
		this.index = index;
		this.value = value;
	}

	@Override
	public int compareTo(Max m) {
		if (this.value != m.value) {
			return Long.compare(this.value, m.value);
		}
		return -this.index + m.index;
	}
}