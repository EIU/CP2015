import java.util.*;
import java.io.*;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		IO io = new IO(inputStream, outputStream);
		TaskA solver = new TaskA();
		solver.solve(io);
		io.close();
	}

	static class TaskA {
		final int LIM = 1000000;
		final int MAX = 0;
		final int SUM = 1;

		public void solve(IO io) {
			int nTest = io.nextInt();
			while (nTest-- > 0) {
				int n = io.nextInt();
				Rectangle[] r = new Rectangle[n];
				HashSet<Integer> ls = new HashSet<Integer>();
				for (int i = 0; i < n; i++) {
					int x1 = io.nextInt() + LIM;
					int y1 = io.nextInt() + LIM;
					int x2 = io.nextInt() + LIM;
					int y2 = io.nextInt() + LIM;
					int v = io.nextInt();

					r[i] = new Rectangle(x1, y1, x2, y2, v);
					ls.add(x1);
					ls.add(x2);
				}
				int len = ls.size();
				Integer lineSweep[] = new Integer[len];
				ls.toArray(lineSweep);

				HashMap<Integer, ArrayList<Segment>> in = new HashMap<Integer, ArrayList<Segment>>();
				HashMap<Integer, ArrayList<Segment>> out = new HashMap<Integer, ArrayList<Segment>>();

				Arrays.sort(r);
				Arrays.sort(lineSweep);
				ArrayList<Segment> is;
				ArrayList<Segment> os;

				for (int i = 0; i < n; i++) {
					is = in.get(r[i].x1);
					if (is == null) {
						is = new ArrayList<Segment>();
					}
					is.add(new Segment(r[i].y1, r[i].y2 - 1, r[i].v));
					in.put(r[i].x1, is);

					os = out.get(r[i].x2);
					if (os == null) {
						os = new ArrayList<Segment>();
					}
					os.add(new Segment(r[i].y1, r[i].y2 - 1, -r[i].v));
					out.put(r[i].x2, os);
				}

				SegmentTree st = new SegmentTree(LIM + LIM + 1);
				int preLine = -1;
				long max = 0;
				long area = 0;

				for (int i = 0; i < len; i++) {
					if (preLine != -1) {
						long[] maxAndSum = st.getMaxAndSum();
						if (max < maxAndSum[MAX]) {
							max = maxAndSum[MAX];
							area = maxAndSum[SUM] * (lineSweep[i] - preLine);
						} else if (maxAndSum[MAX] == max) {
							area += maxAndSum[SUM] * (lineSweep[i] - preLine);
						}
					}
					is = in.get(lineSweep[i]);
					if (is != null) {
						for (Segment s : is) {
							st.update(s.from, s.to, s.v);
						}
					}
					os = out.get(lineSweep[i]);
					if (os != null) {
						for (Segment s : os) {
							st.update(s.from, s.to, s.v);
						}
					}

					preLine = lineSweep[i];
				}

				io.println(max + " " + area);
			}
		}

		class SegmentTree {
			private long[] max;
			private int[] sum;
			private long[] lazy;
			private int n;

			public SegmentTree(int n) {
				this.n = n;
				this.max = new long[4 * n + 10];
				this.lazy = new long[4 * n + 10];
				this.sum = new int[4 * n + 10];
				buildSum(1, 0, n - 1);
			}

			private void buildSum(int root, int rl, int rr) {
				if (rl == rr) {
					sum[root] = 1;
				} else {
					int rm = (rl + rr) >> 1;
					buildSum(left(root), rl, rm);
					buildSum(right(root), rm + 1, rr);
					sum[root] = sum[left(root)] + sum[right(root)];
				}
			}

			private int left(int root) {
				return root << 1;
			}

			private int right(int root) {
				return (root << 1) + 1;
			}

			public long[] getMaxAndSum() {
				lazyUpdate(1, 0, n - 1);
				long[] res = new long[2];
				res[0] = max[1];
				res[1] = sum[1];
				return res;
			}

			public void update(int l, int r, int v) {
				update(1, 0, n - 1, l, r, v);
			}

			private void update(int root, int rl, int rr, int l, int r, int v) {
				lazyUpdate(root, rl, rr);

				if (rl > rr || r < rl || l > rr) {
					return;
				}
				if (rl == l && rr == r) {
					max[root] += v;
					if (rl != rr) {
						lazy[left(root)] += v;
						lazy[right(root)] += v;
					}
					return;
				}
				int rm = (rl + rr) >> 1;
				update(left(root), rl, rm, l, Math.min(r, rm), v);
				update(right(root), rm + 1, rr, Math.max(l, rm + 1), r, v);

				max[root] = Math.max(max[left(root)], max[right(root)]);
				if (max[left(root)] > max[right(root)]) {
					sum[root] = sum[left(root)];
				} else if (max[left(root)] < max[right(root)]) {
					sum[root] = sum[right(root)];
				} else {
					sum[root] = sum[left(root)] + sum[right(root)];
				}
			}

			private void lazyUpdate(int root, int rl, int rr) {
				if (lazy[root] != 0) {
					max[root] += lazy[root];
					if (rl != rr) {
						lazy[left(root)] += lazy[root];
						lazy[right(root)] += lazy[root];
					}
					lazy[root] = 0;
				}
			}
		}

		class Segment {
			int from;
			int to;
			int v;

			public Segment(int from, int to, int v) {
				this.from = from;
				this.to = to;
				this.v = v;
			}
		}

		class Rectangle implements Comparable<Rectangle> {
			int x1;
			int y1;
			int x2;
			int y2;
			int v;

			public Rectangle(int x1, int y1, int x2, int y2, int v) {
				this.x1 = x1;
				this.y1 = y1;
				this.x2 = x2;
				this.y2 = y2;
				this.v = v;
			}

			@Override
			public int compareTo(Rectangle o) {
				int c = this.x1 - o.x1;
				if (c != 0) {
					return c;
				}
				return this.y1 - o.y1;
			}

		}
	}

	static class IO extends PrintWriter {
		public IO(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
		}

		public IO(InputStream i, OutputStream o) {
			super(new BufferedOutputStream(o));
			r = new BufferedReader(new InputStreamReader(i));
		}

		public boolean hasMoreTokens() {
			return peekToken() != null;
		}

		public int nextInt() {
			return Integer.parseInt(nextToken());
		}

		public double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		public long nextLong() {
			return Long.parseLong(nextToken());
		}

		public String next() {
			return nextToken();
		}

		private BufferedReader r;
		private String line;
		private StringTokenizer st;
		private String token;

		private String peekToken() {
			if (token == null)
				try {
					while (st == null || !st.hasMoreTokens()) {
						line = r.readLine();
						if (line == null)
							return null;
						st = new StringTokenizer(line);
					}
					token = st.nextToken();
				} catch (IOException e) {
				}
			return token;
		}

		private String nextToken() {
			String ans = peekToken();
			token = null;
			return ans;
		}
	}
}
