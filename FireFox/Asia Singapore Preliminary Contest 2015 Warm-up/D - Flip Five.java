import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task {
	char[][] board;
	int res;

	public void solve(int caseNum, InputReader in, PrintWriter out) {
		int numberOfTest = in.nextInt();
		while (numberOfTest-- > 0) {
			board = new char[3][3];
			for (int i = 0; i < 3; i++) {
				board[i] = in.next().toCharArray();
			}
			res = Integer.MAX_VALUE;
			go(new char[9], 0);
			out.println(res);
		}
	}

	boolean check(char[] a) {
		int i = 0;
		int j = 0;
		int[] di = { -1, 0, 0, 1 };
		int[] dj = { 0, -1, 1, 0 };
		int[][] count = new int[3][3];
		for (int c = 0; c < 9; c++) {
			if (j == 3) {
				i++;
				j = 0;
			}
			if (a[c] == '1') {
				count[i][j]++;
				for (int d = 0; d < di.length; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (isValid(ni, nj)) {
						++count[ni][nj];
					}
				}
			}
			j++;
		}
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				int cur = count[r][c] % 2;
				int o = board[r][c] == '.' ? 0 : 1;
				if (cur != o) {
					return false;
				}
			}
		}
		return true;
	}

	boolean isValid(int i, int j) {
		return i >= 0 && i < 3 && j >= 0 && j < 3;
	}

	void go(char[] a, int index) {
		if (index == a.length) {
			if (check(a)) {
				int cost = 0;
				for (int i = 0; i < a.length; i++) {
					if (a[i] == '1')
						++cost;
				}
				res = Math.min(res, cost);
			}
			return;
		}
		char[] t = { '0', '1' };
		for (int i = 0; i < 2; i++) {
			a[index] = t[i];
			go(a, index + 1);
		}
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}
}
