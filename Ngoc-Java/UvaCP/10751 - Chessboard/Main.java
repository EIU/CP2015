import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		double sqrt2 = Math.sqrt(2) - 1;
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			double result = 0;
			if (N > 1) {
				result = N * N + (N - 2) * (N - 2) * sqrt2;
			}
			System.out.printf("%.3f%n", result);
			if (t < T - 1) {
				System.out.println();
			}
		}
	}
}

// Sample solution when n=6 (There are some systematic solutions)
// ! 1 2 3 4 5 6
// 1 \ - \ - - |
// 2 | \ \ \ \ |
// 3 | | \ \ | |
// 4 | \ \ \ \ |
// 5 | | \ \ | |
// 6 | - \ - \ -
// 11-22-33-44-55-45-34-23-12-13-24...
// Number of \: (n-2) + (n-3)... + 1 + (n-3)... + 1 = (n-2)^2 for n >= 2

// Alternative
// ! 1 2 3 4 5 6
// 1 \ - \ - - |
// 2 | \ \ \ \ |
// 3 \ \ \ \ | |
// 4 | \ \ \ \ |
// 5 - \ - \ | |
// 6 | - - - - -

// Mathematical Proving
//

