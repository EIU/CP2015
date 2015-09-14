import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
		int index = 0;
		// while (true) {
		// int n = 100000;
		// StringBuilder str = new StringBuilder();
		// // InputStream stream = ByteArrayInputStream() ;
		// str.append(n + " ");
		// for (int i = 0; i < n; ++i) {
		// str.append(nextInt() % 1000 + " " + nextInt() % 1000 + " ");
		// }
		// str.append("\n");
		// InputStream stream = new ByteArrayInputStream(str.toString()
		// .getBytes());
		// in = new InputReader(stream);
		// long left = solver.solve(1, in, out);
		// // stream = new ByteArrayInputStream(str.toString().getBytes());
		// // in = new InputReader(stream);
		//
		// // long right = solver.naive(1, in, out);
		// // if (left != right) {
		// // out.print(str.toString());
		// // break;
		// // }
		// break;
		//
		// // out.println(index++);
		// }
		out.println(solver.solve(1, in, out));
		// out.println(solver.solve(1, in, out));
		out.close();
	}

	static int nextInt() {
		return Math.abs(new Random().nextInt());
	}

	static class Task {
		int[] par;

		public long solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			long ans = 0;
			int[][] points = new int[n][3];
			TreeSet<NO>[] set = new TreeSet[1000];
			HashSet<Integer>[] hashSet = new HashSet[1000];
			for (int i = 0; i < 1000; ++i) {
				set[i] = new TreeSet<>();
				hashSet[i] = new HashSet<Integer>();
			}
			for (int i = 0; i < n; ++i) {
				points[i][0] = in.nextInt();
				points[i][1] = in.nextInt();
				points[i][2] = i;
				if (hashSet[points[i][0]].contains(points[i][1])) {
					i--;
					n--;
				} else {
					set[points[i][0]].add(new NO(i, points[i][1]));
					hashSet[points[i][0]].add(points[i][1]);
				}

			}
			// System.out.println(System.currentTimeMillis());
			List<Edge> edges = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				int curX = points[i][0];
				int curY = points[i][1];
				int atract = curX - curY;
				int plus = curX + curY;
				int[] p1 = new int[] { -1, 0, 0 };
				int[] p2 = new int[] { -1, 0, 0 };
				int[] p3 = new int[] { -1, 0, 0 };
				int[] p4 = new int[] { -1, 0, 0 };

				NO P = set[curX].lower(new NO(-1, curY));
				if (P != null) {
					p1 = new int[] { curX, P.Y, P.index };
				}

				int max = 10000;
				for (int j = curX + 1; j - curX < max && j < 1000; ++j) {
					P = set[j].lower(new NO(-1, plus - j));
					if (P != null) {
						if (p1[0] == -1
								|| dis(p1, points[i]) > dis(points[i], P, j)) {
							p1[0] = j;
							p1[1] = P.Y;
							p1[2] = P.index;
						}
					}

					P = set[j].lower(new NO(-1, curY));
					if (P != null && P.Y >= plus - j) {
						if (p2[0] == -1
								|| dis(p2, points[i]) > dis(points[i], P, j)) {
							p2[0] = j;
							p2[1] = P.Y;
							p2[2] = P.index;
						}
					}

					P = set[j].higher(new NO(-1, curY - 1));
					if (P != null && P.Y < -atract + j) {
						if (p3[0] == -1
								|| dis(p3, points[i]) > dis(points[i], P, j)) {
							p3[0] = j;
							p3[1] = P.Y;
							p3[2] = P.index;
						}
					}

					P = set[j].higher(new NO(-1, -atract + j - 1));
					if (P != null) {
						if (p4[0] == -1
								|| dis(p4, points[i]) > dis(points[i], P, j)) {
							p4[0] = j;
							p4[1] = P.Y;
							p4[2] = P.index;
						}
					}

					if (p1[0] == -1 || p2[0] == -1 || p3[0] == -1
							|| p4[0] == -1) {
						max = 2000;
					} else {
						max = dis(points[i], p1);
						max = Math.max(dis(points[i], p2), max);
						max = Math.max(dis(points[i], p4), max);
						max = Math.max(dis(points[i], p3), max);
					}
				}

				if (p1[0] != -1) {
					edges.add(new Edge(points[i][2], p1[2], dis(points[i], p1)));
				}

				if (p2[0] != -1) {
					edges.add(new Edge(points[i][2], p2[2], dis(points[i], p2)));
				}
				if (p3[0] != -1) {
					edges.add(new Edge(points[i][2], p3[2], dis(points[i], p3)));
				}
				if (p4[0] != -1) {
					edges.add(new Edge(points[i][2], p4[2], dis(points[i], p4)));
				}
			}
			// System.out.println(System.currentTimeMillis());
			createSets(n);
			return MinimumSpanningTree(edges);
		}

		private int dis(int[] p1, NO p, int j) {
			return Math.abs(p1[0] - j) + Math.abs(p1[1] - p.Y);
		}

		private int dis(int[] p1, int[] p2) {
			return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
		}

		public long naive(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			int[] x = new int[n];
			int[] y = new int[n];
			for (int i = 0; i < n; ++i) {
				x[i] = in.nextInt();
				y[i] = in.nextInt();
			}
			createSets(n);
			List<Edge> edges = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					edges.add(new Edge(i, j, Math.abs(x[i] - x[j])
							+ Math.abs(y[i] - y[j])));
				}
			}
			return MinimumSpanningTree(edges);
		}

		public long MinimumSpanningTree(List<Edge> g) {
			Collections.sort(g);
			long res = 0;
			int size = g.size();
			int need = par.length;
			int total = 1;
			for (int i = 0; i < size; i++) {
				Edge cur = g.get(i);
				if (!isSameSet(cur.from, cur.to)) {
					res += cur.c;
					unite(cur.from, cur.to);
					total++;
					if (total == need) {
						break;
					}
				}
			}
			return res;
		}

		public int[] createSets(int size) {
			par = new int[size];
			for (int i = 0; i < size; i++)
				par[i] = i;
			return par;
		}

		public boolean isSameSet(int a, int b) {
			return root(a) == root(b);
		}

		public int root(int x) {
			return x == par[x] ? x : (par[x] = root(par[x]));
		}

		public void unite(int a, int b) {
			a = root(a);
			b = root(b);
			if (a != b)
				par[a] = b;
		}

		static class NO implements Comparable<NO> {
			public int index;
			public int Y;

			@Override
			public int compareTo(NO arg0) {
				// TODO Auto-generated method stub
				return this.Y - arg0.Y;

			}

			public NO(int index, int y) {
				this.index = index;
				this.Y = y;

			}
		}

		static class Edge implements Comparable<Edge> {
			public int from;
			public int to;
			public int c;

			public Edge(int f, int t, int c) {
				from = f;
				to = t;
				this.c = c;
			}

			@Override
			public int compareTo(Edge e) {
				return this.c - e.c;
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
