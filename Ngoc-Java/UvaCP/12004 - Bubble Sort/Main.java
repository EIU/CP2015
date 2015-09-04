import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			long n = sc.nextLong();
			n = n * (n - 1);
			long p = 4;
			if (n % 2 == 0) {
				n /= 2;
				p /= 2;
			}
			if (n % 2 == 0) {
				n /= 2;
				p /= 2;
			}
			if (p == 1) {
				System.out.printf("Case %d: %d%n", (t + 1), n);
			}
			else {
				System.out.printf("Case %d: %d/%d%n", (t + 1), n, p);
			}
		}
	}
}

// Solution
// f(n + 1)
// n1 = (n+1) can be in any position from 1..(n+1) with equal possibility.
// => There are (1+2...+n) swap operation between n1 and {i|i<=n}
// => (n+1)f(n+1) = (n+1)f(n) + (0+1+2...+n)
// => f(n+1) = f(n) + n/2
// => f(n) = n(n-1)/4
