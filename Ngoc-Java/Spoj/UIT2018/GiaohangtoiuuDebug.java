import java.util.*;

public class GiaohangtoiuuDebug {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		n++;
		Store[] stores = new Store[n + 1];
		stores[0] = new Store(0, 0);
		stores[n] = new Store(Integer.MAX_VALUE, 0);
		for (int i = 1; i < n; i++) {
			stores[i] = new Store(sc.nextInt(), sc.nextInt());
		}
		Arrays.sort(stores, (s1, s2) -> s1.location - s2.location);

		// Initial idea, top-down thinking
		int warehouseLocation = stores[n - 1].location;
		long totalDistanceLeft = 0, totalProductLeft = 0;
		for (int i = 0; i < n - 1; i++) {
			Store store = stores[i];
			int newDeliver = store.product;
			if (totalDistanceLeft % k != 0) {
				newDeliver -= k - totalDistanceLeft % k;
			}
			int newTruct = (newDeliver + k - 1) / k;
			totalDistanceLeft += newTruct * (warehouseLocation - store.location);
			totalProductLeft += store.product;
			System.out.println(totalDistanceLeft + " " + totalProductLeft);
		}

		System.out.println("==================================");

		// Another way, to make it look identical with code of part 2
		totalDistanceLeft = 0;
		totalProductLeft = 0;
		for (int warehousePosition = 1; warehousePosition < n; warehousePosition++) {
			int shiftRight = stores[warehousePosition].location - stores[warehousePosition - 1].location;

			totalProductLeft += stores[warehousePosition - 1].product;
			totalDistanceLeft += (totalProductLeft + k - 1) / k * shiftRight;

			System.out.println(totalDistanceLeft + " " + totalProductLeft);
		}

		System.out.println("==================================");

		long minDistance = totalDistanceLeft;
		int minIndex = n - 1;

		int totalDistanceRight = 0, totalProductRight = 0;
		for (int warehousePosition = n - 2; warehousePosition > 0; warehousePosition--) {
			int shiftLeft = stores[warehousePosition + 1].location - stores[warehousePosition].location;

			// Left
			totalDistanceLeft -= (totalProductLeft + k - 1) / k * shiftLeft;
			totalProductLeft -= stores[warehousePosition].product;

			// Right
			totalProductRight += stores[warehousePosition + 1].product;
			totalDistanceRight += (totalProductRight + k - 1) / k * shiftLeft;

			if (totalDistanceLeft + totalDistanceRight <= minDistance) {
				minIndex = warehousePosition;
				minDistance = totalDistanceLeft + totalDistanceRight;
			} else {
				// break;
			}

			System.out.println(totalDistanceLeft + " " + totalDistanceRight);
		}

		System.out.println("==================================");
		System.out.println(minIndex + " " + minDistance);
	}

	static class Store {
		public Store(int location, int product) {
			this.location = location;
			this.product = product;
		}

		public int location;
		public int product;
	}

}
