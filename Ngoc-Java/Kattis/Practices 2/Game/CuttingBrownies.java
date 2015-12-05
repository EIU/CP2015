import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CuttingBrownies {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int B = ni();
			int D = ni();
			boolean isHarry = ns().equals("Harry");
			String canWin = (isHarry && map[B][D] > 0 || !isHarry && map[D][B] > 0) ? "" : "not";
			bf.append((isHarry ? "Harry" : "Vicky") + " can" + canWin + " win");
		}
		System.out.println(bf);
	}

	static final int MAX = 501;
	static int[][] map = new int[MAX][MAX];

	static void prepare() {
		for (int i = 0; i < MAX; i++) {
			for (int row = i; row < MAX; row++) {
			}

			for (int column = i + 1; column < MAX; column++) {
			}
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
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
