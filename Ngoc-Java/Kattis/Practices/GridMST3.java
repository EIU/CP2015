import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GridMST3 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		solve();
	}

	static void solve() {
		int N = ni();

		int MAX = 1000; // 1000

		TreeSet<Point>[] trees = new TreeSet[MAX];
		for (int i = 0; i < MAX; i++) {
			trees[i] = new TreeSet<Point>();
		}

		int distintPoint = 0;
		for (int i = 0; i < N; i++) {
			int x = ni();
			int y = ni();
			Point point = new Point(x, y);
			if (!trees[x].contains(point)) {
				trees[x].add(point);
				distintPoint++;
			}
		}
		N = distintPoint;

		TreeSet<Edge> edges = new TreeSet<Edge>();
		for (int i = 0; i < MAX; i++) {
			TreeSet<Point> treeI = trees[i];
			for (Point current : treeI) {
				Point left = treeI.lower(current), right = treeI.higher(current);
				if (left != null) {
					edges.add(new Edge(left, current));
				}
				if (right != null) {
					edges.add(new Edge(current, right));
				}

				Point preLeft = left, preRight = right;
				for (int k = i + 1; k < MAX; k++) {
					TreeSet<Point> linek = trees[k];

					Point nextLeft = linek.floor(current); // linek.lower(current);
					if (nextLeft != null && (preLeft == null || nextLeft.y >= preLeft.y)) {
						edges.add(new Edge(current, nextLeft));
						preLeft = nextLeft;
						if (nextLeft.y == current.y) {
							break;
						}
					}
					Point nextRight = linek.higher(current);
					if (nextRight != null && (preRight == null || nextRight.y <= preRight.y)) {
						edges.add(new Edge(current, nextRight));
						preRight = nextRight;
						if (nextRight.y == current.y) {
							break;
						}
					}
				}
			}
		}

		int i = 0;
		int weight = 0;
		for (Edge edge : edges) {
			if (edge == null) {
				break;
			}
			Point root1 = edge.start;
			Point root2 = edge.end;
			while (root1.root != null) {
				root1 = root1.root;
			}
			if (edge.start != root1) {
				edge.start.root = root1;
			}
			while (root2.root != null) {
				root2 = root2.root;
			}
			if (edge.end != root2) {
				edge.end.root = root2;
			}
			if (root1 != root2) {
				root1.root = root2;
				i++;
				weight += edge.getDistance();
			}
		}
		System.out.println(weight);
	}

	static class Point implements Comparable<Point> {
		public int x;
		public int y;
		public Point root = null;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point arg0) {
			return this.y - arg0.y;
		}
	}

	static class Edge implements Comparable<Edge> {
		public Point start;
		public Point end;
		public Edge(Point p1, Point p2) {
			start = p1;
			end = p2;
		}
		public int getDistance() {
			return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
		}
		@Override
		public int compareTo(Edge arg0) {
			int diff = getDistance() - arg0.getDistance();
			if (diff != 0) {
				return diff;
			}
			if (start.x != arg0.start.x) {
				return start.x - arg0.start.x;
			}
			if (start.y != arg0.start.y) {
				return start.y - arg0.start.y;
			}
			if (end.x != arg0.end.x) {
				return end.x - arg0.end.x;
			}
			return end.y - arg0.end.y;
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
