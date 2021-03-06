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
		int[] times = new int[] { 1, 2, 3, 
				1, 2, 3, 
				1, 2, 3,
				1, 2, 3,
				1, 2, 3,
				1, 2, 3, 4,
				1, 2, 3, 
				1, 2, 3, 4 };
		int n = in.nextInt();
		for (int i = 1; i <= n; i++) {
			int ans = 0;
			char[] s = in.nextLine().toCharArray();
			for (int j = 0; j < s.length; j++) {
				if (s[j] == ' ') {
					ans++;
				} else {
					ans += times[s[j] - 'a'];
				}
			}
			out.println("Case #" + i + ": " + ans);
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