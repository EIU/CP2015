import java.io.*;
import java.util.*;

public class Classrooms {

	static InputStream is = System.in;

	public static void main(String[] args) throws Exception {

		int n = ni(), k = ni();

		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			segments[i] = new Segment(ni(), ni(), i);
		}
		Arrays.sort(segments);

		TreeSet<Segment> bucket = new TreeSet<Segment>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			Segment current = segments[i];
			Segment lowerKey = bucket.lower(new Segment(0, current.start, 0));
			if (lowerKey == null) {
				if (bucket.size() < k) {
					bucket.add(current);
					count++;
				}
			} else {
				bucket.remove(lowerKey);
				bucket.add(current);
				count++;

			}
		}
		System.out.print(count);
	}

	static class Segment implements Comparable<Segment> {
		public int start = 0;
		public int end = 0;
		public int id = 0;

		public Segment(int start, int end, int id) {
			this.start = start;
			this.end = end;
			this.id = id;
		}

		@Override
		public int compareTo(Segment arg0) {
			int cmp = end - arg0.end;
			if (cmp == 0) {
				cmp = start - arg0.start;
			}
			if (cmp == 0) {
				cmp = id - arg0.id;
			}
			return cmp;
		}
	}

	/******************************************************************/

	static byte[] inbuf = new byte[1 << 16];
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

	static boolean hasNext() {
		return ptrbuf + 3 < lenbuf;
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static int ni() {
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
}