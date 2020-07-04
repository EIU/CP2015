import java.util.*;

public class PF_IlyaMuromets {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt(), k = reader.nextInt();
		long[] sumks = new long[n + 1];
		long preMax1k = 0;
		long max1k2 = 0;
		for (int i = 1; i <= n; i++) {
			int fi = reader.nextInt();
			sumks[i] = sumks[i - 1] + fi;
			preMax1k = Math.max(preMax1k, (i >= k ? (sumks[i - k] - (i >= 2 * k ? sumks[i - k - k] : 0)) : 0));
			max1k2 = Math.max(max1k2, preMax1k + sumks[i] - (i >= k ? sumks[i - k] : 0));
		}
		System.out.println(max1k2);
	}
}



