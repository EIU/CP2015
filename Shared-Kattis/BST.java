import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BST {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		solve();
	}

	static final int LEAF = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;

	static void solve() {
		N = ni();
		maxBIT = new int[N + 1];

		int[] status = new int[N + 1];
		int[] heights = new int[N + 1];
		int[] nextNodes = new int[N + 1];

		heights[0] = -1;
		nextNodes[0] = N + 1;

		long C = 0;
		StringBuilder outString = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int nodeValue = ni();
			int preValue = previous(nodeValue);
			int nextValue = nextNodes[preValue];

			nextNodes[nodeValue] = nextValue;
			nextNodes[preValue] = nodeValue;
			update(nodeValue, nodeValue);

			if (nextValue > N || (status[preValue] & RIGHT) == 0) {
				status[preValue] |= RIGHT;
				C += (heights[nodeValue] = heights[preValue] + 1);
			} else {
				status[nextValue] |= LEFT;
				C += (heights[nodeValue] = heights[nextValue] + 1);
			}
			outString.append(C + "\n");
		}
		System.out.print(outString.toString());
	}

	static int N;
	static int[] maxBIT;

	static void update(int index, int value) {
		while (index <= N) {
			maxBIT[index] = Math.max(maxBIT[index], value);
			index += (index & -index);
		}
	}

	static int previous(int index) {
		int result = 0;
		while (index > 0) {
			result = Math.max(maxBIT[index], result);
			index -= (index & -index);
		}
		return result;
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
