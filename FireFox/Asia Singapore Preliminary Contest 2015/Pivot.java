import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
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

	static class Task {
		static int maxn = 200000;
		int t[] = new int[200000];

		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			Integer[] arr = new Integer[n];
			Integer[] aa = new Integer[n];
			for (int i = 0; i < n; ++i) {
				arr[i] = in.nextInt();
				aa[i] = arr[i];
			}
			Arrays.sort(aa);
			for (int i = 0; i < n; ++i) {
				arr[i] = Arrays.binarySearch(aa, arr[i]);
			}
			int ans = 0;
			for (int i = 0; i < n; ++i) {
				if (i == 0) {
					if (arr[0] == 0) {
						ans++;
					}

				} else {
					if (arr[i] == i && sum(t, i - 1) == i) {
						ans++;
					}
				}
				add(t, arr[i], 1);
			}
			out.println(ans);

		}

		void add(int t[], int i, int value) {
			for (; i < maxn; i += (i + 1) & -(i + 1))
				t[i] += value;
		}

		int sum(int t[], int i) {
			int res = 0;
			for (; i >= 0; i -= (i + 1) & -(i + 1))
				res += t[i];
			return res;
		}
	}

	static class InputReader {
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

	}
}
