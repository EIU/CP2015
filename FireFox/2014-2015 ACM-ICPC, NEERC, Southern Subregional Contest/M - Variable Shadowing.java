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
		Stack<Scope> scopes = new Stack<>();
		Scope currentScope = new Scope();
		String war = ": warning: shadowed declaration of ";
		String left = ", the shadowed position is ";
		Scope pre = null;
		for (int i = 0; i < n; ++i) {
			char[] s = in.nextLine().toCharArray();
			int length = s.length;
			for (int k = 0; k < length; ++k) {
				char c = s[k];
				if (c == ' ') {
					continue;
				}

				if (c == '{') {
					scopes.add(currentScope);
					pre = currentScope;
					currentScope = new Scope();
					for (int j = 0; j < 26; ++j) {
						currentScope.arr[j] = pre.arr[j];
					}
					continue;
				} else if (c == '}') {
					currentScope = scopes.pop();
					continue;
				} else if (scopes.isEmpty()) {
					currentScope.arr[c - 'a'] = new P(i + 1, k + 1);

					continue;
				}

				P index = scopes.peek().arr[c - 'a'];
				if (index != null) {
					out.print((i + 1) + ":" + (k + 1));

					out.print(war);
					out.print(c);
					out.print(left);
					out.println(index.r + ":" + index.c);
				}
				currentScope.arr[c - 'a'] = new P(i + 1, k + 1);

			}

		}

	}

	static class P {
		public int r;
		public int c;

		public P(int a, int b) {
			this.r = a;
			this.c = b;
		}
	}

	static class Scope {
		public P[] arr;

		public Scope() {
			arr = new P[26];
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
