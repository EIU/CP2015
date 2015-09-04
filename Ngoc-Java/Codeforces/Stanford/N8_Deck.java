import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class N8_Deck {
	static InputStream is;
	static PrintWriter out;
	// static String INPUT = "4 1 2 3 4";
	static String INPUT = "10 5 1 2 3 4 5 1 2 3 4";

	public static void main(String[] args) throws Exception {
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
		int n = ni();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = ni();
		}
		ArrayList<ArrayList<Integer>> listSeq = new ArrayList<ArrayList<Integer>>();
		listSeq.add(new ArrayList<Integer>());
		listSeq.add(new ArrayList<Integer>());
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		a2.add(Math.max(a[1], a[2]));
		listSeq.add(a2);
		for (int i = 3; i <= n; i++) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> seqi2 = (ArrayList<Integer>) listSeq.get(i - 2).clone();
			int max2 = Math.max(a[i], a[i - 1]);
			seqi2.add(max2);

			ArrayList<Integer> seqi3 = listSeq.get(i - 3);
			if (i != 4) {
				seqi3.add(Math.max(a[i - 2], max2));
			}

			int cmp = compare(seqi2, seqi3);
			if (cmp > 0) listSeq.add(seqi2);
			else listSeq.add(seqi3);
		}

		listSeq.get(n).sort((s, t) -> t.compareTo(s));

		for (Integer item : listSeq.get(n)) {
			out.print(item + "\n");
		}
	}

	static int compare(ArrayList<Integer> seq1, ArrayList<Integer> seq2) {
		seq1.sort((s, t) -> s.compareTo(t));
		seq2.sort((s, t) -> s.compareTo(t));
		int i1 = seq1.size() - 1;
		int i2 = seq2.size() - 1;
		while (i1 >= 0 && i2 >= 0) {
			int t1 = seq1.get(i1);
			int t2 = seq2.get(i2);
			if (t1 != t2) return t1 - t2;
			i1--;
			i2--;
		}
		return i1 - i2;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 ******** http://tinhotgioitre.com/nhin-canh-nay-thi-sao-ma-kiem-che-duoc-roi-het-nuoc-mieng/
	 *********************************************************/

	static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1) throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0) return -1;
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

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
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

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
