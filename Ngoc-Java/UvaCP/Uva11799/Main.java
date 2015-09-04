import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int max = Integer.MIN_VALUE;
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int speed = sc.nextInt();
				if (speed > max) {
					max = speed;
				}
			}
			System.out.format("Case %d: %d%n", (t + 1), max);
		}
	}
}
