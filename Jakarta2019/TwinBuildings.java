import java.io.*;
import java.util.*;

public class TwinBuildings {

	public static void main(String[] args) {
		int N = reader.nextInt();
		Rectangle[] rectangles = new Rectangle[N];
		for (int i = 0; i < N; i++) {
			rectangles[i] = new Rectangle(reader.nextInt(), reader.nextInt());
		}

		Arrays.sort(rectangles, (r1, r2) -> r2.W - r1.W != 0 ? (r2.W - r1.W) : (r2.L - r1.L));

		Rectangle rectangle = rectangles[0];
		long maxTwinArea = 0, maxArea = (long) rectangle.W * rectangle.L;
		int maxL = rectangle.L;
		for (int i = 1; i < N; i++) {
			rectangle = rectangles[i];
			maxArea = Math.max(maxArea, (long) rectangle.W * rectangle.L);
			int bestL = Math.min(rectangle.L, maxL);
			maxTwinArea = Math.max(maxTwinArea, (long) rectangle.W * bestL);
			maxL = Math.max(maxL, rectangle.L);
		}
		if (maxTwinArea > maxArea / 2) {
			System.out.println(maxTwinArea + ".0");
		} else {
			System.out.println((maxArea / 2) + (maxArea % 2 == 0 ? ".0" : "0.5"));
		}
	}

	static class Rectangle {
		public int W;
		public int L;

		public Rectangle(int w, int l) {
			W = Math.min(l, w);
			L = Math.max(l, w);
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
