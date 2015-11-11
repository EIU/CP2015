import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MegaInversions {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();

		SumBIT values = new SumBIT(n);
		SumBIT countDoubles = new SumBIT(n);
		long sumTriples = 0;

		for (int i = 0; i < n; i++) {
			int position = n + 1 - ni();

			long countSmaller = values.get(position - 1);
			values.set(position, 1);

			long countTwoSmaller = countDoubles.get(position - 1);
			countDoubles.set(position, countSmaller);
			sumTriples += countTwoSmaller;
		}
		System.out.println(sumTriples);
	}

	static class SumBIT {
		int size;
		long[] sums;

		public SumBIT(int n) {
			size = n;
			sums = new long[n + 1];
		}

		public void set(int i, long value) {
			for (; i <= size; i += (i & -i)) {
				sums[i] += value;
			}
		}

		public long get(int i) {
			long sum = 0;
			for (; i > 0; i -= (i & -i)) {
				sum += sums[i];
			}
			return sum;
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
