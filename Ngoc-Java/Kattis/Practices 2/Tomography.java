import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Tomography {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int m = ni();

		Integer[] sumRows = new Integer[n];
		for (int i = 0; i < n; i++) {
			sumRows[i] = ni();
		}

		// Short solution with complexity of m*nlog(n)
		// Better solution has complexity of m*n. Do not sort, decrease reasonable items. 
		// For e.g 1 2 3 3 3 4 4 5 6 (6) => 1 2 (3-1) (3-1) 3 (4-1) (4-1) (5-1) (6-1)
		// Optimal solution has complexity of n*log(n)*log(n)
		// Build Segment Tree for sorted sumRows
		// For each column's value: Find value v0 that we will update (for e.g 3).
		// Then find first and last occurrence of v0 - complexity log(n)*log(n) with log(n) for get value on Segment tree at position i
		// Update two segment on Segment tree: (last+1, n, -1) && (fist, first+last-colValue, -1)
		for (int i = 0; i < m; i++) {
			Arrays.sort(sumRows);
			int sumCol = ni();
			int j = n - 1;
			while (sumCol > 0) {
				sumRows[j--]--;
				sumCol--;
			}
		}

		Arrays.sort(sumRows);
		System.out.println(sumRows[0] == 0 && sumRows[n - 1] == 0 ? "Yes" : "No");
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
