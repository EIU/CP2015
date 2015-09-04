
public class P2017 {

	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		solve();
		System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static long K = 1000;
	static long M = K * K;
	static long B = M * K;
	static long T = B * K;
	static long P = T * K;

	static long MOD = (long) Math.pow(3, 15);

	static void solve() {
		int MAXDIGIT = 23;
		int MAXSUMDIGIT = MAXDIGIT * 9;

		// totals[i][j] = sum(a1_a2_...ai) for every group of ai: sum(ai) = j
		long[][] totals = new long[MAXDIGIT + 1][MAXSUMDIGIT + 11];
		long[][] counts = new long[MAXDIGIT + 1][MAXSUMDIGIT + 11];
		long[] mod10s = new long[MAXDIGIT + 2];

		long mod10 = 1;
		for (int i = 0; i < MAXDIGIT + 2; i++) {
			mod10s[i] = mod10;
			mod10 = (mod10 * 10) % MOD;
		}

		// Level 1: one digit
		for (int i = 1; i <= 9; i++) {
			totals[1][i] = i;
			counts[1][i] = 1;
		}

		for (int level = 2; level <= MAXDIGIT; level++) {
			for (int i = 1; i <= MAXSUMDIGIT; i++) {
				for (int j = 0; j <= 9; j++) {
					if (counts[level - 1][i] > 0) {
						counts[level][i + j] += counts[level - 1][i] % MOD;
						totals[level][i + j] += (totals[level - 1][i] * 10 + j * counts[level - 1][i]) % MOD;
					}
				}
			}
		}

		long[] results = new long[2 * MAXDIGIT + 2];
		results[1] = 45;
		for (int i = 1; i <= MAXDIGIT; i++) {
			long even = 0;
			long odd = 0;
			for (int j = 1; j <= MAXSUMDIGIT; j++) {
				// {R(i)}{L(i),0L(i-1)} OR {R(i)}{0-9}{L(i),0L(i-1)}
				long tr = totals[i][j];
				long cr = counts[i][j];

				long tl = 0;
				long cl = 0;
				for (int k = 1; k <= i; k++) {
					tl += totals[k][j];
					cl += counts[k][j];
				}
				tl %= MOD;
				cl %= MOD;
				// even += tr * mod10s[i] * cl + tl * cr;
				// odd += tr * mod10s[i + 1] * 10 * cl + 45 * mod10s[i] * cr * cl + tl * 10 * cr;
				even += multiplyModP(tr, mod10s[i], cl) + multiplyModP(tl, cr);
				odd += multiplyModP(tr, mod10s[i + 1], 10, cl) + multiplyModP(45, mod10s[i], cr, cl) + multiplyModP(tl, 10, cr);
			}
			results[2 * i] = even % MOD;
			results[2 * i + 1] = odd % MOD;
		}

		long result = 0;
		for (int i = 1; i <= 47; i++) {
			result = (result + results[i]) % MOD;
			System.out.println(i + " " + results[i] + " " + result);
		}
	}

	static long multiplyModP(long a, long b, long c, long d) {
		return multiplyModP(multiplyModP(a, b, c), d);
	}

	static long multiplyModP(long a, long b, long c) {
		return multiplyModP(multiplyModP(a, b), c);
	}

	static long multiplyModP(long a, long b) {
		return (a * b) % MOD;
		// long x4 = b / T;
		// x4 = ((x4 * a) % K) * T;
		// b %= T;
		//
		// long x3 = b / B;
		// x3 = ((x3 * a) % M) * B;
		// b %= B;
		//
		// long x2 = b / M;
		// x2 = ((x2 * a) % B) * M;
		// b %= M;
		//
		// long x1 = b / K;
		// x1 = ((x1 * a) % T) * K;
		// b %= K;
		//
		// b = (b * a) % MOD;
		// return (x4 + x3 + x2 + x1 + b) % MOD;
	}
}
