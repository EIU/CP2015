import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PI_TheCoolMonkeys {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2 3 2 1 3 2 1 1";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @solution:
	 */
	static void solve() {
		int m = ni();
		int nA = ni();
		int nB = ni();
		int T = ni();

		int[] HAs = new int[nA];
		for (int i = 0; i < nA; i++) {
			HAs[i] = ni();
		}
		Arrays.sort(HAs);
		int[] HBs = new int[nB];
		for (int i = 0; i < nB; i++) {
			HBs[i] = ni();
		}
		Arrays.sort(HBs);

		boolean s = solve(HAs, HBs, m, T);
		if (!s) {
			s = solve(HBs, HAs, m, T);
		}
		System.out.println(s ? 'S' : 'N');
	}

	static boolean solve(int[] HAs, int[] HBs, int m, int T) {
		int highestA = HAs.length - 1;
		int highestValidB = HBs.length - 1;
		if (highestA < m - 1 || highestValidB < m - 1) {
			return false;
		}

		while (highestValidB >= m && highestA >= m && !isOK(HAs, HBs, m, T, highestA)) {
			while (highestValidB >= m && (Math.abs(HAs[highestA] - HBs[highestValidB]) > T || Math.abs(HBs[highestValidB] - HAs[highestA - m]) > T)) {
				highestValidB--;
			}
			if (highestValidB >= m && Math.abs(HAs[highestA] - HBs[highestValidB]) <= T && Math.abs(HBs[highestValidB] - HAs[highestA - m]) <= T) {
				highestA--;
				highestValidB--;
			}
		}

		return isOK(HAs, HBs, m, T, highestA);
	}

	static boolean isOK(int[] HAs, int[] HBs, int m, int T, int highestA) {
		int highestB = m - 1;
		if (highestA < m - 1) {
			return false;
		}
		while (highestB >= 0 && Math.abs(HAs[highestA] - HBs[highestB]) <= T) {
			highestA--;
			highestB--;
		}
		return highestB < 0;
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
