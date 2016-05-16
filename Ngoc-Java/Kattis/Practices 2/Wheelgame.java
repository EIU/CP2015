import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Wheelgame {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();

		Arrays.fill(statuses, -1);

		System.out.println(check(n - 1) != 0 ?
				"Destroy the robot before it is too late" :
				"The robot is your friend");
	}

	static int[] statuses = new int[100];

	static int check(int n) {
		if (n >= 87) {
			n = 53 + (n - 53) % 34;
		}
		if (n <= 1) {
			return 0;
		}

		int r = statuses[n];
		if (r == -1) {
			boolean[] checked = new boolean[50];
			for (int i = 0; i - 1 <= n - i - 2; i++) {
				int g = check(i) ^ check(n - i - 2);
				checked[g] = true;
			}
			for (int i = 0;; i++) {
				if (!checked[i]) {
					statuses[n] = r = i;
					break;
				}
			}
		}
		return r;
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
