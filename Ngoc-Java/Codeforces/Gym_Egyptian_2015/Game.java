import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Game {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		map = new int[26][26];
		for (int i = 0; i < 26; i++) {
			char[] chars = ns().toCharArray();
			for (int j = 0; j < 26; j++) {
				map[i][j] = chars[j] - 'a';
			}
		}

		char[] chars = ns().toCharArray();
		int[] numbers = new int[chars.length];
		for (int j = 0; j < chars.length; j++) {
			numbers[j] = chars[j] - 'a';
		}

		System.out.println(canShalahWin(numbers, true) ? "Salah" : "Marzo");
	}

	static int[][] map;

	static int[] moveLeft(int[] numbers) {
		int len = numbers.length;
		int[] lefts = new int[(len + 1) >> 1];
		int i = 0, j = 0;
		for (; j < len - 1; i += 1, j += 2) {
			lefts[i] = map[numbers[j]][numbers[j + 1]];
		}
		if (j < len) {
			lefts[i] = numbers[j];
		}
		return lefts;
	}

	static int[] moveRight(int[] numbers) {
		int len = numbers.length;
		int[] rights = new int[(len + 1) >> 1];
		int i = rights.length - 1, j = len - 1;
		for (; j > 0; i--, j -= 2) {
			rights[i] = map[numbers[j]][numbers[j - 1]];
		}
		if (j >= 0) {
			rights[0] = numbers[0];
		}
		return rights;
	}

	static int A = 'a' - 'a';
	static int E = 'e' - 'a';
	static int I = 'i' - 'a';
	static int O = 'o' - 'a';
	static int U = 'u' - 'a';

	static boolean canShalahWin(int[] numbers, boolean isShalahTurn) {
		if (numbers.length == 1) {
			return numbers[0] == A || numbers[0] == E || numbers[0] == I || numbers[0] == O || numbers[0] == U;
		}

		if (isShalahTurn) {
			return canShalahWin(moveLeft(numbers), false) || canShalahWin(moveRight(numbers), false);
		}
		else {
			return canShalahWin(moveLeft(numbers), true) && canShalahWin(moveRight(numbers), true);
		}
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
}