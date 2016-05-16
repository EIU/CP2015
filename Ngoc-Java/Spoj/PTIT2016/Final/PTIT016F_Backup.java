import java.io.*;
import java.util.*;

class PTIT016F_Backup {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		ArrayList<Integer> pXs = new ArrayList<Integer>();
		ArrayList<Integer> pYs = new ArrayList<Integer>();

		int startX = ni();
		int startY = ni();
		int endX = ni();
		int endY = ni();

		pXs.add(startX);
		pXs.add(endX);
		pYs.add(startY);
		pYs.add(endY);

		int n = ni();
		int[] cellXs = new int[n];
		int[] cellYs = new int[n];

		for (int i = 0; i < n; i++) {
			int x = cellXs[i] = ni();
			int y = cellYs[i] = ni();
			pXs.add(x);
			pYs.add(y);
		}

		Collections.sort(pXs);
		Collections.sort(pYs);

		int[] npXs = new int[n + 6];
		int minX = pXs.get(0);
		npXs[0] = minX - 2;
		npXs[1] = minX - 1;
		npXs[2] = minX;
		int sizeX = 3;
		for (int i = 1; i < n + 2; i++) {
			if (pXs.get(i) != pXs.get(i - 1)) {
				npXs[sizeX++] = pXs.get(i);
			}
		}
		int maxX = pXs.get(n + 1);
		npXs[sizeX++] = maxX + 1;
		npXs[sizeX++] = maxX + 2;

		int[] npYs = new int[n + 6];
		int minY = pYs.get(0);
		npYs[0] = minY - 2;
		npYs[1] = minY - 1;
		npYs[2] = minY;
		int sizeY = 3;
		for (int i = 1; i < n + 2; i++) {
			if (pYs.get(i) != pYs.get(i - 1)) {
				npYs[sizeY++] = pYs.get(i);
			}
		}
		int maxY = pYs.get(n + 1);
		npXs[sizeY++] = maxY + 1;
		npXs[sizeY++] = maxY + 2;

		long[][] map = new long[sizeX][sizeY];
		long minLong = Long.MIN_VALUE / 2;
		for (int i = 0; i < n; i++) {
			int u = Arrays.binarySearch(npXs, cellXs[i], 0, sizeX);
			int v = Arrays.binarySearch(npYs, cellYs[i], 0, sizeY);
			map[u][v] = minLong;
		}

		int startU = Arrays.binarySearch(npXs, startX, 0, sizeX);
		int startV = Arrays.binarySearch(npYs, startY, 0, sizeY);
		int endU = Arrays.binarySearch(npXs, endX, 0, sizeX);
		int endV = Arrays.binarySearch(npYs, endY, 0, sizeY);

		if (startU == endU && startV == endV) {
			System.out.println(0);
			return;
		}

		// XFS from (startU, V) to (endU, V)
		int maxQueue = sizeX * sizeY;
		int[] queueX = new int[maxQueue];
		int[] queueY = new int[maxQueue];
		int head = 1;
		int tail = head + 1;
		queueX[0] = startU;
		queueY[0] = startV;
		while (head <= tail) {
			int u = queueX[head];
			int v = queueY[head];
			endQueue(u - 1, v);
			endQueue(u + 1, v);
			endQueue(u, v - 1);
			endQueue(u, v - 1);
			head++;
			if (u > 0) {
				map[u - 1][v] = Math.min(map[u - 1][v], map[u][v] + npXs[u + 1] - npXs[u - 1]);
			}
			if (u < sizeX - 1) {

			}
			if (v > 0) {

			}
			if (v < sizeY - 1) {

			}
		}

	}

	static class Cell {
		public int x;
		public int y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Queue<Cell> queue;

	static int sizeX;
	static int sizeY;
	static long[][] map;

	static void initiMap(int sX, int sY) {
		sizeX = sX;
		sizeY = sY;
		map = new long[sizeX][sizeY];
		queue = new ArrayDeque<Cell>();
	}

	static void endQueue(int u, int v) {
		if (u > 0 && u < sizeX - 1 && v > 0 && v < sizeY - 1 && map[u][v] == 0) {
			queue.add(new Cell(u, v));
		}
	}

	static void setMap(int u, int v, long value) {
		map[u][v] = Math.min(map[u][v], value);
	}

	static class DistanceMapping {
		List<Integer> list = new ArrayList<Integer>();

		public void add(int x) {
			list.add(x);
		}

		public int[] getDistances() {
			list.sort((i1, i2) -> i1.compareTo(i2));

			int size = list.size();
			int[] result = new int[size];
			int resultSize = 0;
			int pre = list.get(0) - 2;

			for (int i = 0; i < size; i++) {
				int cur = list.get(i);
				if (pre < cur - 1) {
					result[resultSize++] = cur - 1 - pre;
				}
				if (pre <= cur) {
					result[resultSize++] = 1;
				}
				pre = cur;
			}
			result[resultSize++] = 1;
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
