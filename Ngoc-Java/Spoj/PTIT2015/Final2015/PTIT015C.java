import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class PTIT015C {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		int T = ni();
		for (int t = 0; t < T; t++) {
			out.println(solve() ? "YES" : "NO");
		}
		out.flush();
	}

	/**
	 * @Simple solution, complexity: O(n^2lgn)
	 * @Improve 1: instead of re-sort array, we can merge the first part (are minus 1) and the second part (they are
	 *          already sorted) => Complexity O(n^2)
	 * @Improve 2:
	 * @+ C={cn>=...>=c2>=c1} ==transform=> {(ni,pi): |{ci=i}| & pi=nearest(n(pi)>0)} => merge & split (ni, pi) has
	 *    complexity O(0) => Total Complexity O(sum(ci))
	 * @Improve 3: I guess there is a O(nlgn) or O(nlgnlgn) solution
	 * @+ Hints: Mỗi lần lặp, ta xác dịnh được vị trí cuối cùng si bị trừ 1. Thay vì trừ ta xem tất cả phần tử (i->si)
	 *    vay 1 => thực hiện merge and split (ni,pi) trong O(0) => tìm vị trí si mới O(sum(c(i+1)-ci)). Tính toán số
	 *    lượng đã vay sử dụng SegmentTree O(nlgn)
	 * @Problem tricks:
	 * @+ For every valid graph of matches: we can always assign Home-Away for all matches so that for every team
	 *    |Home-Away| <= 1. Prove: For every cyclic path, assign H-WH-WH-...W then remove these edges. Finally we have
	 *    some trees => easy
	 * @+ The core problem is: for any two teams, there at most one match between them! => Greedy algorithm!
	 */
	static boolean solve() {
		int n = ni();
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = ni();
		}

		Arrays.sort(c);
		for (int i = n - 1; i >= 0; i--) {
			if (c[i] > i) {
				return false;
			}
			int last = i - c[i];
			for (int j = i - 1; j >= last; j--) {
				if (c[j] > 0) {
					c[j]--;
				} else {
					return false;
				}
			}
			Arrays.sort(c, 0, i);
		}

		return true;
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096];
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
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
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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
