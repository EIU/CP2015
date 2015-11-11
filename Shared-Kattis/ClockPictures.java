import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ClockPictures {
	static InputStream is;

	static final int MAXLENGTH = 360000;
	static int[] primes = {5, 7, 11, 13, 17, 19};
	//{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 87, 89, 91, 97};

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		int[] seq1 = read(n);
		int[] seq2 = read(n);

		int[] testIndexes = new int[n];
		int needTest = n;
		for (int i = 0; i < n; i++) {
			testIndexes[i] = i;
		}

		for (int repeat = 0; repeat < 12 && needTest > 0; repeat++) {
			int testSize = Math.min(n, primes[repeat % primes.length]);
			int[] testPositions = genRandom(testSize);

			int tested = 0;
			for (int i = 0; i < needTest; i++) {
				int testIndex = testIndexes[i];
				int baseAngle = seq2[testIndex];
				boolean isOK = true;
				for (int position : testPositions) {
					int s2Pos = testIndex + position;
					if (s2Pos >= n) {
						s2Pos -= n;
					}
					int angle = seq2[s2Pos] - baseAngle;
					if (angle < 0) {
						angle += MAXLENGTH;
					}
					if (seq1[position] != angle) {
						isOK = false;
						break;
					}
				}
				if (isOK) {
					testIndexes[tested++] = testIndexes[i];
				}
			}
			needTest = tested;
		}

		if (needTest == 0) {
			System.out.println("impossible");
			return;
		}

		int i;
		for (i = 0; i < needTest; i++) {
			int testIndex = testIndexes[0];
			int baseAngle = seq2[testIndex];
			int position;
			for (position = 0; position < n; position++) {
				int s2Pos = testIndex + position;
				if (s2Pos >= n) {
					s2Pos -= n;
				}
				int angle = seq2[s2Pos] - baseAngle;
				if (angle < 0) {
					angle += MAXLENGTH;
				}
				if (seq1[position] != angle) {
					break;
				}
			}
			if (position == n) {
				break;
			}
		}

		System.out.println(i < needTest ? "possible" : "impossible");

	}
	static int[] genRandom(int size) {
		int[] numbers = new int[size];
		for (int i = 0; i < size; i++) {
			numbers[i] = (int) (Math.random() * size);
		}
		return numbers;
	}

	static int[] read(int n) {
		int[] numbers = new int[n];
		int base = ni();
		for (int i = 1; i < n; i++) {
			int number = ni() - base;
			if (number < 0) {
				number += MAXLENGTH;
			}
			numbers[i] = number;
		}
		Arrays.sort(numbers);
		return numbers;
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
