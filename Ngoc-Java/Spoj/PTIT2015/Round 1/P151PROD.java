import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

class P151PROD {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {

		long s = System.currentTimeMillis();
		is = System.in;
		out = new PrintWriter(System.out);

		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}

		out.flush();
		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		int n = ni();
		int k = ni();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(ni(), i + 1);
		}
		for (int i = 0; i < n - 1; i++) {
			int u = ni() - 1;
			int v = ni() - 1;
			int w = ni();
			nodes[u].addEdge(nodes[v], w);
			nodes[v].addEdge(nodes[u], w);
		}

		long min = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			ArrayList<Long> ws = new ArrayList<Long>();
			traverse(null, nodes[i], ws);
			Collections.sort(ws);
			long totalWeight = 0;
			for (int j = 0; j < n - 1 - k; j++) {
				totalWeight += ws.get(j);
			}
			// System.out.println(totalWeight);
			min = Math.min(min, totalWeight);
		}
		System.out.println(min);
	}

	static long traverse(Node from, Node node, ArrayList<Long> ws) {
		int len = node.tos.size();
		long totalPop = node.population;
		for (int i = 0; i < len; i++) {
			Node toNode = node.tos.get(i);
			if (from != toNode) {
				long pop = traverse(node, toNode, ws);
				ws.add(pop * node.ws.get(i));
				totalPop += pop;
			}
		}
		return totalPop;
	}

	static class Node {
		public ArrayList<Node> tos = new ArrayList<Node>();
		public ArrayList<Integer> ws = new ArrayList<Integer>();
		public int population = 0;
		public int id;

		public Node(int pop, int id) {
			this.id = id;
			this.population = pop;
		}

		public void addEdge(Node node, int w) {
			tos.add(node);
			ws.add(w);
		}

		@Override
		public String toString() {
			return id + ": " + population;
		}

	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[4096];
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
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

	static long nl() {
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
