import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BST_Linear {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		solve();
	}

	static final int LEAF = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;

	static void solve() {
		int N = ni();
		int[] values = new int[N];
		for (int i = 0; i < N; i++) {
			values[i] = ni();
		}

		int[] nextNodes = new int[N + 2];
		int[] preNodes = new int[N + 2];
		for (int i = 0; i <= N + 1; i++) {
			nextNodes[i] = i + 1;
			preNodes[i] = i - 1;
		}

		for (int i = N - 1; i >= 0; i--) {
			int value = values[i];
			nextNodes[preNodes[value]] = nextNodes[value];
			preNodes[nextNodes[value]] = preNodes[value];
		}

		int[] status = new int[N + 1];
		int[] heights = new int[N + 1];
		heights[0] = -1;

		long C = 0;
		StringBuilder outString = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int value = values[i];

			int next = nextNodes[value];
			int pre = preNodes[value];

			if ((status[pre] & RIGHT) == 0) {
				status[pre] |= RIGHT;
				C += (heights[value] = heights[pre] + 1);
			} else {
				status[next] |= LEFT;
				C += (heights[value] = heights[next] + 1);
			}
			outString.append(C + "\n");
		}
		System.out.print(outString.toString());
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
