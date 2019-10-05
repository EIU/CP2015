import java.util.*;

class ESOP {

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
				totalProfits[j] = Math.max(totalProfits[j], totalProfits[j - price] + profit);
			}
		}

		int maxProfit = 0;
		for (int j = 0; j <= X; j++) {
			if (totalProfits[j] > maxProfit) {
				maxProfit = totalProfits[j];
			}
		}

		System.out.println(maxProfit);
	}
}
