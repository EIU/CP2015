package uva;

import java.util.Scanner;

public class SummingDigits {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int n = scan.nextInt();
			if (n == 0) {
				break;
			}
			System.out.println(sumdigits(sumdigits(sumdigits(n))));
		}
		scan.close();
	}

	private static int sumdigits(int n) {
		int sum = 0;
		while (n > 0) {
			sum += n % 10;
			n = n / 10;
		}
		return sum;
	}
}