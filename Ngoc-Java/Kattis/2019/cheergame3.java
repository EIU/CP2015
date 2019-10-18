import java.io.*;
import java.util.*;

public class cheergame3 {

	static int[][][] currentStatus = new int[19][19][10];
	static int[][][] nextStatus = new int[19][19][10];

	public static void main(String[] args) {
		int n = reader.nextInt();
		int t = reader.nextInt();
		int m = reader.nextInt();

		int[] cheers = new int[92];
		int[] cheersStart = new int[91];
		for (int i = 0; i < m; i++) {
			cheersStart[reader.nextInt() + 1]++;
			cheers[reader.nextInt() + 1]--;
		}
		cheers[0] = cheersStart[0];
		for (int i = 1; i < 91; i++) {
			cheers[i] += cheers[i - 1] + cheersStart[i];
		}

		// long ss = System.currentTimeMillis();
		fillStatus(currentStatus, Integer.MAX_VALUE);
		fillStatus(nextStatus, Integer.MAX_VALUE);
		currentStatus[0][0][5] = 0;
		int total = n * t;

		for (int i = 0; i < 90; i++) {
			for (int f = 0; f < 19; f++) {
				for (int s = 0; s < 19; s++) {
					for (int p = 0; p < 10; p++) {
						int preValue = currentStatus[f][s][p];
						if (preValue <= total) {
							boolean s2 = false, s0 = false;
							int nextCheer = cheers[i + 1];
							if (p == 9 && n > nextCheer && i != 45) {
								setStatus(f + 1, s, 5, preValue + nextCheer + 1);
								s2 = true;
							}
							if (p == 1 && nextCheer > 0 && i != 45) {
								setStatus(f, s + 1, 5, preValue);
								s0 = true;
							}
							if (!s2 && n > nextCheer) {
								setStatus(f, s, i == 45 ? 6 : Math.min(Math.max(p, 5) + 1, 9),
										preValue + nextCheer + 1);
							}
							if (n >= nextCheer) {
								setStatus(f, s, 5, preValue + nextCheer);
							}
							if (!s0 && nextCheer > 0) {
								setStatus(f, s, i == 45 ? 4 : Math.max(Math.min(p, 5) - 1, 1), preValue);
							}
						}
					}
				}
			}
			int[][][] temp = currentStatus;
			currentStatus = nextStatus;
			nextStatus = temp;
			fillStatus(nextStatus, Integer.MAX_VALUE);
		}

		int bf = -19, bs = 19;
		for (int f = 0; f < 19; f++) {
			for (int s = 0; s < 19; s++) {
				for (int p = 0; p < 10; p++) {
					if (currentStatus[f][s][p] <= total) {
						if (f - s > bf - bs || (f - s == bf - bs && bf < f)) {
							bf = f;
							bs = s;
						}
					}
				}
			}
		}

		System.out.println(bf + " " + bs);
	}

	static void setStatus(int f, int s, int p, int value) {
		if (nextStatus[f][s][p] > value) {
			nextStatus[f][s][p] = value;
		}
	}

	static void fillStatus(int[][][] status, int value) {
		for (int f = 0; f < 19; f++) {
			for (int s = 0; s < 19; s++) {
				Arrays.fill(status[f][s], value);
			}
		}
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
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

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
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

		long nextLong() {
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
	}
}
