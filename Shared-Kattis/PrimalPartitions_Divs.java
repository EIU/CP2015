import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrimalPartitions_Divs {
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
		ArrayList<Integer> cp = parseDivs(ni());
		State state0 = new State(1000001, cp);
		states[0] = state0;

		for (int i = 1; i < n; i++) {
			cp = parseDivs(ni());

			for (int j = k - 1; j > 0; j--) {
				State statej = states[j];
				State statej1 = states[j - 1];
				if (statej != null) {
					statej.lastCPs = commonPrimes(cp, statej.lastCPs);
					statej.score = Math.min(statej.score, statej.last());

				}
				if (statej1 != null) {
					int newScore = Math.min(cp.get(cp.size() - 1), statej1.score);
					if (statej == null) {
						states[j] = new State(newScore, cp);
					} else if (newScore >= statej.score) {
						statej.lastCPs = cp;
						statej.score = newScore;
					}

					// Improve
					if (statej1.score == 0) {
						break;
					}
				}
			}

			state0.lastCPs = commonPrimes(states[0].lastCPs, cp);
			state0.score = state0.last();
		}

		System.out.println(states[k - 1].score);
	}

	static ArrayList<Integer> commonPrimes(ArrayList<Integer> x, ArrayList<Integer> y) {
		ArrayList<Integer> cp = new ArrayList<Integer>();
		cp.add(0);
		int i = 1;
		int j = 1;
		while (i < x.size() && j < y.size()) {
			int dif = x.get(i) - y.get(j);
			if (dif > 0) {
				j++;
			} else if (dif < 0) {
				i++;
			} else {
				cp.add(x.get(i));
				i++;
				j++;
			}
		}
		return cp;
	}

	static ArrayList<Integer> parseDivs(int number) {
		ArrayList<Integer> cp = new ArrayList<Integer>();
		cp.add(0);
		int limit = (int) Math.ceil(Math.sqrt(number));
		for (int i = 2; i <= limit && i <= number; i++) {
			if (primes[i] && number % i == 0) {
				cp.add(i);
				while (number > 1 && number % i == 0) {
					number /= i;
				}
			}
		}
		if (number > 1) {
			cp.add(number);
		}
		return cp;
	}

	static final int MAXPRIME = 1001;
	static boolean[] primes = new boolean[MAXPRIME];

	static void prepare() {
		Arrays.fill(primes, true);
		for (int i = 2; i < MAXPRIME; i++) {
			if (primes[i]) {
				int i2 = i * i;
				for (int j = i2; j < MAXPRIME; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	static class State {
		public ArrayList<Integer> lastCPs;
		public int score;

		public State(int score, ArrayList<Integer> cp) {
			this.score = Math.min(score, cp.get(cp.size() - 1));
			lastCPs = cp;
		}

		public int last() {
			return lastCPs.get(lastCPs.size() - 1);
		}
	}
	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1 << 20];
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
