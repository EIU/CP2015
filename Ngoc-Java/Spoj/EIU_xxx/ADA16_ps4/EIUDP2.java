import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class EIUDP2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			int n = ni();
			int k = ni();

			int nCache = n + 4 * k + 1;
			long[] sum = new long[nCache];
			for (int i = 2 * k + 1; i <= n + 2 * k; i++) {
				sum[i] = sum[i - 1] + nl();
			}

			// f(j) = max{f(i) + s(i+k+1,j)}
			// f(j) - s(0,j) = f(i) - s(0,i) + s(0,i) + s(i+k+1,j) - s(0,j)
			// G(j) = G(i) - s(i+1,i+k)
			long max = 0;
			long maxg = 0;
			reset();
			for (int i = 0; i <= n + 2 * k; i++) {
				if (i == 54) {
					int x = 0;
					x++;
				}
				if (cacheSegments[i] != null) {
					add(cacheSegments[i]);
				}
				long g = Math.max(maxg, getMax(i));
				maxg = Math.max(maxg, g) - sum[i + 1] + sum[i];
				cacheSegments[i + k + 1] = new Segment(g - sum[i + k] + sum[i], i + k + k);
				max = Math.max(max, g + sum[i]);
			}
			System.out.println(max);
		}
	}

	static class Segment {
		public long value;
		public int right;

		public Segment(long value, int right) {
			this.value = value;
			this.right = right;
		}
	}

	static final int MAX = 1000000 * 4 + 1;
	static int head = 0;
	static int tail = 0;
	static Segment[] cacheSegments = new Segment[MAX];
	static Segment[] queueSegments = new Segment[MAX];

	static void reset() {
		head = 1;
		tail = 0;
		queueSegments[0] = new Segment(0, MAX);
	}

	static void add(Segment segment) {
		while (head > tail && segment.value >= queueSegments[head - 1].value) {
			head--;
		}
		queueSegments[head++] = segment;
	}

	static long getMax(int i) {
		while (tail < head && queueSegments[tail].right < i) {
			tail++;
		}
		return queueSegments[tail].value;
	}

	/* ****************************************************************
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
