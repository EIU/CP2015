import java.io.*;
import java.util.*;

class PTIT016F {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {

		int startX = ni();
		int startY = ni();
		int endX = ni();
		int endY = ni();
		int n = ni();

		DistinctMapping mapH = new DistinctMapping();
		DistinctMapping mapV = new DistinctMapping();

		mapH.add(startX, endX);
		mapV.add(startY, endY);

		int[] rookXs = new int[n];
		int[] rookYs = new int[n];
		for (int i = 0; i < n; i++) {
			mapH.add(rookXs[i] = ni());
			mapV.add(rookYs[i] = ni());
		}

		if (startX == endX && startY == endY) {
			System.out.println(0);
			return;
		}

		int[] distanceXs = mapH.getValues();
		int[] distanceYs = mapV.getValues();

		sizeX = distanceXs.length;
		sizeY = distanceYs.length;

		cells = new Cell[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}

		for (int i = 0; i < n; i++) {
			int u = Arrays.binarySearch(distanceXs, rookXs[i]);
			int v = Arrays.binarySearch(distanceYs, rookYs[i]);
			cells[u][v].value = -1;
			cells[u][v].completed = BOTH;
		}
		startX = Arrays.binarySearch(distanceXs, startX);
		startY = Arrays.binarySearch(distanceYs, startY);
		endX = Arrays.binarySearch(distanceXs, endX);
		endY = Arrays.binarySearch(distanceYs, endY);

		Cell startCell = cells[startX][startY];
		Cell endCell = cells[endX][endY];
		endCell.value = -1;
		queue.add(startCell);

		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			if ((cell.completed & HORIZONTAL) == 0) {
				moveY(cell.x, cell.y + 1, 1, cell.value + 1);
				moveY(cell.x, cell.y - 1, -1, cell.value + 1);
				cell.completed |= HORIZONTAL;
			}
			if ((cell.completed & VERTICAL) == 0) {
				moveX(cell.x + 1, cell.y, 1, cell.value + 1);
				moveX(cell.x - 1, cell.y, -1, cell.value + 1);
				cell.completed |= VERTICAL;
			}
		}

		System.out.println(endCell.value);
	}

	static void moveY(int x, int y, int dy, int value) {
		for (; 0 <= y && y < sizeY && (cells[x][y].completed & HORIZONTAL) == 0; y += dy) {
			if (cells[x][y].completed == 0) {
				cells[x][y].value = value;
			}
			cells[x][y].completed |= HORIZONTAL;
			if (cells[x][y].completed != BOTH) {
				queue.add(cells[x][y]);
			}
		}
	}

	static void moveX(int x, int y, int dx, int value) {
		for (; 0 <= x && x < sizeX && (cells[x][y].completed & VERTICAL) == 0; x += dx) {
			if (cells[x][y].completed == 0) {
				cells[x][y].value = value;
			}
			cells[x][y].completed |= VERTICAL;
			if (cells[x][y].completed != BOTH) {
				queue.add(cells[x][y]);
			}
		}
	}

	static class Cell {
		public int value;
		public int completed;
		public int x;
		public int y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int VERTICAL = 1;
	static final int HORIZONTAL = 2;
	static final int BOTH = 3;

	static int sizeX;
	static int sizeY;
	static Cell[][] cells;

	static Queue<Cell> queue = new ArrayDeque<Cell>();

	static class DistinctMapping {
		List<Integer> list = new ArrayList<Integer>();

		public void add(int... range) {
			for (int x : range) {
				list.add(x - 1);
				list.add(x);
			}
		}

		public int[] getValues() {
			list.sort((i1, i2) -> i1.compareTo(i2));

			int size = list.size();
			int[] result = new int[size];
			int resultSize = 0;

			result[resultSize++] = list.get(0);
			for (int i = 1; i < size; i++) {
				if (list.get(i).compareTo(list.get(i - 1)) != 0) {
					result[resultSize++] = list.get(i);
				}
			}
			result[resultSize++] = list.get(list.size() - 1) + 1;
			return Arrays.copyOf(result, resultSize);
		}
	}

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

	static byte[] inbuf = new byte[4096];
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
