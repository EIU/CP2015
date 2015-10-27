import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrimalPartitions {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		// long s = System.currentTimeMillis();
		prepare();
		solve();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		int n = ni();
		int k = ni();

		State[] states = new State[k];
		int number = ni();
		State state0 = new State(number, maxPrimes[number]);
		states[0] = state0;

		for (int i = 1; i < n; i++) {
			number = ni();

			for (int j = k - 1; j >= 0; j--) {
				State statej = states[j];
				State statej1 = j == 0 ? null : states[j - 1];

				if (statej != null) {
					statej.lastCPs = commonPrimes(number, statej.lastCPs);
					statej.score = Math.min(statej.score, maxPrimes[statej.lastCPs]);
				}

				if (statej1 != null) {
					int newScore = Math.min(maxPrimes[number], statej1.score);
					if (statej == null) {
						states[j] = new State(number, newScore);
					} else if (newScore >= statej.score) {
						statej.lastCPs = number;
						statej.score = newScore;
					}

					// Improve
					if (statej1.score == 0) {
						break;
					}
				}
			}
		}

		System.out.println(states[k - 1].score);
	}

	static int commonPrimes(int x, int y) {
		int cp = 1;
		while (x > 1 && y > 1) {
			if (maxPrimes[x] > maxPrimes[y]) {
				x = preDivs[x];
			} else if (maxPrimes[x] < maxPrimes[y]) {
				y = preDivs[y];
			} else {
				cp *= maxPrimes[x];
				x = preDivs[x];
				y = preDivs[y];
			}
		}
		return cp;
	}

	static final int MAXPRIME = 1001 * 1001;
	static int[] maxPrimes = new int[MAXPRIME];
	static int[] preDivs = new int[MAXPRIME];

	static void prepare() {
		for (int i = 2; i < MAXPRIME; i++) {
			if (maxPrimes[i] == 0) {
				for (int j = i << 1, k = 2; j < MAXPRIME; j += i, k++) {
					maxPrimes[j] = i;
					preDivs[j] = k;
				}
				maxPrimes[i] = i;
				preDivs[i] = 1;
			}
		}

		// for (int i = 2; i < MAXPRIME; i++) {
		// int maxPrime = maxPrimes[i];
		// if (maxPrime != 0) {
		// int j = i;
		// // May improve
		// // while (j % maxPrime == 0) {
		// j /= maxPrime;
		// // }
		// preDivs[i] = j;
		// } else {
		// maxPrimes[i] = i;
		// preDivs[i] = 1;
		// }
		// }
	}

	static class State {
		public int lastCPs;
		public int score;

		public State(int lastCp, int score) {
			this.lastCPs = lastCp;
			this.score = score;
		}
	}
	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 18];
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
