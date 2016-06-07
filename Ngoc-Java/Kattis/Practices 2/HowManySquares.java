import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HowManySquares {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		Line[] lines = new Line[n];

		for (int i = 0; i < n; i++) {
			lines[i] = new Line(ni(), ni(), ni(), ni());
		}

		Arrays.sort(lines);

	}

	static int count(double[] fx, double[] ifx) {
		int result = 0;
		List<Double> xSegments = new ArrayList<Double>();

		for (int base = 0; base < fx.length - 1; base++) {
			for (int i = base + 1; i < fx.length; i++) {
				xSegments.add(fx[i] - fx[base]);
			}
		}

		Collections.sort(xSegments);

		return 0;
	}

	static class Line implements Comparable<Line> {

		int dx;
		int dy;
		int type = 0;
		double fx;

		public Line(int x1, int y1, int x2, int y2) {
			dx = x1 - x2;
			dy = y1 - y2;

			long c = gcd(dx >= 0 ? dx : -dx, dy >= 0 ? dy : -dy);
			dx /= c;
			dy /= c;

			if (dx < 0) {
				dx = -dx;
				dy = -dy;
			}

			if (dy < 0) {
				fx = (0 - x1) * dy / dx + y1;
				type = 1;
				int temp = dx;
				dx = -dy;
				dy = temp;
			} else {
				fx = (0 - y1) * dx / dy + x1;
			}
		}

		@Override
		public int compareTo(Line arg0) {
			int cmp = dx - arg0.dx;
			if (cmp != 0) {
				return cmp;
			}
			cmp = dy - arg0.dy;
			if (cmp != 0) {
				return cmp;
			}
			cmp = type - arg0.type;
			if (cmp != 0) {
				return cmp;
			}
			return Double.compare(fx, arg0.fx);
		}
	}

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	/*
	 			dx = x1 - x2;
			dy = y1 - y2;

			if (dx == 0) {
				dy = 1;
				fx = x1;
			} else if (dy == 0) {
				dx = 0;
				dy = 1;
				type = 1;
				fx = y1;
			}
			else {
				if (dx < 0) {
					dx = -dx;
					dy = -dy;
				}

				if (dy < 0) {
					fx = (0 - x1) * dy / dx + y1;
					type = 1;
					int temp = dx;
					dx = -dy;
					dy = temp;
				} else {
					fx = (0 - y1) * dx / dy + x1;
				}

				long c = gcd(dx, dy);
				dx /= c;
				dy /= c;
			}
	 */

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
}
