import java.io.*;
import java.util.*;

public class ADifferentListGame {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 5);
		long X = Long.parseLong(reader.readLine());
		long Y = X;

		// long s = System.currentTimeMillis();
		List<Long> divisors = new ArrayList<Long>();
		List<Long> primes = new ArrayList<Long>();

		divisors.add(1L);

		X = factorize(X, 2, divisors, primes);
		X = factorize(X, 3, divisors, primes);
		for (long p = 5; p * p <= X; p += 6) {
			X = factorize(X, p, divisors, primes);
			X = factorize(X, p + 2, divisors, primes);
		}

		if (X > 1) {
			factorize(X, X, divisors, primes);
		}

		List<Long> newDivisors = new ArrayList<Long>();
		for (Long div : divisors) {
			if (!primes.contains(div)) {
				newDivisors.add(div);
			}
		}

		divisors = newDivisors;
		Collections.sort(divisors);

		for (Long prime : primes) {
			Y /= prime;
		}

		System.out.println((Y > 1 && !primes.contains(Y) ? process(Y, divisors, 0) : 0) + primes.size());

		// System.out.println((System.currentTimeMillis() - s) + " ms");
	}

	static int process(long X, List<Long> divisors, int preindex) {
		int max = 1;
		int i = preindex + 1;
		long div = 0;
		while (i < divisors.size() && (div = divisors.get(i)) * div < X) {
			if (X % div == 0) {
				max = Math.max(max, 1 + process(X / div, divisors, i));
			}
			i++;
		}
		return max;
	}

	static long factorize(long X, long factor, List<Long> divisors, List<Long> primes) {
		int len = divisors.size();
		long power = 1;
		while (X % factor == 0) {
			power *= factor;
			X /= factor;
			for (int i = 0; i < len; i++) {
				divisors.add(divisors.get(i) * power);
			}
		}
		if (power > 1) {
			primes.add(factor);
		}
		return X;
	}
}
