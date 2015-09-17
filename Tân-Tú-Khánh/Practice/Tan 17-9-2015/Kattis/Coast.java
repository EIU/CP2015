import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Coast {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		solve();
	}

	static int res = 0;
	static int[][] grid;
	static int n;
	static int m;
	static void solve() {
		//https://open.kattis.com/problems/coast
		n = ni();
		m = ni();
		grid = new int[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			String s = ns();
			for (int j = 1; j <= m; j++) {
				if (s.charAt(j-1) == '1') {
					grid[i][j] = 1;
				}
			}
		}
		dfs(0,0);
		System.out.println(res);
	}

	static void dfs(int i, int j) {

		if (grid[i][j] == 0) {
			grid[i][j] = 2;
			if(i-1>=0){
				dfs(i-1,j);
			}
			if(j-1>=0){
				dfs(i,j-1);
			}
			if(i+1 <= n+1){
				dfs(i+1,j);
			}
			if(j+1 <= m+1){
				dfs(i,j+1);
			}
			
		} else {
			if (grid[i][j] == 1) {

				res++;
			}
		}

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