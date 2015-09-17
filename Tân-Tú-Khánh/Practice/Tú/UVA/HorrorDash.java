package uva;

import java.util.Scanner;

public class HorrorDash {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			int n = scan.nextInt();
			int[] c = new int[n];
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < c.length; j++) {
				c[j] = scan.nextInt();
				if (max < c[j]) {
					max = c[j];
				}
			}
			System.out.println("Case " + i + ": " + max);
		}
		scan.close();
	}

}
