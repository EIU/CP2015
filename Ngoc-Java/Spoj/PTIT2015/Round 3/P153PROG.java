import java.util.*;

class P153PROG {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long k = sc.nextLong();
		if (2 * k - 1 <= n) {
			System.out.println(2 * k - 1);
		}
		else if (n % 2 == 0) {
			System.out.println(2 * k - n);
		}
		else {
			System.out.println(2 * k - 1 - n);
		}
	}
}