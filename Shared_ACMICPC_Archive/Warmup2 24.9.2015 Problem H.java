import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
	final static int LIM = 100;
	final static int MAX = 200;

	public static void main(String[] args) throws IOException {
		int nTest = nextInt();

		while (nTest-- > 0) {
			int id = nextInt();
			int n = nextInt();
			Point[] points = new Point[n + 1];
			for (int i = 0; i < n; ++i) {
				int x = nextInt() + LIM;
				int y = nextInt() + LIM;
				points[i] = new Point(x, y);
			}
			points[n] = new Point(points[0].x, points[0].y);
			n++;
			Line[] lines = new Line[MAX + 1];
			for (int i = 0; i <= MAX; i++) {
				lines[i] = new Line();
			}
			for (int i = 1; i < n; i++) {
				Point pre = points[i - 1];
				Point cur = points[i];
				int deltaX = Math.abs(cur.x - pre.x);
				int deltaY = Math.abs(cur.y - pre.y);
				int minY = Math.min(pre.y, cur.y);
				int maxY = Math.max(pre.y, cur.y);

				if (deltaY != 0) {
					if (cur.x > pre.x) {
						if (cur.y > pre.y) {
							for (int y = minY + 1; y <= maxY; y++) {
								int diff = getDiff(deltaX, deltaY, maxY - y);
								lines[y].leftMost = cur.x - diff - LIM;
							}
						} else {
							for (int y = maxY - 1; y >= minY; y--) {
								int diff = getDiff(deltaX, deltaY, maxY - y);
								lines[y].rightMost = pre.x + diff - LIM;
							}
						}

					} else {
						if (cur.y < pre.y) {
							for (int y = maxY - 1; y >= minY; y--) {
								int diff = getDiff(deltaX, deltaY, y - minY);
								lines[y].rightMost = cur.x + diff - LIM;
							}
						} else {
							for (int y = minY + 1; y <= maxY; y++) {
								int diff = getDiff(deltaX, deltaY, y - minY);
								lines[y].leftMost = pre.x - diff - LIM;
							}
						}
					}
				}
			}
			int count = 0;
			for (int i = MAX; i >= 0; i--) {
				int left = lines[i].leftMost;
				int right = lines[i].rightMost;
				if (left != -1 && right != -1 && right >= left) {
					count++;
				}
			}

			System.out.println(id + " " + count);
			for (int i = MAX; i >= 0; i--) {
				int left = lines[i].leftMost;
				int right = lines[i].rightMost;
				if (left != -1 && right != -1 && right >= left) {
					String ans = (i - LIM) + " " + left + " " + right;
					System.out.println(ans);
				}
			}
		}
	}

	private static int getDiff(int deltaX, int deltaY, int lowerDeltaY) {
		// ti le tam giac dong dang.
		double diff = 1.0 * deltaX * (1.0 * lowerDeltaY / deltaY);
		if (diff == Math.floor(diff) && !Double.isInfinite(diff)) {
			diff--;
		}
		return (int) diff;
	}

	static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer tokenizer = new StringTokenizer("");

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			String n = reader.readLine();
			if (n == null) {
				return null;
			}
			tokenizer = new StringTokenizer(n);

		}
		return tokenizer.nextToken();
	}

	static String nextLine() throws IOException {
		return reader.readLine();
	}

	static Integer nextInt() throws IOException {
		String next = next();
		if (next == null) {
			return null;
		}
		return Integer.parseInt(next);
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static Double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}

class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Line {
	public int leftMost, rightMost;

	public Line() {
		leftMost = -1;
		rightMost = -1;
	}
}
