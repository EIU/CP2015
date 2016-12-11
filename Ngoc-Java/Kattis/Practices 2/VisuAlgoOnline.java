import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class VisuAlgoOnline {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int V = ni(), E = ni();

		Point[] points = new Point[V];
		for (int i = 0; i < V; i++) {
			points[i] = new Point();
		}

		for (int i = 0; i < E; i++) {
			points[ni()].addEdge(points[ni()], ni());
		}

		int s = ni(), t = ni();

		dijkstra(points[s]);

		System.out.println(points[t].count);
	}

	static void dijkstra(Point start) {
		PriorityQueue<PointLink> nextPoints = new PriorityQueue<PointLink>();
		start.length = 0;
		start.count = 1;
		nextPoints.add(new PointLink(start));
		while (!nextPoints.isEmpty()) {
			Point point = nextPoints.poll().point;
			if (point.isCompleted) {
				continue;
			}
			point.isCompleted = true;
			for (int i = 0; i < point.targets.size(); i++) {
				Point next = point.targets.get(i);
				if (next.isCompleted) {
					continue;
				}
				if (next.length == point.length + point.edges.get(i)) {
					next.count += point.count;
				} else if (next.length > point.length + point.edges.get(i)) {
					next.length = point.length + point.edges.get(i);
					next.count = point.count;
					nextPoints.add(new PointLink(next));
				}
			}
		}
	}

	static class Point {
		public boolean isCompleted;
		public long length = Long.MAX_VALUE / 2;
		public int count = 0;
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
