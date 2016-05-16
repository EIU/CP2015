import java.io.IOException;
import java.util.*;

public class ProblemA {

	final static int MAX = 1000010;
	static boolean[] primes = new boolean[MAX + 1];
	static int[] countPrimes = new int[MAX + 1];

	static {
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		for (int i = 2; i * i <= MAX; i++) {
			if (primes[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					primes[j] = false;
				}
			}
		}

		for (int i = 2; i <= MAX; i++) {
			countPrimes[i] = countPrimes[i - 1] + (primes[i] ? 1 : 0);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuilder bf = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			if (l < r) {
				bf.append((countPrimes[r] - countPrimes[l - 1]) + "\n");
			}
			else {
				bf.append("0\n");
			}
		}

		System.out.print(bf.toString());
	}

}
