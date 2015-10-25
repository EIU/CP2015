import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Hiring {
	static InputStream is;

	/*
	 * Base on TranCongDuy's solution
	 */
	public static void main(String[] args) throws Exception {
		is = System.in;

		n = ni();
		m = ni();
		sumBIT = new long[m + 1];
		countBIT = new int[m + 1];

		Date[] dates = new Date[m];
		for (int i = 0; i < m; i++) {
			dates[i] = new Date(i + 1, ni());
		}
		Arrays.sort(dates); // Descending

		Worker[] workers = new Worker[n];
		for (int i = 0; i < n; i++) {
			workers[i] = new Worker(i, ni(), ni());
		}
		Arrays.sort(workers); // Descending

		long[] workerResult = new long[n];

		int j = 0;
		for (int i = 0; i < n; i++) {
			Worker w = workers[i];
			for (; j < m && dates[j].t > w.d; j++) {
				setValue(dates[j].id, dates[j].t);
			}
			workerResult[w.id] = findBest(w.d, w.r);
		}

		StringBuilder bf = new StringBuilder();
		for (long r : workerResult) {
			bf.append(r + " ");
		}

		System.out.println(bf.toString());
	}

	static long findBest(int d, int t) {
		int lower = 0;
		int upper = m;

		long value = getSum(upper);
		long count = getCount(upper);
		if (value < count * d + t) {
			return 0;
		}

		while (lower + 1 < upper) {
			int mid = (lower + upper) >> 1;
			value = getSum(mid);
			count = getCount(mid);

			if (value >= count * d + t) {
				upper = mid;
			} else {
				lower = mid;
			}
		}
		return upper;
	}

	static int n;
	static int m;
	static long[] sumBIT;
	static int[] countBIT;

	static void setValue(int index, int value) {
		for (; index <= m; index += (index & -index)) {
			sumBIT[index] += value;
			countBIT[index] += 1;
		}
	}

	static long getSum(int index) {
		long result = 0;
		for (; index > 0; index -= (index & -index)) {
			result += sumBIT[index];
		}
		return result;
	}

	static int getCount(int index) {
		int result = 0;
		for (; index > 0; index -= (index & -index)) {
			result += countBIT[index];
		}
		return result;
	}

	static class Worker implements Comparable<Worker> {
		public int id;
		public int d;
		public int r;

		public Worker(int id, int d, int r) {
			this.id = id;
			this.d = d;
			this.r = r;
		}

		@Override
		public int compareTo(Worker arg0) {
			return arg0.d - d;
		}
	}

	static class Date implements Comparable<Date> {
		public int id;
		public int t;

		public Date(int id, int t) {
			this.id = id;
			this.t = t;
		}

		@Override
		public int compareTo(Date arg0) {
			return arg0.t - this.t;
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
