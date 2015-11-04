import java.io.InputStream;
import java.util.*;

public class ThreeDigits2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		long s = System.currentTimeMillis();
		prepare();
		solve();
		System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int exp2 = countExp(2, n);
		int exp5 = countExp(5, n);
		exp2 -= exp5;
		int threeDigit = (powMod1000(2, exp2) * get(n)) % 1000;
		int f5 = n / 5;
		while (f5 > 0) {
			threeDigit = (threeDigit * get(f5)) % 1000;
			f5 /= 5;
		}
		int f2 = n / 2;
		while (f2 > 0) {
			threeDigit = (threeDigit * get(f2)) % 1000;
			f2 /= 2;
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

	static int get(int k) {
		return (mods[k % 1000] * powMod1000(mods[1000], k / 1000)) % 1000;
	}

	static int[] mods = new int[1001];
	static void prepare() {
		mods[0] = 1;
		int mod = 1;
		for (int i = 1; i <= 1000; i++) {
			if (i % 2 != 0 && i % 5 != 0) {
				mod = (mod * i) % 1000;
			}
			mods[i] = mod;
		}
	}
}
