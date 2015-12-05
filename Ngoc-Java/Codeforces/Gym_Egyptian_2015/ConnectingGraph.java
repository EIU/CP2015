import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ConnectingGraph {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int n = ni();
		int m = ni();

		Node[] nodes = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		StringBuilder bf = new StringBuilder();
		for (int i = 1; i <= m; i++) {
			int type = ni();
			int u = ni();
			int v = ni();
			if (type == 1) {
				Node rootU = nodes[u].getRoot();
				Node rootV = nodes[v].getRoot();
				if (rootU != rootV) {
					if (rootU.nChild < rootV.nChild) {
						Node temp = rootU;
						rootU = rootV;
						rootV = temp;
					}
					rootV.parent = rootU;
					rootV.connectId = i;
					rootU.nChild += rootV.nChild;
				}
			}
			else {
				Node nodeU = nodes[u];
				Node nodeV = nodes[v];
				int connectId = 0;
				while (nodeU != nodeV && nodeU != null && nodeV != null) {
					if (nodeU.connectId < nodeV.connectId) {
						connectId = nodeU.connectId;
						nodeU = nodeU.parent;
					} else {
						connectId = nodeV.connectId;
						nodeV = nodeV.parent;
					}
				}
				if (bf.length() != 0) {
					bf.append("\n");
				}
				bf.append(nodeU == nodeV ? connectId : -1);
			}
		}
		System.out.println(bf);
	}

	static int s_connectId = Integer.MAX_VALUE;

	static class Node {
		public int id;
		public Node parent;
		public int connectId;
		public int nChild = 1;

		public Node(int id) {
			this.id = id;
			this.connectId = s_connectId--;
		}

		public Node getRoot() {
			Node node = this;
			while (node.parent != null) {
				node = node.parent;
			}
			return node;
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
}