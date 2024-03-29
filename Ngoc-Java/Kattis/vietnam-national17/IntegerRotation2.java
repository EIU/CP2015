import java.io.*;
import java.util.*;

public class IntegerRotation2 {
	static InputStream is;

	static int MAX = 1000001;

	public static void main(String[] args) throws Exception {
		is = System.in;

		long s = System.currentTimeMillis();
		List<List<Integer>> listNumbers = (ArrayList<List<Integer>>) new ArrayList();

		int base = 10;
		for (int i = 11; i < MAX; i++) {
			if (i == base * 10) {
				base *= 10;
				continue;
			}

			int division = 10;
			int right = base;
			List<Integer> rotatedNumbers = null;
			while (division < i) {
				int j = i / division + (i % division) * right;
				division *= 10;
				right /= 10;
				if (j > i) {
					if (rotatedNumbers == null) {
						listNumbers.add(rotatedNumbers = new ArrayList<Integer>());
						rotatedNumbers.add(i);
					}
					if (!rotatedNumbers.contains(j)) {
						rotatedNumbers.add(j);
					}
				}
			}
		}
		System.out.println(System.currentTimeMillis() - s + "ms");

		int T = ni();
		for (int t = 0; t < T; t++) {
			int A = ni();
			int B = ni();
			int result = 0;
			for (List<Integer> rotatedNumbers : listNumbers) {
				int firstNumber = rotatedNumbers.get(0);
				if (firstNumber > B) {
					break;
				}
				if (firstNumber < A) {
					continue;
				}
				for (int j : rotatedNumbers) {
					if (j > firstNumber && j <= B) {
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}

	/*
	 * **************************************************************** BASIC READER
	 * *******************************
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
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
