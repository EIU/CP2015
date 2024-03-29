import java.io.*;
import java.util.*;

public class cheergame {

	static int[][][][][] status = new int[92][19][19][81][5];

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

		fillStatus(Integer.MAX_VALUE);
		status[0][0][0][0][4] = 0;
		int total = n * t;

		for (int i = 0; i < 91; i++) {
			for (int f = 0; f < 19; f++) {
				for (int s = 0; s < 19; s++) {
					for (int p = 0; p < 81; p++) {
						for (int b = 0; b < 5; b++) {
							int preValue = status[i][f][s][p][b];
							int newb = b > 0 ? (b - 1) : 0;
							if (preValue <= total) {
								boolean s2 = false, s0 = false;
								int p0 = p / 3, p1 = p0 + 27, p2 = p0 + 54;
								if (p == 80 && (i < 45 || i >= 49) && b == 0 && n > cheers[i + 1]) {
									setStatus(i + 1, f + 1, s, p2, 4, preValue + cheers[i + 1] + 1);
									s2 = true;
								}
								if (p == 0 && (i < 45 || i >= 49) && b == 0 && cheers[i + 1] > 0) {
									setStatus(i + 1, f, s + 1, p0, 4, preValue);
									s0 = true;
								}
								if (!s2 && n > cheers[i + 1]) {
									setStatus(i + 1, f, s, p2, newb, preValue + cheers[i + 1] + 1);
								}
								if (n >= cheers[i + 1]) {
									setStatus(i + 1, f, s, p1, newb, preValue + cheers[i + 1]);
								}
								if (!s0 && cheers[i + 1] > 0) {
									setStatus(i + 1, f, s, p0, newb, preValue);
								}
							}
						}
					}
				}
			}
		}

		int bf = -19, bs = 19;
		for (int f = 0; f < 19; f++) {
			for (int s = 0; s < 19; s++) {
				for (int p = 0; p < 81; p++) {
					for (int b = 0; b < 5; b++) {
						if (status[90][f][s][p][b] <= total) {
							if (f - s > bf - bs || (f - s == bf - bs && bf < f)) {
								bf = f;
								bs = s;
							}
						}
					}
				}
			}
		}

		System.out.println(bf + " " + bs);
	}

	static void setStatus(int i, int f, int s, int p, int b, int value) {
		status[i][f][s][p][b] = Math.min(status[i][f][s][p][b], value);
	}

	static void fillStatus(int value) {
		for (int i = 0; i < 92; i++) {
			for (int f = 0; f < 19; f++) {
				for (int s = 0; s < 19; s++) {
					for (int p = 0; p < 81; p++) {
						Arrays.fill(status[i][f][s][p], value);
					}
				}
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
