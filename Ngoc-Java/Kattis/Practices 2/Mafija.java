import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Mafija {

	public static void main(String[] args) throws Exception {
		int N = ni();

		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 1; i <= N; i++) {
			Node next = nodes[ni()];
			nodes[i].setNext(next);
			next.addDependency(nodes[i]);
		}

		int result = 0;
		for (Node node : nodes) {
			if (!node.visited) {
				node.visit();
				if (node.next.visited) {
					result += Math.max(node.maxSelected, node.maxUnselected);
				}
			}
		}

		System.out.println(result);
	}

	static class Node {
		public int id;
		public Node next;
		public List<Node> dependencies = new ArrayList<Node>();
		public boolean visited = false;
		public int maxSelected = 0;
		public int maxUnselected = 0;

		public Node(int id) {
			this.id = id;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public void addDependency(Node dependency) {
			dependencies.add(dependency);
		}

		public void visit() {
			if (visited) {
				return;
			}
			visited = true;

			for (Node dependency : dependencies) {
				if (!dependency.visited) {
					dependency.visit();
				}
				maxSelected += dependency.maxUnselected;
				maxUnselected += Math.max(dependency.maxSelected, dependency.maxUnselected);
			}
		}
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static InputStream is = System.in;
	static byte[] inbuf = new byte[1 << 16];
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

	static boolean hasNext() {
		return ptrbuf + 3 < lenbuf;
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
