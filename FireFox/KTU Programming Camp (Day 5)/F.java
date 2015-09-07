import java.io.*;
import java.util.*;

public class Main {
	static boolean isMultiTest = false;

	public static void main(String[] args) {
		OutputStream outputStream = System.out;
		InputStream inputStream = System.in;
		InputFast in = new InputFast();
		// Input in = new Input(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		if (isMultiTest) {
			int testNumber = in.nextInt();
			for (int i = 1; i <= testNumber; i++) {
				solver.solve(i, in, out);
			}
		} else {
			solver.solve(1, in, out);
		}
		out.close();
	}
}

class Task {
	final static int MOD = 1000000009;
	final int LIM = 1000001;
	int[] matching;

	public void solve(int testNumber, InputFast in, PrintWriter out) {

		int n = in.nextInt();
		int[][] a = new int[n][n];
		TreeMap<Integer, Integer> id = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> val = new TreeMap<Integer, Integer>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = in.nextInt();
				if (!id.containsKey(a[i][j])) {
					val.put(count, a[i][j]);
					id.put(a[i][j], count++);

				}
			}
		}

		int n1 = (n << 1) - 1;
		int n2 = id.size();

		List<Integer>[] g = new List[n1];
		for (int i = 0; i < n1; i++)
			g[i] = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int valId = id.get(a[i][j]);
				int digId = -(i - j) + n - 1;
				g[digId].add(valId);
			}
		}
		int max = maxMatching(g, n2);
		int[] ans = new int[n1];
		if (max == n1) {
			out.print("YES");
			for (int i = 0; i < n2; i++) {
				if (matching[i] != -1)
					ans[matching[i]] = val.get(i);
			}
			for (int i = 0; i < n1; i++)
				out.print(" " + ans[i]);
			out.println();
		} else {
			out.println("NO");
		}

	}

	public int maxMatching(List<Integer>[] graph, int n2) {
		int n1 = graph.length;
		matching = new int[n2];
		Arrays.fill(matching, -1);
		int matches = 0;
		for (int u = 0; u < n1; u++) {
			if (findPath(graph, u, matching, new boolean[n1]))
				++matches;
		}
		return matches;
	}

	boolean findPath(List<Integer>[] graph, int u1, int[] matching,
			boolean[] vis) {
		vis[u1] = true;
		for (int v : graph[u1]) {
			int u2 = matching[v];
			if (u2 == -1 || !vis[u2] && findPath(graph, u2, matching, vis)) {
				matching[v] = u1;
				return true;
			}
		}
		return false;
	}
}

class InputFast {
	static InputStream is = System.in;
	static private byte[] buffer = new byte[1024];
	static private int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(buffer);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return buffer[ptrbuf++];
	}

	private boolean isSpace(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int read() {
		int b;
		while ((b = readByte()) != -1 && isSpace(b))
			;
		return b;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public char nextChar() {
		return (char) read();
	}

	public String next() {
		int b = read();
		StringBuilder sb = new StringBuilder();
		while (!(isSpace(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public char[] next(int n) {
		char[] buf = new char[n];
		int b = read(), p = 0;
		while (p < n && !(isSpace(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	public int nextInt() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	public long nextLong() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

}

class Input {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public Input(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
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
		String s = "";
		try {
			s = reader.readLine();
		} catch (IOException e) {
			try {
				throw new IOException();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return s;
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}