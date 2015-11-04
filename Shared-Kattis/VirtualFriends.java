import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class VirtualFriends {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		TreeMap<String, Node> map = new TreeMap<String, Node>();
		int N = ni();
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String name1 = ns();
			Node node1 = map.get(name1);
			if (node1 == null) {
				node1 = new Node(name1);
				map.put(name1, node1);
			}
			String name2 = ns();
			Node node2 = map.get(name2);
			if (node2 == null) {
				node2 = new Node(name2);
				map.put(name2, node2);
			}

			Node root1 = node1.getRoot();
			Node root2 = node2.getRoot();

			if (root1 != root2) {
				root1.parent = root2;
				root2.count += root1.count;
			}
			bf.append(root2.count + "\n");
		}
		System.out.println(bf);
	}

	static class Node {
		public String Name;
		public Node parent;
		public int count = 1;

		public Node(String name) {
			this.Name = name;
		}

		public Node getRoot() {
			if (parent == null) {
				return this;
			}

			Node root = this;
			while (root.parent != null) {
				root = root.parent;
			}
			return this.parent = root;
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
}
