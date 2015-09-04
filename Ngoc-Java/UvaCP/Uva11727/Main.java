import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			int result = 0;

			if (a >= b && a >= c) {
				result = Math.max(b, c);
			}
			else if (b >= a && b >= c) {
				result = Math.max(a, c);
			}
			else {
				result = Math.max(a, b);
			}

			System.out.format("Case %d: %d%n", (t + 1), result);
		}
	}
}
