import java.io.*;
import java.util.*;

public class adventuremoving43 {

	static int tankSize = 200;
	static int currentVolume = tankSize / 2;
	static int totalSpent = 0;
	static Stack<Invoice> invoices = new Stack<Invoice>();

	public static void main(String[] args) {
		int length = reader.nextInt();

		int lastPosition = 0;
		while (reader.hasNext()) {
			int position = reader.nextInt();
			int price = reader.nextInt();
			currentVolume -= (position - lastPosition);
			if (currentVolume < 0) {
				break;
			}
			refundHighPrice(price);
			invoices.push(new Invoice(price, tankSize - currentVolume));
			totalSpent += (tankSize - currentVolume) * price;
			currentVolume = tankSize;
			lastPosition = position;
		}
		currentVolume -= length - lastPosition;
		currentVolume -= tankSize / 2;
		refundHighPrice(0);
		System.out.println(currentVolume < 0 ? "Impossible" : totalSpent);
	}

	static void refundHighPrice(int currentPrice) {
		Invoice invoice;
		while (currentVolume > 0 && !invoices.isEmpty() && (invoice = invoices.peek()).price > currentPrice) {
			int min = Math.min(currentVolume, invoice.volume);
			invoice.volume -= min;
			currentVolume -= min;
			totalSpent -= min * invoice.price;
			invoices.pop();
		}
	}

	static class Invoice {
		public int price;
		public int volume;

		public Invoice(int price, int volume) {
			this.price = price;
			this.volume = volume;
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
