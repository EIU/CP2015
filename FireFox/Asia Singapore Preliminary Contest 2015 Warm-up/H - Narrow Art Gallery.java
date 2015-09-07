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
		int n, k;
		while (true) {
			n = in.nextInt();
			k = in.nextInt();
			if (n == 0 && k == 0) {
				break;
			}
			long[][] a = new long[n][2];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 2; j++) {
					a[i][j] = in.nextLong();
				}
			}
			long[][][] f = new long[n][3][k + 2];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					Arrays.fill(f[i][j], -100 * 200 * 2);
				}
			}
			final int LEFT = 0;
			final int RIGHT = 1;
			final int NONE = 2;
			// none
			f[0][NONE][0] = a[0][0] + a[0][1];
			// right
			f[0][RIGHT][1] = a[0][LEFT];
			// left
			f[0][LEFT][1] = a[0][RIGHT];
			for (int i = 1; i < n; i++) {
				for (int lock = 0; lock <= k; lock++) {
					// lock left
					long best = Math.max(f[i - 1][LEFT][lock],
							f[i - 1][NONE][lock]) + a[i][RIGHT];
					f[i][LEFT][lock + 1] = Math.max(f[i][LEFT][lock + 1], best);
					// lock right
					best = Math
							.max(f[i - 1][RIGHT][lock], f[i - 1][NONE][lock])
							+ a[i][LEFT];
					f[i][RIGHT][lock + 1] = Math.max(f[i][RIGHT][lock + 1],
							best);
					// none
					best = Math
							.max(f[i - 1][LEFT][lock], f[i - 1][RIGHT][lock]);
					best = Math.max(best, f[i - 1][NONE][lock]);
					f[i][2][lock] = best + a[i][LEFT] + a[i][RIGHT];
				}
			}
			long res = Math.max(f[n - 1][LEFT][k], f[n - 1][RIGHT][k]);
			out.println(Math.max(res, f[n - 1][NONE][k]));
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

	public String nextLine() {

		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return "";
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}
}
