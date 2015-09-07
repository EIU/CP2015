import java.util.*;

public class Trick {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		double X = sc.nextDouble();
		long p = (int) (X * 10000);
		long q = 10000;
		long gcdpq = gcd(p, q);
		p /= gcdpq;
		q /= gcdpq;

		boolean hasSolution = false;
		long pow10 = 1;
		for (int k = 0; k <= 7; k++) {
			for (int d = 1; d <= 9; d++) {
				long A = d * (pow10 * p - q);
				long B = (10 * q - p);
				if (B != 0 && A % B == 0) {
					long H = A / B;
					if (H < pow10) {
						System.out.println(d * pow10 + H);
						hasSolution = true;
					}
				}
			}
			pow10 *= 10;
		}
		if (!hasSolution) {
			System.out.println("No solution");
		}
	}

	public static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
