import java.io.InputStream;
import java.util.*;

public class ThreeDigits {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		// long s = System.currentTimeMillis();
		prepare();
		solve();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int exp2 = countExp(2, n);
		int exp3 = countExp(3, n);
		int exp5 = countExp(5, n);
		exp2 -= exp5;
		int threeDigit = powMod1000(2, exp2) * powMod1000(3, exp3);
		for (int i = 7; i <= n; i++) {
			if (primes[i]) {
				int exp = countExp(i, n);
				if (exp > 0) {
					threeDigit = (threeDigit * powMod1000(i, exp)) % 1000;
				}
			}
		}

		String out = threeDigit + "";
		if (n > 7) {
			out = ("000" + threeDigit);
			out = out.substring(out.length() - 3, out.length());
		}

		System.out.println(out);
	}

	static int countExp(int p, int n) {
		int result = 0;
		while (n > 0) {
			n /= p;
			result += n;
		}
		return result;
	}

	static int powMod1000(int a, int p) {
		if (p == 0) {
			return 1;
		}
		int haft = powMod1000(a, p / 2);
		if (p % 2 == 0) {
			return (int) (((long) haft * haft) % 1000l);
		}
		return (int) (((long) a * haft * haft) % 1000l);
	}

	static final int MAXPRIME = 10000001;
	static boolean[] primes = new boolean[MAXPRIME];

	static void prepare() {
		Arrays.fill(primes, true);
		for (int i = 2; i < 3200; i++) {
			if (primes[i]) {
				for (int j = i * i; j < MAXPRIME; j += i) {
					primes[j] = false;
				}
			}
		}
	}
}
