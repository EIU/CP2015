import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AllFriends2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		StringBuilder outBf = new StringBuilder();

		do {
			int n = ni();
			int m = ni();

			MySet[] points = new MySet[n];
			for (int i = 0; i < n; i++) {
				points[i] = new MySet();
			}

			for (int i = 0; i < m; i++) {
				int v1 = ni() - 1;
				int v2 = ni() - 1;
				points[v1].set(v2);
				points[v2].set(v1);
			}

			List<MySet> list = new ArrayList<MySet>();
			for (int i = 0; i < n; i++) {
				MySet point = points[i];
				int len = list.size();
				for (int j = 0; j < len; j++) {
					MySet set = list.get(j);

					if (point.isSubset(set)) {
						set.set(i);
					}
				}

				for (int j = 0; j < len; j++) {
					MySet clone = point.clone();
					clone.and(list.get(j));
					clone.set(i);
					replaceAndAdd(list, clone, len);
				}

				MySet newSet = new MySet();
				newSet.set(i);
				replaceAndAdd(list, newSet, len);

				if (list.size() > 1000) {
					break;
				}
			}

			outBf.append((list.size() > 1000 ? "Too many maximal sets of friends." : list.size()) + "\n");
		} while (hasNext());
		System.out.println(outBf);
	}

	// Replace or Add
	static void replaceAndAdd(List<MySet> list, MySet newSet, int last) {
		int k;
		int u = list.size();
		for (k = list.size() - 1; k >= last; k--) {
			if (newSet.isSubset(list.get(k))) {
				list.remove(k);
				//				list.set(k, null);
				//				u = k;
			}
		}

		// 10% faster
		//		for (k = u + 1; k < list.size(); k++) {
		//			if (list.get(k) != null) {
		//				list.set(u++, list.get(k));
		//			}
		//		}
		//
		//		for (k = list.size() - 1; k >= u; k--) {
		//			list.remove(k);
		//		}

		k = 0;
		for (; k < list.size(); k++) {
			if (list.get(k).isSubset(newSet)) {
				break;
			}
		}
		if (k == list.size()) {
			list.add(newSet);
		}
	}

	static class MySet {
		long l1;
		long l2;

		static long[] masks = new long[64];

		static {
			long mask = 1;
			for (int i = 0; i < 64; i++) {
				masks[i] = mask;
				mask <<= 1;
			}
		}

		public MySet() {

		}

		public MySet(MySet set) {
			l1 = set.l1;
			l2 = set.l2;
		}

		public void set(int i) {
			if (i < 64) {
				l1 |= masks[i];
			} else {
				i -= 64;
				l2 |= masks[i];
			}
		}

		public void and(MySet set) {
			l1 &= set.l1;
			l2 &= set.l2;
		}

		public boolean equals(MySet set) {
			return l1 == set.l1 && l2 == set.l2;
		}

		public boolean isSubset(MySet set) {
			long t1 = set.l1 & l1;
			long t2 = set.l2 & l2;
			return t1 == set.l1 && t2 == set.l2;
		}

		public MySet clone() {
			return new MySet(this);
		}
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 25];
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
