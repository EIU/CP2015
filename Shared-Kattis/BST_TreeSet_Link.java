import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BST_TreeSet_Link {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		solve();
	}

	static final int LEAF = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;

	static void solve() {
		int N = ni();
		TreeSet<Node> treeSet = new TreeSet<Node>();
		Node first = new Node(0);
		first.height = -1;
		treeSet.add(first);

		long C = 0;
		StringBuilder outString = new StringBuilder();
		for (int i = 0; i < N; i++) {
			Node node = new Node(ni());
			Node lower = treeSet.lower(node);
			Node higher = lower.higher;
			treeSet.add(node);
			lower.higher = node;
			node.higher = higher;
			if ((lower.status & RIGHT) == 0) {
				lower.status |= RIGHT;
				C += (node.height = lower.height + 1);
			} else {
				higher.status |= LEFT;
				C += (node.height = higher.height + 1);
			}

			outString.append(C + "\n");
		}
		System.out.print(outString.toString());
	}

	static class Node implements Comparable<Node> {
		public int value;
		public int status;
		public int height = 0;
		public Node higher;

		@Override
		public int compareTo(Node arg0) {
			return this.value - arg0.value;
		}

		public Node(int value) {
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
}
