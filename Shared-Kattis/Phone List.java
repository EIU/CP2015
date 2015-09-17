import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int t = nextInt();
		while (t-- > 0) {
			solve();
		}
	}

	private static void solve() throws IOException {
		int n = nextInt();
		String[] phones = new String[n];
		for (int i = 0; i < n; ++i) {
			phones[i] = next();
		}

		Arrays.sort(phones);
		for (int i = 1; i < n; ++i) {
			int l1 = phones[i - 1].length();
			int l2 = phones[i].length();
			if (l1 > l2) {
				continue;
			}

			boolean is = true;
			for (int j = 0; j < l1; ++j) {
				if (phones[i - 1].charAt(j) != phones[i].charAt(j)) {
					is = false;
					break;
				}
			}
			if (is) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
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
		return Double.parseDouble(next());
	}
}
