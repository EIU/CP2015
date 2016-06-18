import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HowManySquares2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		List<Line> line0 = new ArrayList<Line>();
		List<Line> line1 = new ArrayList<Line>();

		for (int i = 0; i < n; i++) {
			Line line = new Line(ni(), ni(), ni(), ni());
			if (line.type == 0)
				line0.add(line);
			else
				line1.add(line);
		}

		Collections.sort(line0);
		Collections.sort(line1);

		long result = 0;
		int i = 0, j = 0;
		while (i < line0.size() && j < line1.size()) {
			Line l0 = line0.get(i);
			Line l1 = line1.get(j);
			int cmp = compare(l0.angle, l1.angle);

			if (cmp < 0) {
				i++;
			}
			else if (cmp > 0) {
				j++;
			}
			else {
				clear();
				while (i < line0.size() && compare(line0.get(i).angle, l0.angle) == 0) {
					list0.add(line0.get(i).fy);
					i++;
				}
				while (j < line1.size() && compare(line1.get(j).angle, l1.angle) == 0) {
					list1.add(line1.get(j).fy);
					j++;
				}
				result += getValue();
			}
		}
		System.out.println(result);
	}
	static List<Long> list0 = new ArrayList<Long>();
	static List<Long> list1 = new ArrayList<Long>();

	static long getValue() {
		Long[] st0 = createSegment(list0);
		Long[] st1 = createSegment(list1);

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
				long pre = st0[i];
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

	static Long[] createSegment(List<Long> list) {
		Collections.sort(list);
		int listSize = list.size();
		Long[] result = new Long[listSize * (listSize - 1) / 2];

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

	static final long e = 4;

	static int compare(long x, long y) {
		double d = x - y;
		if (-e <= d && d <= e)
			return 0;
		else if (d > e)
			return 1;
		else
			return -1;
	}

	static long MUL1 = 1l << 32;

	static class Line implements Comparable<Line> {

		long angle;
		int type = 0;
		long fy;

		public Line(int x1, int y1, int x2, int y2) {
			int dx = x1 - x2;
			int dy = y1 - y2;

			if (dx == 0 || dx * dy < 0) {
				type = 1;
				angle = -((dx * MUL1) / dy);
				fy = -(y1 * dx * MUL1 / dy) + x1 * MUL1;
			} else {
				angle = (dy * MUL1 / dx);
				fy = -(x1 * dy * MUL1 / dx) + y1 * MUL1;
			}
		}

		@Override
		public int compareTo(Line arg0) {
			int cmp = compare(angle, arg0.angle);
			return cmp;
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
