import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PolycarpMasterpiece {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		// long s = System.currentTimeMillis();
		solve();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {

		String s = ns();
		int n = ni();
		int m = ni();

		StringBuffer ss = new StringBuffer(s);
		int i = 1;
		int limit = Math.max(n, 63);
		for (i = 1; i <= limit && ss.length() < 100000; i++) {
			int k = 0;
			if (i <= n) {
				k = ni();
			}
			k %= ss.length();
			String s1 = ss.substring(ss.length() - k);
			String s2 = ss.substring(0, ss.length() - k);
			ss.append(s1 + s2);
		}

		char[] chars = ss.toString().toCharArray();
		len = chars.length;
		int nChar = 'z' - 'a' + 1;
		sums = new int[nChar][len];
		for (int u = 0; u < len; u++) {
			int c = chars[u] - 'a';
			for (int j = 0; j < nChar; j++) {
				sums[j][u] = (u > 0 ? sums[j][u - 1] : 0) + (c == j ? 1 : 0);
			}
		}

		int maxi = 0;
		kthLen[0] = len;
		n -= i - 1;
		limit = Math.max(n, 63);
		for (i = 1; i <= limit; i++) {
			int k = 0;
			if (i <= n) {
				k = ni();
			}
			if (i < 64 && kthLen[i - 1] < Long.MAX_VALUE >> 1) {
				kth[i] = (int) (k % kthLen[i - 1]);
				kthLen[i] = kthLen[i - 1] << 1;
				maxi = i;
				kthSums[i] = (i > 0 ? kthSums[i - 1] : 0) + kth[i];
			} else if (i < 64) {
				kthLen[i] = Long.MAX_VALUE;
				kthSums[i] = Integer.MAX_VALUE;
			}
		}

		StringBuilder bf = new StringBuilder();
		for (i = 0; i < m; i++) {
			long left = nl() - 1;
			long right = nl() - 1;
			char c = nc();
			int[] sum = sums[c - 'a'];
			bf.append(calculate(right, sum, maxi) - (left > 0 ? calculate(left - 1, sum, maxi) : 0l));
			bf.append("\n");
		}
		System.out.println(bf);
	}

	static int len;
	static int[][] sums;
	static int[] kth = new int[64];
	static long[] kthLen = new long[64];
	static int[] kthSums = new int[64];

	static long calculate(long right, int[] sum, int maxi) {
		if (right < len) {
			return sum[(int) right];
		}

		long upper = kthLen[maxi];
		long haft = kthLen[maxi - 1];
		if (right < haft) {
			return calculate(right, sum, maxi - 1);
		}

		int k = kth[maxi];
		if (right == upper - 1) {
			return ((long) sum[len - 1]) << maxi;
		}

		long leftValue = ((long) sum[len - 1]) << (maxi - 1);

		if (right - k >= haft) {
			leftValue += calculate(right - haft - k, sum, maxi - 1);
		}

		int x = len - kthSums[maxi];
		int y = len - (int) Math.max(haft + k - right, 1) - kthSums[maxi - 1];

		return leftValue + sum[y] - sum[x - 1];
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

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