import java.io.*;
import java.util.*;

public class PATHINGRID2 {

	public static void main(String[] args) {
		int T = reader.nextInt();
		StringBuilder outBf = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int n = reader.nextInt();
			int m = reader.nextInt();
			long[][] statuses = new long[m][64];
			for (int i = 0; i < n; i++) {
				char[] numbers = reader.next().toCharArray();
				for (int j = 0; j < m; j++) {
					int c = numbers[j] - '0';
					if (i == 0 && j == 0) {
						set(statuses[0], c);
						continue;
					}
					if (i > 0 && c > 0) {
						shiftUp(statuses[j], c);
					}
					if (j > 0) {
						mergeLeftToRight(statuses[j - 1], statuses[j], c);
					}
				}
			}
			for (int b = 0; b < 4001; b++) {
				if (check(statuses[m - 1], b)) {
					outBf.append(b + " ");
				}
			}
			outBf.append("\r\n");
		}
		System.out.println(outBf);
	}

	static void shiftUp(long[] status, int shift) {
		long firstBits = 0;
		for (int i = 0; i < status.length; i++) {
			long temp = shift > 0 ? (status[i] >>> (64 - shift)) : 0;
			status[i] = (status[i] << shift) | firstBits;
			firstBits = temp;
		}
	}

	static void mergeLeftToRight(long[] leftStatus, long[] rightStatus, int shift) {
		long firstBits = 0;
		for (int i = 0; i < rightStatus.length; i++) {
			long left = leftStatus[i];
			long temp = shift > 0 ? (left >>> (64 - shift)) : 0;
			left = (left << shift) | firstBits;
			firstBits = temp;

			rightStatus[i] |= left;
		}
	}

	static void set(long[] status, int m) {
		int index = m / 64;
		status[index] |= (0x1 << (m % 64));
	}

	static boolean check(long[] status, int m) {
		int index = m / 64;
		return ((status[index] >>> (m % 64)) & 0x1) != 0;
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
