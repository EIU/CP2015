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
	public void solve(int caseNum, InputReader in, PrintWriter out) {
		int n = in.nextInt();
		int k = in.nextInt();

		long total = 0;
		long[] sum = new long[n + 1];
		long[] bestLeft = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			long foo = in.nextInt();
			total += foo;
			sum[i] = foo;
			sum[i] += sum[i - 1];
			if (i - k >= 0) {
				long cur = sum[i] - sum[i - k];
				bestLeft[i] = Math.max(bestLeft[i - 1], cur);
			} else {
				bestLeft[i] = sum[i];
			}
		}
		if (2 * k >= n) {
			out.println(total);
			return;
		}
		long best = 0;
		long res = 0;
		for (int i = n; i >= 0; i--) {
			long cur;
			if (i + k > n) {
				cur = total - sum[i];
			} else {
				cur = sum[i + k] - sum[i];
			}
			best = Math.max(best, cur);
			res = Math.max(res, best + bestLeft[i]);
		}
		out.println(res);
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
