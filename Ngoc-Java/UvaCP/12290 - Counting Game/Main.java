import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();

			if (n == 0) {
				break;
			}

			// Solution: mth will count 1 + 2i(n-1) + (m-1) and 1 + (2i+1)(n-1) + (n-m)
			// Suppose i < 10 * k
			int sum = 1; // 1+0(n-1)
			int count = 0;
			while (true) {
				if (checkClap(sum + m - 1)) {
					count++;
					if (count == k) {
						System.out.println(sum + m - 1);
						break;
					}
				}
				sum += (n - 1);
				if (m != 1 && m != n && checkClap(sum + n - m)) {
					count++;
					if (count == k) {
						System.out.println(sum + n - m);
						break;
					}
				}
				sum += (n - 1);
			}
		}
	}

	static boolean checkClap(int i) {
		if (i % 7 == 0) {
			return true;
		}
		while (i > 0) {
			if (i % 10 == 7) {
				return true;
			}
			i /= 10;
		}
		return false;
	}
}
