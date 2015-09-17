import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder str = new StringBuilder();

	public static void main(String[] args) throws IOException {
		while (true) {
			Double C = nextDouble();
			if (C == null) {
				PrintWriter writer = new PrintWriter(System.out);
				writer.println(str);
				writer.close();
				return;
			}
			solve(C);
		}
	}

	private static void solve(double c) throws IOException {
		int size = (int) c;
		int n = nextInt();
		int[][] dp = new int[n + 1][size + 1];
		int obj[][] = new int[n + 1][2];
		for (int i = 1; i <= n; ++i) {

			int v = nextInt();
			int w = nextInt();
			obj[i][0] = w;
			obj[i][1] = v;
			for (int j = 1; j <= size; ++j) {
				if (j < w) {
					dp[i][j] = dp[i - 1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
			}
		}
		int ans = -1;
		int index = -1;
		for (int i = 0; i <= size; ++i) {
			if (dp[n][i] > ans) {
				ans = dp[n][i];
				index = i;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		int cur = n;
		int totalW = index;
		while (cur > 0) {
			if (dp[cur][totalW] != dp[cur - 1][totalW]) {
				list.add(cur);
				totalW -= obj[cur][0];
			}
			cur--;
		}
		int count = list.size();
		str.append(count);
		str.append("\n");
		if (count > 0) {
			str.append(list.get(count - 1) - 1);
			for (int j = count - 2; j >= 0; --j) {
				str.append(" " + (list.get(j) - 1));
			}
			str.append("\n");
		} else {
			str.append("\n");
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in), 1 << 14);
	static StringTokenizer tokenizer = new StringTokenizer("");

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			String n = reader.readLine();
			if (n == null) {
				return null;
			}
			tokenizer = new StringTokenizer(n);

		}
		return tokenizer.nextToken();
	}

	static String nextLine() throws IOException {
		return reader.readLine();
	}

	static Integer nextInt() throws IOException {
		String next = next();
		if (next == null) {
			return null;
		}
		return Integer.parseInt(next);
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static Double nextDouble() throws IOException {
		String next = next();
		if (next == null) {
			return null;
		}
		return Double.parseDouble(next);
	}
}
