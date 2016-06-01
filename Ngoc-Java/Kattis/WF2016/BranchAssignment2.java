import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BranchAssignment2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int b = ni();
		int s = ni();
		int r = ni();
		
		Point[] points = new Point[n];
		Point[] invPoints = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = new Point();
			invPoints[i] = new Point();
		}

		for (int i = 0; i < r; i++) {
			int u = ni() - 1;
			int v = ni() - 1;
			int length = ni();
			points[u].addEdge(points[v], length);
			invPoints[v].addEdge(invPoints[u], length);
		}

		dijkstra(points[b]);
		dijkstra(invPoints[b]);

		Long[] branches = new Long[b + 1];
		branches[0] = 0L;
		for (int i = 0; i < b; i++) {
			branches[i + 1] = points[i].length + invPoints[i].length;
		}

		Arrays.sort(branches);

		long[] sums = new long[b + 1];
		for (int i = 1; i <= b; i++) {
			sums[i] = sums[i - 1] + branches[i];
		}

		long[][] dp = new long[s + 1][b + 1];

		for (int i = 1; i <= b; i++) {
			dp[1][i] = sums[i] * (i - 1);
		}

		for (int nGroup = 2; nGroup <= s; nGroup++) {
			int j = nGroup;
			for (int i = nGroup; i <= b; i++) {
				int minj = j;
				long min = dp[nGroup - 1][minj - 1] + (sums[i] - sums[minj - 1]) * (i - minj);

				int t = 1;
				while (j <= i && t < 28) {
					if ((sums[i] - sums[j - 1]) * (i - j) + dp[nGroup - 1][j - 1] <= min) {
						minj = j;
						min = (sums[i] - sums[j - 1]) * (i - j) + dp[nGroup - 1][j - 1];
					}
					j++;
					t++;
				}

				dp[nGroup][i] = min;
				j = minj;
			}
		}

		System.out.println(dp[s][b]);
	}
	static void dijkstra(Point start) {
		PriorityQueue<PointLink> nextPoints = new PriorityQueue<PointLink>();
		start.length = 0;
		nextPoints.add(new PointLink(start));
		while (!nextPoints.isEmpty()) {
			Point point = nextPoints.poll().point;
			if (point.isCompleted) {
				continue;
			}
			point.isCompleted = true;
			for (int i = 0; i < point.targets.size(); i++) {
				Point next = point.targets.get(i);
				if (!next.isCompleted && next.length > point.length + point.edges.get(i)) {
					next.length = point.length + point.edges.get(i);
					//				if (!next.isCompleted) {
					//					next.length = Math.min(next.length, point.length + point.edges.get(i));
					nextPoints.add(new PointLink(next));
				}
			}
		}
	}

	static class Point {
		public boolean isCompleted;
		public long length = Long.MAX_VALUE / 2;
		public List<Point> targets = new ArrayList<Point>();
		public List<Integer> edges = new ArrayList<Integer>();

		public void addEdge(Point target, int length) {
			targets.add(target);
			edges.add(length);
		}
	}

	static class PointLink implements Comparable<PointLink> {
		public Point point;
		public long curLength;

		public PointLink(Point point) {
			this.point = point;
			this.curLength = point.length;
		}

		@Override
		public int compareTo(PointLink arg0) {
			return Long.compare(curLength, arg0.curLength);
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
