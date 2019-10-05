import java.io.*;
import java.util.*;

public class beehouseperimeter {

	static final int OUTSIDE = 0;
	static final int EMPTY = 1;
	static final int CHOOSEN = 2;
	static final int CHECKED = 3;

	static int[][] nextIndies = new int[][] { { -1, 0 }, { 1, 0 }, { -1, -1 }, { 0, -1 }, { 0, 1 }, { 1, 1 } };
	static int[][] honeycomb;

	static int result = 0;

	public static void main(String[] args) throws Exception {
		int R = reader.nextInt(), K = reader.nextInt();

		int[] cells = new int[K];
		for (int i = 0; i < K; i++) {
			cells[i] = reader.nextInt();
		}
		Arrays.sort(cells);

		int diameter = R + R;
		honeycomb = new int[diameter + 1][diameter + 1];
		int cellIndex = 0;

		int number = 0;
		int startColumn = 1;
		int rowLength = R;
		for (int i = 1; i < diameter; i++) {
			for (int j = 0; j < rowLength; j++) {
				number++;
				if (cellIndex < cells.length && number == cells[cellIndex]) {
					honeycomb[i][startColumn + j] = CHOOSEN;
					cellIndex++;
				} else {
					honeycomb[i][startColumn + j] = EMPTY;
				}
			}
			if (i >= R) {
				startColumn += 1;
				rowLength -= 1;
			} else {
				rowLength += 1;
			}
		}

		for (int i = 0; i < honeycomb.length; i++) {
			for (int j = 0; j < honeycomb.length; j++) {
				if (honeycomb[i][j] == OUTSIDE) {
					dfs(i, j);
				}
			}
		}

		System.out.println(result);
	}

	static void dfs(int row, int column) {
		honeycomb[row][column] = CHECKED;

		for (int k = 0; k < nextIndies.length; k++) {
			int nextRow = row + nextIndies[k][0], nextColumn = column + nextIndies[k][1];
			if (isIndexValid(nextRow, nextColumn)) {
				int nextCell = honeycomb[nextRow][nextColumn];
				if (nextCell == CHOOSEN) {
					result++;
				} else if (nextCell == EMPTY) {
					dfs(nextRow, nextColumn);
				}
			}
		}
	}

	static boolean isIndexValid(int i, int j) {
		return 0 <= i && i < honeycomb.length && 0 <= j && j < honeycomb.length;
	}

	// x. x. x. x. x. x. x.
	// x. 1. 2. 3. x. x. x.
	// x. 4. 5. 6. 7. x. x.
	// x. 8. 9. 10 11 12 x.
	// x. x. 13 14 15 16 x.
	// x. x. x. 17 18 19 x.
	// x. x. x. x. x. x. x.

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
