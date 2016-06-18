import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class NonBoringSequences {
	static InputStream is;
	static StringBuilder outBf = new StringBuilder();

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int n = ni();

		Arrays.fill(pres, 0, n, -1);
		Arrays.fill(nexts, 0, n, n);

		HashMap<Integer, Integer> preMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < n; i++) {
			Integer v = numbers[i] = ni();
			Integer pre = preMap.get(v);
			if (pre != null) {
				nexts[pre] = i;
				pres[i] = pre;
			}
			preMap.put(v, i);
		}
		System.out.println(find(0, n) ? "non-boring" : "boring");
	}

	static final int MAX = 200001;
	static Integer[] numbers = new Integer[MAX];
	static Integer[] pres = new Integer[MAX];
	static Integer[] nexts = new Integer[MAX];
	static boolean[] rands = new boolean[MAX];

	static boolean find(int left, int right) {
		if (left >= right - 1) {
			return true;
		}
		int mid = (Math.random() < 0.5d) ? left : (right - 1);
		int inc = (mid == left) ? 1 : -1;
		while (left <= mid && mid < right && (pres[mid] >= left || nexts[mid] < right)) {
			mid += inc;
		}
		// System.out.println(mid + " " + inc);
		if (left <= mid && mid < right && find(left, mid) && find(mid + 1, right)) {
			return true;
		}
		return false;
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
