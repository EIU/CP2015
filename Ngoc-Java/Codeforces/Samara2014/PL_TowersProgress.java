import java.math.BigInteger;
import java.util.Scanner;

public class PL_TowersProgress {

	static final long MOD = 1000 * 1000 * 1000 + 7;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		BigInteger i = BigInteger.valueOf(n / (k + 1));
		long pow2ModI = BigInteger.valueOf(2).modPow(i, BigInteger.valueOf(MOD)).longValue();

		long remain = n % (k + 1);

		long result = remain > 0 ? pow2ModI * (2 * remain - 1) : 0;
		result += (2 * k + 1) * (pow2ModI - 1);
		result %= MOD;

		System.out.println(result);
	}

}
