import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task {

		List<Edge>[] graph;
		boolean[] visited;

		int[] shortest;

		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			int m = in.nextInt();
			graph = new List[n];
			visited = new boolean[n];
			for (int i = 0; i < n; ++i) {
				graph[i] = new ArrayList<Edge>();
			}
			for (int i = 0; i < m; ++i) {
				int u = in.nextInt();
				int v = in.nextInt();
				int c = in.nextInt();
				graph[u].add(new Edge(v, c));
			}
			int s = in.nextInt();
			int end = in.nextInt();
			long[] count = new long[n];
			count[s] = 1;
			shortest = new int[n];

			visited[s] = true;
			Arrays.fill(shortest, Integer.MAX_VALUE);
			shortest[s] = 0;
			PriorityQueue<Edge> edges = new PriorityQueue<>();
			// int[] pre = new int[n];
			for (Edge es : graph[s]) {
				edges.add(es);
				shortest[es.t] = es.cost;
				// pre[es.t] = s;
				count[es.t] = 1;
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
					int temp = es.cost + shortest[e.t];
					if (temp < shortest[es.t]) {
						shortest[es.t] = temp;
						count[es.t] = count[e.t];
					} else if(temp==shortest[es.t]){
						count[es.t] += count[e.t];
					}
					edges.add(new Edge(es.t, es.cost + shortest[es.t]));
				}
			}

			if (shortest[end] == Integer.MAX_VALUE) {
				out.println(0);
			} else {
				out.println(count[end]);
			}

		}

		static class Edge implements Comparable<Edge> {
			int t, cost;

			public Edge(int t, int cost) {
				this.t = t;
				this.cost = cost;
			}

			@Override
			public int compareTo(Edge e) {
				return this.cost - e.cost;
			}
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

	}
}
