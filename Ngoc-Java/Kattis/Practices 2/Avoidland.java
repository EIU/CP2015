import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Avoidland {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int[] verticals = new int[n];
		int[] horizontals = new int[n];

		for (int i = 0; i < n; i++) {
			verticals[ni() - 1]++;
			horizontals[ni() - 1]++;
		}

		System.out.println(solve(verticals) + solve(horizontals));
	}

	private static long solve(int[] numbers) {
		int len = numbers.length;
		long result = 0;
		int i = 0, j = 0;
		while (i < len && j < len) {
			while (i < len && numbers[i] != 0) {
				i++;
			}
			while (j < len && numbers[j] <= 1) {
				j++;
			}
			if (i < len && j < len) {
				result += i < j ? j - i : i - j;
				numbers[i]++;
				numbers[j]--;
			}
		}
		return result;
	}

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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
