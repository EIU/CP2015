import java.util.*;

class Assembly {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			long M = sc.nextLong();

			Robot[] robots = new Robot[N];
			for (int i = 0; i < N; i++) {
				robots[i] = new Robot(sc.nextInt(), sc.nextInt());
			}
			Arrays.sort(robots);

			long invPerUnit = robots[0].invPerProduct;
			long product = robots[0].productivity;
			for (int i = 1; i < N; i++) {

				int improveP = robots[i].productivity - robots[i - 1].productivity;
				if (invPerUnit * improveP >= M) {
					product += M / invPerUnit;
					M = 0;
					break;
				}

				M -= invPerUnit * improveP;
				invPerUnit += robots[i].invPerProduct;
				product = robots[i].productivity;
			}
			product += M / invPerUnit;
			
			System.out.println(product);
		}
	}

	static class Robot implements Comparable<Robot> {
		public int productivity;
		public int invPerProduct;

		public Robot(int p, int m) {
			this.productivity = p;
			this.invPerProduct = m;
		}

		@Override
		public int compareTo(Robot arg0) {
			int t = this.productivity - arg0.productivity;
			if (t != 0) {
				return t;
			}
			return this.invPerProduct - arg0.invPerProduct;
		}
	}
}
