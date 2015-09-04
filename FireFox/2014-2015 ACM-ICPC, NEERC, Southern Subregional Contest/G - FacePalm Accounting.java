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
		int min = Integer.MAX_VALUE;
		L[] v = new L[n];
		int s = 0;
		for (int i = 0; i < n; ++i) {
			v[i] = new L();
			v[i].a = in.nextInt();
			v[i].id = i;
			s += v[i].a;
			min = Math.min(min, v[i].a);
		}
		int sum = 0;
		Stack<L> w = new Stack<>();
		for (int i = 0; i < k - 1; i++) {
			w.add(v[i]);
			sum += v[i].a;
		}
		int cur = k - 1;

		for (int i = k - 1; i < n; i++) {
			w.add(v[i]);
			sum += v[i].a;
			if (sum >= 0) {
				int need = sum + 1;
				while (need > 0) {
					L t = w.pop();
					int can = t.a - min;
					if (need - can <= 0) {
						v[t.id].a -= need;
						need = 0;
						w.add(t);
					} else {
						need -= can;
						v[t.id].a -= can;
					}
				}
				sum = -1;
			}
			sum -= v[i - k + 1].a;
		}
		int c = 0;
		for (int i = 0; i < n; i++) {
			c += v[i].a;
		}
		out.println(Math.abs(c - s));
		for (int i = 0; i < n; i++) {
			out.print(v[i].a + " ");
		}
	}

	class L {
		int a;
		int id;
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
