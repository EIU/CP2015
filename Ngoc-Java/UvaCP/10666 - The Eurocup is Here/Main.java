import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {

			int N = sc.nextInt();
			int X = sc.nextInt();

			int optimistic = 0;
			int pestimistic = 0;
			boolean hasPestimistic = true;

			if (X == 0)
				pestimistic = N;

			while (X > 0) {
				if (X % 2 == 0) {
					if (hasPestimistic) {
						pestimistic++;
					}
				}
				else {
					optimistic++;
					hasPestimistic = false;
				}
				X /= 2;
			}
			int team = (int) Math.pow(2, N);
			int opRank = 1 + optimistic;
			int pesRank = team - (int) Math.pow(2, pestimistic) + 1;
			System.out.println(opRank + " " + pesRank);
		}
	}
}
