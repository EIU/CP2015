import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EntertainmentBox {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int k = ni();

		Edge[] edges = new Edge[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new Edge(ni(), ni());
		}

		Arrays.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				return arg0.start - arg1.start;
			}
		});

		TreeSet<Edge> ends = new TreeSet<Edge>();
		int result = 0;
		for (Edge edge : edges) {
			Edge first, last;
			while (ends.size() > 0 && (first = ends.first()).end <= edge.start) {
				ends.remove(first);
				result++;
			}
			if (ends.size() < k) {
				ends.add(edge);
			} else if ((last = ends.last()).end > edge.end) {
				ends.remove(last);
				ends.add(edge);
			}
		}

		System.out.println(result + ends.size());
	}

	public static int s_id;

	static class Edge implements Comparable<Edge> {

		public int id;
		public int start;
		public int end;

		public Edge(int start, int end) {
			this.id = s_id++;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Edge arg0) {
			int cmp = this.end - arg0.end;
			if (cmp != 0) {
				return cmp;
			}
			return this.id - arg0.id;
		}
	}

	/* ****************************************************************
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
