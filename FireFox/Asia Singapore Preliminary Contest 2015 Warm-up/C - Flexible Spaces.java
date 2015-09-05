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
		HashSet<Integer> exist = new HashSet<>();
		int w = in.nextInt();
		int index = 0;
		int[] ans = new int[w + 1];
		int p = in.nextInt();
		int[] pa = new int[p + 3];
		pa[0] = 0;
		for (int i = 1; i <= p; ++i) {
			pa[i] = in.nextInt();
		}
		pa[p + 1] = w;
		for (int i = 0; i <= p + 1; ++i) {
			for (int j = i + 1; j <= p + 1; ++j) {
				int a = pa[j] - pa[i];
				if (!exist.contains(a)) {
					exist.add(a);
					ans[index++] = a;
				}
			}
		}
		Arrays.sort(ans, 0, index);
		out.print(ans[0]);

		for (int i = 1; i < index; ++i) {
			out.print(" " + ans[i]);
		}
		out.println();

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
