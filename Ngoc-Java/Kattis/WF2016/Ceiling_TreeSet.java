import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.*;

public class Ceiling_TreeSet {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int k = ni();
		List<Integer[]> list = new ArrayList<Integer[]>();
		for (int i = 0; i < n; i++) {
			Integer[] levels = readAndHashTree(k);
			int j = 0;
			while (j < list.size() && !areEqual(levels, list.get(j))) {
				j++;
			}
			if (j == list.size()) {
				list.add(levels);
			}
		}
		System.out.println(list.size());
	}

	static boolean areEqual(Integer[] source, Integer[] target) {
		for (int i = 0; i < source.length; i++) {
			if (!source[i].equals(target[i])) {
				return false;
			}
		}
		return true;
	}

	static Integer[] readAndHashTree(int k) {
		TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
		for (int i = 0; i < k; i++) {
			int key = ni();
			Entry<Integer, Integer> lower = tree.lowerEntry(key);
			Entry<Integer, Integer> upper = tree.higherEntry(key);
			int level = 3;
			if (lower != null && upper == null) {
				level = lower.getValue() + 1;
			} else if (lower == null && upper != null) {
				level = upper.getValue() + 1;
			} else if (lower != null && upper != null) {
				level = Math.max(lower.getValue(), upper.getValue()) + 1;
			}
			tree.put(key, level);
		}
		Integer[] levels = new Integer[k];
		return tree.values().toArray(levels);
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
