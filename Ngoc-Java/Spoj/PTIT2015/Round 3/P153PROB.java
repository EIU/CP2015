import java.util.*;
import java.math.*;

class P153PROB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() + 1;

		BigInteger big = BigInteger.valueOf(1);
		for (int i = 1; i <= n; i++) {
			big = big.multiply(BigInteger.valueOf(4 * i - 2));
			big = big.divide(BigInteger.valueOf(i + 1));
		}
		System.out.println(big.toString());
	}
}