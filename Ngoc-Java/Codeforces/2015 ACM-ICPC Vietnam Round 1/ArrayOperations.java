import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ArrayOperations {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) throws Exception {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		int T = ni();
		for (int t = 1; t <= T; t++) {
			solve(t);
		}
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static void solve(int t) {
		int N = ni();
		int Q = ni();

		Node[] values = new Node[N];
		Node[] questions = new Node[Q];
		Node[] allValues = new Node[N + Q];

		for (int i = 0; i < N; i++) {
			allValues[i] = values[i] = new Node(ni(), I);
		}

		for (int i = 0; i < Q; i++) {
			String op = ns();
			Node node;
			if (op == "GET") {
				node = new Node(G, ni(), ni(), ni());
			} else {
				node = new Node(S, ni(), ni());
			}
			allValues[i + N] = questions[i] = node;
		}

		Arrays.sort(allValues, new Comparator<Node>() {
			@Override
			public int compare(Node arg0, Node arg1) {
				return arg0.value - arg1.value;
			}
		});

		int order = 0;
		for (int i = 0; i < N + Q; i++) {
			if (i == 0 || allValues[i - 1].value != allValues[i].value) {
				order++;
			}
			allValues[i].order = order;
		}

		MAXBIT = order;
		sumBIT = new int[MAXBIT + 1];

		for (Node node : values) {
			set(node.order, 1);
		}

		StringBuilder bf = new StringBuilder("Case #" + t + "\n");
		for (Node question : questions) {
			if (question.operation == S) {
				set(question.order, 1);
			} else {
				bf.append(get(question.order));
			}
		}
	}

	static int MAXBIT;
	static int[] sumBIT;

	static void set(int i, int value) {
		for (; i <= MAXBIT; i += (i & -i)) {
			sumBIT[i] += value;
		}
	}

	static int get(int i) {
		int sum = 0;
		for (; i > 0; i -= (i & -i)) {
			sum += sumBIT[i];
		}
		return sum;
	}

	static int get(int l, int r) {
		if (r > MAXBIT) {
			r = MAXBIT;
		}
		int sum = get(r);
		if (l > 0) {
			sum -= get(l);
		}
		return sum;
	}

	final static int I = 0;
	final static int G = 1;
	final static int S = 2;

	static class Node {
		public int value;
		public int operation;

		public int index;
		public int left;
		public int right;

		public int order;

		public Node(int operation, int value) {
			this.operation = operation;
			this.value = value;
		}

		public Node(int operation, int index, int value) {
			this.operation = operation;
			this.index = index;
			this.value = value;
		}

		public Node(int operation, int left, int right, int value) {
			this.operation = operation;
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b));
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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

	static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
