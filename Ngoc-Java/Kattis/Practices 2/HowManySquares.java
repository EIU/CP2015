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

		long result = 0;
		for (int i = 0; i < n; i++) {
			addLine(lines[i]);
			if (i == n - 1 || compare(lines[i].angle, lines[i + 1].angle) != 0) {
				result += getValue();
				clear();
			}
		}
		System.out.println(result);
	}

	static List<Double> list0 = new ArrayList<Double>();
	static List<Double> list1 = new ArrayList<Double>();

	static void addLine(Line line) {
		if (line.type == 0) {
			list0.add(line.fy);
		} else {
			list1.add(line.fy);
		}
	}

	static long getValue() {
		Double[] st0 = createSegment(list0);
		Double[] st1 = createSegment(list1);

		long result = 0;
		int i = 0, j = 0;
		while (i < st0.length && j < st1.length) {
			int cmp = compare(st0[i], st1[j]);
			if (cmp < 0) {
				i++;
			} else if (cmp > 0) {
				j++;
			} else {
				long count0 = 0;
				double pre = st0[i];
				while (i < st0.length && compare(st0[i], pre) == 0) {
					count0++;
					i++;
				}
				pre = st1[j];
				long count1 = 0;
				while (j < st1.length && compare(st1[j], pre) == 0) {
					count1++;
					j++;
				}
				result += count0 * count1;
			}
		}
		return result;
	}

	static Double[] createSegment(List<Double> list) {
		Collections.sort(list);
		int listSize = list.size();
		Double[] result = new Double[listSize * (listSize - 1) / 2];

		int rIndex = 0;
		for (int i = 0; i < listSize; i++) {
			for (int j = i + 1; j < listSize; j++) {
				result[rIndex++] = list.get(j) - list.get(i);
			}
		}
		Arrays.sort(result);
		return result;
	}

	static void clear() {
		list0.clear();
		list1.clear();
	}

	static final double e = 0.0000000001;

	static int compare(double x, double y) {
		double d = x - y;
		if (-e <= d && d <= e)
			return 0;
		else if (d > e)
			return 1;
		else
			return -1;
	}

	static class Line implements Comparable<Line> {

		double angle;
		int type = 0;
		double fy;

		public Line(int x1, int y1, int x2, int y2) {
			int dx = x1 - x2;
			int dy = y1 - y2;

			if (dx == 0 || dx * dy < 0) {
				type = 1;
				angle = -(double) dx / dy;
				fy = -y1 * (double) dx / dy + x1;
			} else {
				angle = (double) dy / dx;
				fy = -x1 * (double) dy / dx + y1;
			}
		}

		@Override
		public int compareTo(Line arg0) {
			int cmp = compare(angle, arg0.angle);
			return cmp;
		}

		@Override
		public String toString() {
			return type + "\t" + angle + "\t" + fy;
		}
	}

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
