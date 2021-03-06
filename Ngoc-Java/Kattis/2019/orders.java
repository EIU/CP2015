import java.io.*;
import java.util.*;

public class orders {

	public static void main(String[] args) {
		int n = reader.nextInt();
		int[] prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = reader.nextInt();
		}
		int maxValue = 30001;
		int[] countPaths = new int[maxValue];
		int[] lastItems = new int[maxValue];
		countPaths[0] = 1;
		lastItems[0] = 1000;

		for (int j = 1; j < maxValue; j++) {
			for (int i = n - 1; i >= 0; i--) {
				int previous = j - prices[i];
				if (previous >= 0 && lastItems[previous] >= i && countPaths[previous] > 0) {
					countPaths[j] = Math.min(countPaths[j] + countPaths[previous], 1000000000);
					lastItems[j] = Math.max(i, lastItems[j]);
				}
			}
		}

		int m = reader.nextInt();
		StringBuilder outBf = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int totalValue = reader.nextInt();
			if (countPaths[totalValue] == 0) {
				outBf.append("Impossible");
			} else if (countPaths[totalValue] > 1) {
				outBf.append("Ambiguous");
			} else {
				while (totalValue > 0) {
					outBf.append((lastItems[totalValue] + 1) + " ");
					totalValue -= prices[lastItems[totalValue]];
				}
			}
			outBf.append("\r\n");
		}
		System.out.println(outBf);
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
