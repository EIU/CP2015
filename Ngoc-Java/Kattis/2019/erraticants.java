import java.io.*;
import java.util.*;

public class erraticants {

	static Cell[][] matrix = new Cell[122][122];
	static int startX = 61, startY = 61;

	public static void main(String[] args) {
		int n = reader.nextInt();

		for (int test = 0; test < n; test++) {
			clear();
			int s = reader.nextInt();
			Cell current = matrix[startX][startY];
			// Cell current = matrix[startX][startY] = new Cell();
			int currentX = startX, currentY = startY;
			for (int i = 0; i < s; i++) {
				char direction = reader.nextChar();
				currentX += (direction == 'E' ? 1 : (direction == 'W' ? -1 : 0));
				currentY += (direction == 'S' ? 1 : (direction == 'N' ? -1 : 0));
				Cell next = matrix[currentX][currentY];
				/*
				 * if (next == null) { next = matrix[currentX][currentY] = new Cell(); }
				 */
				current.addNext(next);
				next.addNext(current);
				current = next;
			}
			System.out.println(shortestPath(current));
		}
	}

	static int shortestPath(Cell end) {
		Cell start = matrix[startX][startY];
		Queue<Cell> queue = new ArrayDeque<Cell>();
		queue.add(start);
		start.isVisited = true;
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (Cell next : cell.nextCells) {
				if (!next.isVisited) {
					queue.add(next);
					next.distance = cell.distance + 1;
					next.isVisited = true;
				}
			}
		}
		return end.distance;
	}

	static void clear() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Cell();
			}
		}
	}

	static class Cell {
		public int distance = 0;
		public boolean isVisited = false;
		List<Cell> nextCells = new ArrayList<Cell>();

		public void addNext(Cell next) {
			nextCells.add(next);
		}
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
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

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
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

		long nextLong() {
			long num = 0;
			int b;
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
	}
}
