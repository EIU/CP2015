import java.io.*;
import java.util.*;

public class adventuremoving4 {

	public static void main(String[] args) {
		int tankSize = 200;
		int length = reader.nextInt();
		int[] status = new int[tankSize + 1];

		Arrays.fill(status, -1);
		status[100] = 0;

		int position = 0;
		while (reader.hasNext()) {

			int lastUsed = -position + (position = reader.nextInt());
			int price = reader.nextInt();

			// Update
			for (int volume = 0; volume <= tankSize; volume++) {
				if (volume + lastUsed <= tankSize && status[volume + lastUsed] >= 0) {
					status[volume] = status[volume + lastUsed];
				} else {
					status[volume] = -1;
				}
			}

			for (int volume = 1; volume <= tankSize; volume++) {
				if (status[volume - 1] >= 0) {
					status[volume] = Integer.min(status[volume - 1] + price,
							status[volume] < 0 ? Integer.MAX_VALUE : status[volume]);
				}
			}
		}

		int lastUsed = length - position;
		int min = (100 + lastUsed) <= tankSize ? status[100 + lastUsed] : -1;
		System.out.println(min >= 0 ? min : "Impossible");
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
