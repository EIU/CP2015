import java.io.*;
import java.util.*;

public class TAXIDRIVER2 {

	public static void main(String[] args) {
		int T = reader.nextInt();
		StringBuilder outBf = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int n = reader.nextInt();
			int m = reader.nextInt();

			@SuppressWarnings("unchecked")
			ArrayList<Trip>[] map = new ArrayList[n + 1];
			for (int i = 0; i < m; i++) {
				Trip trip = new Trip(reader.nextInt(), reader.nextInt(), reader.nextInt());
				map[trip.start].add(trip);
			}

			for (int i = n; i >= 1; i--) {
				for (Trip trip : map[i]) {
					for (int j = trip.start + 1; j <= trip.end; j++) {
						map[j].add(trip);
					}
				}
			}

			for (int i = 1; i < n; i++) {

			}

			outBf.append("\r\n");
		}
		System.out.println(outBf);
	}

	static class Trip {
		public int start;
		public int end;
		public int cost;
		public int[] holdMoney;

		public Trip(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
			holdMoney = new int[end - start + 1];
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
