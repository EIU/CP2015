import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Grass {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		do {
			solve();
		} while (ptrbuf + 4 < lenbuf);
	}

	static void solve() {
		int n = ni();
		int l = ni();
		int w = ni();
		double hw2 = w * w / 4.0;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			int x = ni();
			int r = ni();
			if (r <= w / 2.0) {
				continue;
			}
			double edge = Math.sqrt(r * r - hw2);
			if (x - edge >= l || x + edge <= 0) {
				continue;
			}
			edges.add(new Edge(x - edge, x + edge));
		}
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				return Double.compare(arg0.right, arg1.right);
			}
		});

		ArrayList<Edge> checkedEdges = new ArrayList<Edge>();
		Edge root = new Edge(0, 0);
		root.value = 0;
		checkedEdges.add(root);
		int minValue = n + 1;
		for (Edge edge : edges) {
			for (int last = checkedEdges.size() - 1; last >= 0; last--) {
				Edge checkedEdge = checkedEdges.get(last);
				if (checkedEdge.right < edge.left) {
					break;
				} else {
					edge.value = Math.min(checkedEdge.value + 1, edge.value);
				}
			}
			checkedEdges.add(edge);
			if (edge.right >= l) {
				minValue = Math.min(minValue, edge.value);
			}
		}
		System.out.println(minValue == n + 1 ? -1 : minValue);
	}

	static class Edge {
		public double left;
		public double right;
		public int value = 1000000;

		public Edge(double l, double r) {
			left = l;
			right = r;
		}
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 20];
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
