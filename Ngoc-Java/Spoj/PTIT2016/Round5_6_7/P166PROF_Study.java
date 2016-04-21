import java.io.*;
import java.util.*;

class P166PROF_Study {
	static InputStream is;

	static final int MOD = 1000000009;

	public static void main(String[] args) {
		is = System.in;

		int n = ni();
		int m = ni();

		if (m == 1) {
			System.out.println(n == 1 ? 1 : 0);
			return;
		}

		if (m == 2) {
			System.out.println(solve2(n));
			return;
		}

		long result = solve3(n);
		long mod = powMod(2, n);
		for (int i = 3; i < m; i++) {
			result = (result * mod) % MOD;
		}

		System.out.println(result);
	}

	//static int[][] map2 = new int[][]{{}, {1}, {2}, {2, 1}, {3}, {3, 1}, {3, 2}, {1, 2, 3}};
	//static int[][] map3 = new int[][]{{}};

	static public long solve2(int n) {
		long[] preList = new long[8];
		Arrays.fill(preList, 0);
		long[] curList = new long[8];

		preList[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int code = 0; code < 8; code++) {
				if (preList[code] <= 0) {
					continue;
				}
				for (int next = 1; next <= 3; next++) {
					int bit = 1;
					int bitFlag = 1;
					int newCode = 1 << (next - 1);
					for (; bit <= 3; bit++, bitFlag <<= 1) {
						if ((code & bitFlag) != 0) {
							int newBit = bit ^ next;
							if (newBit == 0) {
								break;
							} else {
								newCode |= (1 << (newBit - 1));
							}
						}
					}
					if (bit == 4) {
						curList[newCode] = (curList[newCode] + preList[code]) % MOD;
					}
				}

			}

			long[] temp = preList;
			preList = curList;
			curList = temp;
			Arrays.fill(curList, 0);
		}

		long result = 0;
		for (long value : preList) {
			result += value;
		}
		return result % MOD;
	}
	
	static public long solve3(int n) {
		long[] preList = new long[128];
		Arrays.fill(preList, 0);
		long[] curList = new long[128];

		preList[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int code = 0; code < 128; code++) {
				if (preList[code] <= 0) {
					continue;
				}
				for (int next = 1; next <= 7; next++) {
					int bit = 1;
					int bitFlag = 1;
					int newCode = 1 << (next - 1);
					for (; bit <= 7; bit++, bitFlag <<= 1) {
						if ((code & bitFlag) != 0) {
							int newBit = bit ^ next;
							if (newBit == 0) {
								break;
							} else {
								newCode |= (1 << (newBit - 1));
							}
						}
					}
					if (bit == 8) {
						curList[newCode] = (curList[newCode] + preList[code]) % MOD;
					}
				}

			}

			long[] temp = preList;
			preList = curList;
			curList = temp;
			Arrays.fill(curList, 0);
		}

		long result = 0;
		for (long value : preList) {
			result += value;
		}
		return result % MOD;
	}

	static public long powMod(int a, int n) {
		if (n == 1) {
			return a;
		}
		long result = powMod(a, n / 2);
		result *= result;
		if (n % 2 == 1) {
			result *= a;
		}
		return result % MOD;
	}

	/* *
	 */

	/* ****************************************************************
	 * ******************* BASIC READER *******************************
	 * ****************************************************************/

	static byte[] inbuf = new byte[4096];
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
