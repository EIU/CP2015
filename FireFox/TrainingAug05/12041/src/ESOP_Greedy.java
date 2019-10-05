import java.util.*;

public class ESOP_Greedy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), X = sc.nextInt(), P = sc.nextInt();

		ESOP[] esops = new ESOP[n + 1];
		esops[0] = new ESOP(0, 0, P);

		for (int i = 1; i <= n; i++) {
			esops[i] = new ESOP(sc.nextInt(), sc.nextInt(), P);
		}

		// Arrays.sort(esops, (e1, e2) -> e2.profit - e1.profit);
		// Arrays.sort(esops, (e1, e2) -> e2.price - e1.price);
		Arrays.sort(esops, (e1, e2) -> e1.stockPrice - e2.stockPrice);

		int maxProfit = 0;
		int i = 0;
		while (i <= n && esops[i].price <= X) {
			X -= esops[i].price;
			maxProfit += esops[i].profit;
		}

		System.out.println(maxProfit);
	}

	static class ESOP {
		public int stockPrice;
		public int price;
		public int profit;

		public ESOP(int stock, int stockPrice, int expectedPrice) {
			this.stockPrice = stockPrice;
			this.price = stock * stockPrice;
			this.profit = stock * (expectedPrice - stockPrice);
		}
	}
}
