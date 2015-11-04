import java.util.*;

public class Memory_Small {

	final static int MOD = 1000000009;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next(), 2);

		int max = 20;
		long[][] counts = new long[n + 1][max + 2];
		counts[0][0] = 1;

		for (int i = 0; i <= n; i++) {
			long zero = 0;
			for (int k = 0; k < max; k++) {
				zero += counts[i][k];
				if (zero != 0) {
					int j = i + (1 << k);
					if (j <= n) {
						counts[j][k + 1] = (counts[j][k + 1] + zero) % MOD;
					}
					j = i + (2 << k);
					if (j <= n) {
						counts[j][k + 1] += (counts[j][k + 1] + zero) % MOD;
					}
				}
			}
		}

		long count = 0;
		for (int k = 0; k < max; k++) {
			count += counts[n][k];
		}
		System.out.println(count % MOD);
	}
}
