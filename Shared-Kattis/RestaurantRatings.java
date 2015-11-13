import java.util.Scanner;

public class RestaurantRatings {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n > 0) {

			int[] ratings = new int[n];
			int totalPoint = 0;
			for (int i = 0; i < n; i++) {
				totalPoint += (ratings[i] = sc.nextInt());
			}

			long[][] counts = new long[n + 1][totalPoint + 1];
			counts[n][0] = 1;

			for (int i = n - 1; i >= 0; i--) {
				for (int j = 0; j <= totalPoint; j++) {
					for (int k = 0; k <= j; k++) {
						counts[i][j] += counts[i + 1][j - k];
					}
				}
			}

			long result = 0;
			for (long v : counts[0]) {
				result += v;
			}

			for (int i = 0; i < n - 1; i++) {
				for (int k = ratings[i] + 1; k <= totalPoint; k++) {
					result -= counts[i + 1][totalPoint - k];
				}
				totalPoint -= ratings[i];
			}

			System.out.println(result);

			n = sc.nextInt();
		}
	}
}
