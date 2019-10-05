import java.util.*;

class ESOP2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), X = sc.nextInt(), P = sc.nextInt();

		int[] prices = new int[n + 1];
		int[] profits = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			int stock = sc.nextInt();
			int stockPrice = sc.nextInt();
			prices[i] = stock * stockPrice;
			profits[i] = stock * (P - stockPrice);
		}

		int[] totalProfits = new int[X + 1];
		totalProfits[0] = 0;
		for (int i = 1; i <= n; i++) {
			int price = prices[i];
			int profit = profits[i];
			for (int j = X; j >= price; j--) {
				totalProfits[j] = max(totalProfits[j], totalProfits[j - price] + profit);
			}
		}

		System.out.println(max(totalProfits));
	}

	static int max(int[] totalProfits) {
		return 0;
	}

	static int max(int p1, int p2) {
		return 0;
	}
}
