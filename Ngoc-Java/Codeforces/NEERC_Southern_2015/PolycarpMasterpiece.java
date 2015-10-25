import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PolycarpMasterpiece {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		long s = System.currentTimeMillis();
		solve();
		System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {

		String s = ns();
		int n = ni();
		int m = ni();

		StringBuffer ss = new StringBuffer(s);
		int i;
		int limit = Math.max(n, 63);
		for (i = 1; i <= limit && ss.length() < 1000000; i++) {
			int k = 0;
			if (i <= n) {
				k = ni();
			}
			k %= ss.length();
			String s1 = ss.substring(ss.length() - k);
			String s2 = ss.substring(0, ss.length() - k);
			ss.append(s1 + s2);
		}

		// chars = ns().toCharArray();
		chars = ss.toString().toCharArray();
		len = chars.length;
		int nChar = 'z' - 'a' + 1;
		sums = new int[nChar][len];
		for (int u = 0; u < len; u++) {
			int c = chars[u] - 'a';
			for (int j = 0; j < nChar; j++) {
				sums[j][u] = (u > 0 ? sums[j][u - 1] : 0) + (c == j ? 1 : 0);
			}
		}

		long maxLen = len;
		int maxi = 0;
		kthLen[0] = len;
		n -= i - 1;
		limit = Math.max(n, 63);
		for (i = 1; i <= limit; i++) {
			int k = 0;
			if (i <= n) {
				k = ni();
			}
			if (maxLen < Long.MAX_VALUE >> 1) {
				kth[i] = (int) (k % kthLen[i - 1]);
				maxLen <<= 1;
				kthLen[i] = maxLen;
				maxi = i;
			} else if (i < 64) {
				kthLen[i] = Long.MAX_VALUE;
			}
		}

		StringBuilder bf = new StringBuilder();
		for (i = 0; i < m; i++) {
			bf.append(calculate(nl() - 1, nl() - 1, nc(), maxLen, maxi) + "\n");
			// System.out.println(calculate(nl() - 1, nl() - 1, nc(), maxLen));
		}
		// System.out.println(bf);
	}

	static char[] chars;
	static int len;
	static int[][] sums;
	static int[] kth = new int[64];
	static long[] kthLen = new long[64];

	static long calculate(long left, long right, char c, long upper, int j) {
		if (right < len) {
			// System.out.println(1);
			return sums[c - 'a'][(int) right] - (left > 0 ? sums[c - 'a'][(int) left - 1] : 0);
		}

		long haft = upper >> 1;
		if (right < haft) {
			// System.out.println(2);
			return calculate(left, right, c, haft, j - 1);
		}

		// int i = Arrays.binarySearch(kthLen, upper);
		int i = j;
		long k = kth[i];

		if (left == 0 && right == haft - 1 || left == haft && right == upper - 1) {
			// System.out.println(3);
			return calculate(0, len - 1, c, len, 0) << (i - 1);
		}

		if (left == haft && right - haft >= k + 1) {
			return calculate(0, haft - 1, c, haft, j - 1) - calculate(right - haft + 1 - k, haft - 1 - k, c, haft, j - 1);
		}

		if (right == upper - 1) {
			
		}

		if (left - k >= haft) {
			// System.out.println(4);
			return calculate(left - haft - k, right - haft - k, c, haft, j - 1);
		}

		if (left < haft) {
			System.out.println(5);
			return calculate(left, haft - 1, c, haft, j - 1) + calculate(haft, right, c, upper, j);
		}

		if (right - k >= haft) {
			// System.out.println(6);
			return calculate(0, right - haft - k, c, haft, j - 1) + calculate(left - k, haft - 1, c, haft, j - 1);
		}

		// System.out.println(7);
		return calculate(left - k, right - k, c, haft, j - 1);
	}

	static long cal(long right, char c, long upper) {

		if (right == upper - 1) {

		}
		return 0;
	}

	/*****************************************************************
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

	static long nl() {
		long num = 0;
		int b;
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char nc() {
		return (char) skip();
	}
}