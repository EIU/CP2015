import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class SS {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskA solver = new TaskA();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskA {
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			long a = in.nextInt();
			long p = in.nextLong();
			long q = in.nextLong();

			long max = (p + q - 1) / q;
			if (max > a || p < q) {
				out.println("IMPOSSIBLE");
				return;
			}

			for (long i = q; i <= a; ++i) {
				if (i % q != 0) {
					continue;
				}
				long tempP = p * (i / q);
				long start = a - i + 1;
				long sum = (a + start) * (i) / 2;
				if (sum >= tempP && tempP >= (1 + i) * i / 2) {
					long diff = sum - tempP;
					long[] result = new long[(int) i];
					result[0] = start;
					for (int j = 1; j < i; ++j) {
						result[j] = result[j - 1] + 1;
					}

					for (int j = 0; j < i; ++j) {
						long m = Math.min(diff, result[j] - j - 1);
						result[j] -= m;
						diff -= m;
					}

					out.print(result[0]);
					for (int j = 1; j < i; ++j) {
						out.print(" " + result[j]);
					}
					out.println();
					return;
				}
			}

			out.println("IMPOSSIBLE");

		}

		public long gcd(long a, long b) {
			return a % b == 0 ? b : gcd(b, a % b);
		}

		boolean check(int a, int b, int c, int d) {
			boolean ok = a * a + b * b >= c * c + d * d;
			ok &= a * b >= c * d;
			ok &= a + b >= c + d;
			ok &= Math.min(a, b) >= Math.min(c, d);
			return ok;
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

		public long nextLong() {
			return Long.parseLong(next());
		}

	}
}