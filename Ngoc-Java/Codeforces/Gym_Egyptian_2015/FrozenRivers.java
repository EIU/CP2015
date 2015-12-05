import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FrozenRivers {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int N = ni();
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 2; i <= N; i++) {
			int parent = ni();
			nodes[parent].addChild(nodes[i]);
			nodes[i].ice = nl();
		}

		traverse(nodes[1], 0l, 0l);

		ArrayList<Double> times = new ArrayList<Double>();
		for (int i = 1; i <= N; i++) {
			if (nodes[i].children.size() == 0) {
				times.add((double) nodes[i].time);
			}
		}

		Collections.sort(times);

		int Q = ni();
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int count = Collections.binarySearch(times, 0.5D + nl());
			bf.append((-count - 1) + "\n");
		}
		System.out.print(bf);
	}

	static void traverse(Node node, long startTime, long fastUnit) {
		node.time = startTime + fastUnit + 2 * (node.ice - fastUnit);
		long smallest = Integer.MAX_VALUE;
		for (Node child : node.children) {
			if (child.ice < smallest) {
				smallest = child.ice;
			}
		}
		for (Node child : node.children) {
			traverse(child, node.time, smallest);
		}
	}

	static class Node {
		public int id;
		public long ice = 0;
		public long time = 0;

		public ArrayList<Node> children = new ArrayList<Node>();

		public Node(int id) {
			this.id = id;
		}

		public void addChild(Node node) {
			children.add(node);
		}
	}

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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