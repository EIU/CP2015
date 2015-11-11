import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class RobertHood {
	static InputStream is;

	final static int BASE = 1001;
	final static int BASE2 = BASE * 2;
	static int[] ups = new int[BASE2];
	static int[] downs = new int[BASE2];

	public static void main(String[] args) throws Exception {
		is = System.in;
		Arrays.fill(ups, -BASE);
		Arrays.fill(downs, BASE);

		int C = ni();
		for (int i = 0; i < C; i++) {
			int x = ni();
			int y = ni();
			int index = x + BASE;
			ups[index] = Math.max(ups[index], y);
			downs[index] = Math.min(downs[index], y);
		}

		int maxLength = 0;
		for (int i = 0; i < BASE2; i++) {
			if (downs[i] == BASE) {
				continue;
			}
			for (int j = i + 1; j < BASE2; j++) {
				if (downs[j] == BASE) {
					continue;
				}
				maxLength = Math.max(maxLength, getSquareLength(i, ups[i], j, ups[j]));
				maxLength = Math.max(maxLength, getSquareLength(i, downs[i], j, ups[j]));
				maxLength = Math.max(maxLength, getSquareLength(i, ups[i], j, downs[j]));
				maxLength = Math.max(maxLength, getSquareLength(i, downs[i], j, downs[j]));
			}
		}
		System.out.println(Math.sqrt(maxLength));
	}

	static int getSquareLength(int x1, int y1, int x2, int y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

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
