import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
		TaskA solver = new TaskA();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskA {
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int s = in.nextInt();
			while (s != 0) {
				int b = in.nextInt();
				int[] left = new int[s];
				int[] right = new int[s];
				for (int i = 0; i < s; i++) {
					left[i] = i - 1 >= 0 ? i - 1 : -1;
					right[i] = i + 1 < s ? i + 1 : -1;
				}
				for (int i = 0; i < b; i++) {
					int l = in.nextInt() - 1;
					int r = in.nextInt() - 1;
					out.print(left[l] == -1 ? "* " : (left[l] + 1) + " ");
					out.println(right[r] == -1 ? "*" : (right[r] + 1));
					if (right[r] != -1) {
						left[right[r]] = left[l];
					}
					if (left[l] != -1) {
						right[left[l]] = right[r];
					}
				}
				s = in.nextInt();
				out.println("-");
			}
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