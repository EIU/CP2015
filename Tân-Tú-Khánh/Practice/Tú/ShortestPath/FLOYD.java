import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.io.IOException;

public class FLOYD {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		List<Edge>[] graph;
		boolean[] visited;
		long[] shortest;
		out = new PrintWriter(System.out);
		is = System.in;
		int n = ni();
		int m = ni();
		int k = ni();
		graph = new List[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i < n + 1; ++i) {
			graph[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < m; ++i) {
			int u = ni();
			int v = ni();
			int c = ni();
			graph[u].add(new Edge(v, c));
			graph[v].add(new Edge(u, c));
		}
		shortest = new long[n + 1];
		for (int i = 0; i < k; i++) {
			int c = ni();
			int u = ni();
			int v = ni();
			if (c == 1) {
				System.out
						.println(shortestpath(u, v, shortest, graph, visited, n));
			} else {
				long[] res = shortest(u, v, shortest, graph, visited, n);
				System.out.println(res[v]);
			}
		}
	}

	private static StringBuilder shortestpath(int u, int v, long[] shortest,
			List<Edge>[] graph, boolean[] visited, int n) {
		visited = new boolean[n + 1];
		visited[1] = true;
		Arrays.fill(shortest, Long.MAX_VALUE);
		shortest[u] = 0;
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		int[] prev = new int[n + 1];
		for (Edge es : graph[1]) {
			edges.add(es);
			shortest[es.t] = es.cost;
			prev[es.t] = 1;
		}
		while (!edges.isEmpty()) {

			Edge e = edges.poll();
			while (visited[e.t] && !edges.isEmpty()) {
				e = edges.poll();
			}
			if (visited[e.t]) {
				break;
			}
			visited[e.t] = true;

			for (Edge es : graph[e.t]) {
				long temp = es.cost + shortest[e.t];
				if (temp < shortest[es.t]) {
					shortest[es.t] = temp;
					prev[es.t] = e.t;
				}
				edges.add(new Edge(es.t, es.cost + shortest[es.t]));
			}
		}
		StringBuilder res = new StringBuilder();
		ArrayList<Integer> link = new ArrayList<>();
		int j = n;
		while (j != 1) {
			if (prev[j] <= 1) {
				break;
			} else {
				link.add(prev[j]);
				j = prev[j];
			}
		}
		res.append((2+link.size()) + " ");
		res.append(1 + " ");
		for (int l = link.size() - 1; l >= 0; l--) {
			res.append(link.get(l) + " ");
		}
		res.append(n);
		return res;
	}

	private static long[] shortest(int u, int v, long[] shortest,
			List<Edge>[] graph, boolean[] visited, int n) {
		visited = new boolean[n + 1];
		visited[1] = true;
		Arrays.fill(shortest, Long.MAX_VALUE);
		shortest[u] = 0;
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		int[] prev = new int[n + 1];
		for (Edge es : graph[1]) {
			edges.add(es);
			shortest[es.t] = es.cost;
			prev[es.t] = 1;
		}
		while (!edges.isEmpty()) {

			Edge e = edges.poll();
			while (visited[e.t] && !edges.isEmpty()) {
				e = edges.poll();
			}
			if (visited[e.t]) {
				break;
			}
			visited[e.t] = true;

			for (Edge es : graph[e.t]) {
				long temp = es.cost + shortest[e.t];
				if (temp < shortest[es.t]) {
					shortest[es.t] = temp;
					prev[es.t] = e.t;
				}
				edges.add(new Edge(es.t, es.cost + shortest[es.t]));
			}
		}
		return shortest;
	}

	static class Edge implements Comparable<Edge> {
		int t;
		long cost;

		public Edge(int t, long l) {
			this.t = t;
			this.cost = l;
		}

		@Override
		public int compareTo(Edge e) {
			return (int) (this.cost - e.cost);
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}