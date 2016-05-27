import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Oil {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();

		int[][] deposits = new int[n][3];
		for (int i = 0; i < n; i++) {
			deposits[i][0] = ni();
			deposits[i][1] = ni();
			deposits[i][2] = ni();
			if (deposits[i][0] > deposits[i][1]) {
				int temp = deposits[i][0];
				deposits[i][0] = deposits[i][1];
				deposits[i][1] = temp;
			}
		}

		long max = 0;
		for (int i = 0; i < n; i++) {
			Group group = new Group(deposits, i);
			max = Math.max(max, group.getMax());
		}
		System.out.println(max);
	}

	static class Group {
		int rootX;
		int rootY;
		int iAngle = 0;
		long currentOil;

		long[] angles;
		long[] begins;
		long[] ends;

		public Group(int[][] deposits, int i) {
			rootX = deposits[i][0];
			rootY = deposits[i][2];
			currentOil = deposits[i][1] - deposits[i][0];

			angles = new long[deposits.length * 2];
			begins = new long[deposits.length * 2];
			ends = new long[deposits.length * 2];

			for (int j = 0; j < deposits.length; j++) {
				angles[iAngle++] = getAngle(deposits[j][0], deposits[j][2]);
				angles[iAngle++] = getAngle(deposits[j][1], deposits[j][2]);
			}
			Arrays.sort(angles);

			for (int j = 0; j < deposits.length; j++) {
				addInterval(deposits[j][0], deposits[j][1], deposits[j][2]);
			}
		}

		private long getAngle(long x, long y) {
			if (y == rootY) {
				return Long.MAX_VALUE;
			}
			return (x - rootX) * Integer.MAX_VALUE / (y - rootY);
		}

		private void addInterval(int x1, int x2, int y) {
			long angle1 = getAngle(x1, y);
			long angle2 = getAngle(x2, y);
			if (angle1 > angle2) {
				long temp = angle1;
				angle1 = angle2;
				angle2 = temp;
			}
			if (angle1 == Long.MAX_VALUE) {
				return;
			}

			int startIndex = Arrays.binarySearch(angles, angle1);
			if (startIndex < 0) {
				startIndex = -startIndex - 1;
			}

			int endIndex = Arrays.binarySearch(angles, angle2);
			if (endIndex < 0) {
				endIndex = -endIndex - 2;
			}

			if (startIndex <= endIndex && endIndex >= 0) {
				begins[startIndex] += x2 - x1;
				ends[endIndex] += x2 - x1;
			}
		}

		public long getMax() {
			long sum = 0;
			long maxSum = 0;
			for (int i = 0; i < begins.length; i++) {
				sum += begins[i];
				maxSum = Math.max(maxSum, sum);
				sum -= ends[i];
			}
			return currentOil + maxSum;
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
