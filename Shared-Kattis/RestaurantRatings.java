import java.util.Scanner;

public class RestaurantRatings {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n > 0) {

			int[] ratings = new int[n];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += (ratings[i] = sc.nextInt());
			}

			long[][] counts = new long[n][sum + 1];
			for (int i = 0; i <= sum; i++) {
				counts[n - 1][i] = i + 1;
			}

			for (int i = n - 2; i >= 0; i--) {
				for (int j = 1; j <= sum; j++) {
					counts[i][j] = counts[i - 1][sum - j];
				}
			}

			long result = 0;
			for (int i = 0; i <= sum; i++) {
				result += counts[0][i];
			}

			System.out.println(counts[0][sum]);

			n = sc.nextInt();
		}
	}

}
