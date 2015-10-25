import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GourmetAndBanquet {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		int n = ni();
		Edge[] edges = new Edge[n];
		int[] endpoints = new int[n << 1];

		for (int i = 0, j = 0; i < n; i++, j += 2) {
			edges[i] = new Edge(endpoints[j] = ni(), endpoints[j + 1] = ni());
		}

		Arrays.sort(edges);
		Arrays.sort(endpoints);

		int[] values = new int[n << 1];

		int max = 0;
		int lower = 0;
		int upper = 10001;
		while (lower + 1 < upper) {
			int mid = (upper + lower) / 2;
			int value = check(edges, endpoints, values, mid);
			max = Math.max(max, value);
			if (value > 0) {
				lower = mid;
			} else {
				upper = mid;
			}
		}

		System.out.println(max);
	}

	static int check(Edge[] edges, int[] endpoints, int[] values, int k) {
		int sum = 0;
		int len = edges.length;
		int vlen = endpoints.length;

		for (int j = 0; j < vlen - 1; j++) {
			values[j] = endpoints[j + 1] - endpoints[j];
		}

		for (int i = 0; i < len; i++) {
			int start = edges[i].start;
			int end = edges[i].end;
			int need = k;
			int j = Arrays.binarySearch(endpoints, start);
			for (; j < vlen && need > 0 && endpoints[j] < end; j++) {
				int minus = Math.min(need, values[j]);
				values[j] -= minus;
				need -= minus;
			}
			if (need == 0) {
				sum += k;
			} else {
				return -1;
			}
		}

		return sum;
	}

	static class Edge implements Comparable<Edge> {
		public int start;
		public int end;

		public Edge(int s, int e) {
			this.start = s;
			this.end = e;
		}

		@Override
		public int compareTo(Edge arg0) {
			return this.end - arg0.end;
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
}
