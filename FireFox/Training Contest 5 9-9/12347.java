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
	Integer[] arr = new Integer[100001];
	Integer[] tree = new Integer[100001];
	int root = 0;

	public void solve(int caseNum, InputReader in, PrintWriter out) {

		int cnt = 0;
		String s = null;
		while ((s = in.nextLine()) != null) {
			if (s.length() == 0) {
				break;
			}
			arr[cnt++] = Integer.parseInt(s);
		}

		for (int i = 0; i < cnt; ++i) {
			tree[i] = arr[i];
		}
		root = 0;
		Arrays.sort(arr, 0, cnt);
		dfs(out, 0, cnt - 1, root);
	}

	private void dfs(PrintWriter out, int i, int j, int root) {
		if (i > j) {
			return;
		} else if (i == j) {
			//System.out.println(tree[root]);
			out.println(tree[root]);
			return;
		}
		int rootValue = tree[root];
		int index = Arrays.binarySearch(arr, i, j + 1, rootValue);
		root++;
		dfs(out, i, index - 1, root);
		root += index - i;
		dfs(out, index + 1, j, root);
		out.println(rootValue);
		//System.out.println(rootValue);

	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 1 << 20);
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