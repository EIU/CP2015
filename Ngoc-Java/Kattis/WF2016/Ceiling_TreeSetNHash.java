import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.*;

public class Ceiling_TreeSetNHash {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int k = ni();
		HashSet<Long> set = new HashSet<Long>();
		for (int i = 0; i < n; i++) {
			set.add(readAndHashTree(k));
		}
		System.out.println(set.size());
	}

	static long M1 = 2038072819L;
	static long M2 = 3121238891L;
	static long P = 32416190071L;

	static long readAndHashTree(int k) {
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

		long hash = 0;
		for (Entry<Integer, Integer> entry : tree.entrySet()) {
			hash = (hash * M1 + entry.getValue() * M2) % P;
		}

		return hash;
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
