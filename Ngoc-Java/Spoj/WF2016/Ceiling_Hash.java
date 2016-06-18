import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class Ceiling_Hash {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		do {
			int n = ni();
			int k = ni();
			HashSet<Long> set = new HashSet<Long>();
			for (int i = 0; i < n; i++) {
				set.add(readAndHashTree(k));
			}
			System.out.println(set.size());
		} while (hasNext());
	}

	static long readAndHashTree(int k) {
		int[] numbers = new int[k];
		for (int i = 0; i < k; i++) {
			numbers[i] = ni();
		}
		return hash(numbers, 0, k, -1, 1000010);
	}

	static long M1 = 2038072819L;
	static long M2 = 3121238891L;
	static long P = 32416190071L;

	static long hash(int[] numbers, int start, int k, int min, int max) {
		if (start == k) {
			return 3;
		}
		if (numbers[start] <= min || numbers[start] >= max) {
			return hash(numbers, start + 1, k, min, max);
		}
		return (M1 * hash(numbers, start + 1, k, numbers[start], max) + M2 * hash(numbers, start + 1, k, min, numbers[start])) % P;
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
