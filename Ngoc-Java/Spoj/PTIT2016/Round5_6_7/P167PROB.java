import java.io.*;
import java.util.*;

class P167PROB {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		int M = ni();
		char[][] words = new char[M][];
		for (int i = 0; i < M; i++) {
			words[i] = ns().toCharArray();
		}

		StringBuilder outBf = new StringBuilder();
		int N = 0;
		while ((N = ni()) > 0) {
			char[][] matrix = nm(N, N);
			Node[][] nodes = new Node[N][N];

			Node root = new Node('^');

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					root.nodes.add(nodes[i][j] = new Node(matrix[i][j]));
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i > 0 && j > 0)
						nodes[i][j].nodes.add(nodes[i - 1][j - 1]);
					if (i > 0)
						nodes[i][j].nodes.add(nodes[i - 1][j]);
					if (j > 0)
						nodes[i][j].nodes.add(nodes[i][j - 1]);
					if (i > 0 && j < N - 1)
						nodes[i][j].nodes.add(nodes[i - 1][j + 1]);
					if (i < N - 1 && j > 0)
						nodes[i][j].nodes.add(nodes[i + 1][j - 1]);
					if (i < N - 1 && j < N - 1)
						nodes[i][j].nodes.add(nodes[i + 1][j + 1]);
					if (i < N - 1)
						nodes[i][j].nodes.add(nodes[i + 1][j]);
					if (j < N - 1)
						nodes[i][j].nodes.add(nodes[i][j + 1]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (nodes[i][j].letter == 'q') {
						Node uNode = new Node('u');
						uNode.nodes = nodes[i][j].nodes;
						nodes[i][j].nodes = new ArrayList<Node>();
						nodes[i][j].nodes.add(uNode);
					}
				}
			}

			List<String> result = new ArrayList<String>();
			for (char[] word : words) {
				if (find(root, word, 0)) {
					result.add(new String(word));
				}
			}

			result.sort((s1, s2) -> s1.compareTo(s2));
			for (String word : result) {
				outBf.append(word + "\n");
			}
			outBf.append("-\n");
		}
		System.out.print(outBf);
	}

	static public boolean find(Node node, char[] word, int index) {
		if (index == word.length)
			return word[index - 1] != 'q';
		node.visiting = true;
		for (Node child : node.nodes) {
			if (child.letter == word[index] && !child.visiting) {
				boolean subResult = find(child, word, index + 1);
				if (subResult) {
					node.visiting = false;
					return true;
				}
			}
		}
		node.visiting = false;
		return false;
	}

	static class Node {
		public char letter;
		public boolean visiting;
		public List<Node> nodes = new ArrayList<Node>();

		public Node(char letter) {
			this.letter = letter;
		}
	}

	/* *
	 */

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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
}
