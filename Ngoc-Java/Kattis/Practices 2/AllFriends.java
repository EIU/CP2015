import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AllFriends {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		StringBuilder outBf = new StringBuilder();

		do {
			int n = ni();
			int m = ni();

			BitSet[] points = new BitSet[n];
			for (int i = 0; i < n; i++) {
				points[i] = new BitSet(n);
			}

			for (int i = 0; i < m; i++) {
				int v1 = ni() - 1;
				int v2 = ni() - 1;
				points[v1].set(v2);
				points[v2].set(v1);
			}

			List<BitSet> list = new ArrayList<BitSet>();
			for (int i = 0; i < n; i++) {
				BitSet point = points[i];
				int len = list.size();
				for (int j = 0; j < len; j++) {
					BitSet set = list.get(j);

					if (subset(point, set)) {
						set.set(i);
					}
				}

				for (int j = 0; j < len; j++) {
					BitSet set = list.get(j);
					if (!subset(point, set)) {
						BitSet clone = (BitSet) point.clone();
						clone.and(list.get(j));
						clone.set(i);
						replaceAndAdd(list, clone, len);
					}
				}

				BitSet newSet = new BitSet(n);
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
	static void replaceAndAdd(List<BitSet> list, BitSet newSet, int last) {
		int k;
		for (k = list.size() - 1; k >= last; k--) {
			if (subset(newSet, list.get(k))) {
				list.remove(k);
			}
		}

		k = 0;
		for (; k < list.size(); k++) {
			if (subset(list.get(k), newSet)) {
				break;
			}
		}
		if (k == list.size()) {
			list.add(newSet);
		}
	}

	static boolean subset(BitSet source, BitSet target) {
		BitSet clone = (BitSet) target.clone();
		clone.and(source);
		return clone.equals(target);
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
