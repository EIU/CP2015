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
		int m = in.nextInt();
		while (n != 0 || m != 0) {
			PriorityQueue<T> q = new PriorityQueue<>();
			int[] arr = new int[2000001];
			for (int i = 0; i < n; ++i) {
				int s = in.nextInt();
				int e = in.nextInt();
				arr[s]++;
				arr[e]--;
			}

			for (int i = 0; i < m; ++i) {
				q.add(new T(in.nextInt(), in.nextInt(), in.nextInt()));
			}
			boolean ok = true;
			for (int i = 0; i < 1000000; ++i) {
				while (!q.isEmpty() && q.peek().start == i) {
					T t = q.poll();
					arr[t.start]++;
					if (t.end <= 2000000) {
						arr[t.end]--;
					}

					t.start += t.inter;
					t.end += t.inter;
					q.add(t);
				}
				if (i > 0) {
					arr[i] += arr[i - 1];
				}
				if (arr[i] > 1) {
					ok = false;
					break;
				}
			}
			System.out.println(ok ? "NO CONFLICT" : "CONFLICT");
			n = in.nextInt();
			m = in.nextInt();
		}
	}

	static class T implements Comparable<T> {
		public int start;
		public int end;
		public int inter;

		public T(int s, int e, int inter) {
			this.start = s;
			this.end = e;
			this.inter = inter;
		}

		@Override
		public int compareTo(T t) {
			return this.start - t.start;
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