import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DeadFraction {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 22);
		String number;
		while ((number = reader.readLine()).compareTo("0") != 0) {
			solve(number);
		}
	}

	static void solve(String number) {
		int len = number.length() - 3;
		long minNumerator = 0;
		long minDenominator = Long.MAX_VALUE;
		for (int i = 2; i < len; i++) {
			long n1 = i == 2 ? 0 : Long.parseLong(number.substring(2, i));
			long n2 = Long.parseLong(number.substring(i, len));

			long numerator = n1 * (decimalPower[len - i] - 1) + n2;
			long denominator = decimalPower[i - 2] * (decimalPower[len - i] - 1);
			long c = gcd(numerator, denominator);
			numerator /= c;
			denominator /= c;
			if (denominator < minDenominator) {
				minNumerator = numerator;
				minDenominator = denominator;
			}
		}
		System.out.println(minNumerator + "/" + minDenominator);
	}

	static long[] decimalPower = new long[]{
			1, 10, 100,
			1000, 10000, 100000,
			1000000, 10000000, 100000000,
			1000000000, 10000000000l, 100000000000l,
			1000000000000l, 10000000000000l, 100000000000000l,
			1000000000000000l, 10000000000000000l, 100000000000000000l,
			1000000000000000000l};

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
