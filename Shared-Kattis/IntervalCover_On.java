import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class IntervalCover_On {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		do {
			solve();
		} while (ptrbuf + 4 < lenbuf);
	}

	static void solve() {
		double start = nd();
		double end = nd();
		int n = ni();

		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			double left = nd();
			double right = nd();
			if (right >= start && left <= end) {
				edges.add(new Edge(i, left, right));
			}
		}

		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				return Double.compare(arg0.right, arg1.right);
			}
		});

		int validIndex = -1;

		for (Edge edge : edges) {
			if (edge.right < start || edge.left > end || edge.left > (validIndex >= 0 ? edges.get(validIndex).right : start)) {
				continue;
			}

			while (validIndex == 0 && edge.left <= start || validIndex > 0 && edge.left <= edges.get(validIndex - 1).right) {
				validIndex--;
			}

			validIndex++;
			edges.set(validIndex, edge);
		}

		if (validIndex >= 0 && edges.get(validIndex).right >= end) {
			while (validIndex > 0 && edges.get(validIndex - 1).right >= end) {
				validIndex--;
			}
			StringBuffer bf = new StringBuffer((validIndex + 1) + "\n");
			for (; validIndex >= 0; validIndex--) {
				bf.append(edges.get(validIndex).id + " ");
			}
			System.out.println(bf.toString());
		} else {
			System.out.println("impossible");
		}
	}

	static class Edge {
		public int id;
		public double left;
		public double right;

		public Edge(int id, double left, double right) {
			this.id = id;
			this.left = left;
			this.right = right;
		}
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 24];
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

	static double nd() {
		return Double.parseDouble(ns());
	}
}
