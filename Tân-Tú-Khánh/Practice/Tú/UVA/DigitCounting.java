package uva;

import java.util.Scanner;

public class DigitCounting {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 0; i < test; i++) {
			int a[] = new int[10];
			int n = scan.nextInt();
			for (int j = 1; j <= n; j++) {
				String b = "";
				b += j;
				for(int k = 0; k < b.length(); k++){
					a[b.charAt(k) - 48]++;
				}
			}
			for (int j = 0; j < 10; j++) {
				System.out.print(a[j] + " ");
			}
		}
	}
}
