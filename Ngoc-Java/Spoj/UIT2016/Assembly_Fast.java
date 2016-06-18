import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class Assembly {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		int T = ni();
		for (int t = 0; t < T; t++) {
			int N = ni();
			long M = nl();

			Robot[] robots = new Robot[N];
			for (int i = 0; i < N; i++) {
				robots[i] = new Robot(ni(), ni());
			}
			Arrays.sort(robots);

			long invPerUnit = robots[0].invPerProduct;
			long product = robots[0].productivity;
			for (int i = 1; i < N; i++) {

				int improveP = robots[i].productivity - robots[i - 1].productivity;
				if (invPerUnit * improveP >= M) {
					product += M / invPerUnit;
					M = 0;
					break;
				}

				M -= invPerUnit * improveP;
				invPerUnit += robots[i].invPerProduct;
				product = robots[i].productivity;
			}
			product += M / invPerUnit;

			System.out.println(product);
		}
	}

	static class Robot implements Comparable<Robot> {
		public int productivity;
		public int invPerProduct;

		public Robot(int p, int m) {
			this.productivity = p;
			this.invPerProduct = m;
		}

		@Override
		public int compareTo(Robot arg0) {
			int t = this.productivity - arg0.productivity;
			if (t != 0) {
				return t;
			}
			return this.invPerProduct - arg0.invPerProduct;
		}
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 12];
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
