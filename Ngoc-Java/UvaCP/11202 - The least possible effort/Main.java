import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == m) {
				n = (n + 1) / 2;
				System.out.printf("%d%n", n * (n + 1) / 2);
			}
			else {
				n = (n + 1) / 2;
				m = (m + 1) / 2;
				System.out.printf("%d%n", n * m);
			}
		}
		sc.close();
	}
}
